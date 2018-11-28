package com.qa.demo.Interoperability;

import com.qa.demo.Constants;
import com.qa.demo.NullChecker;
import com.qa.demo.domain.Account;
import com.qa.demo.domain.PasswordObject;
import com.qa.demo.domain.Role;
import com.qa.demo.repository.AccountRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static com.qa.demo.domain.Role.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Transactional
@RestController
@RequestMapping("/account")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AccountController {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private PasswordEncoder bCode;


    @PostAuthorize("hasRole('ROLE_admin')")
    @GetMapping(value = "/getall")
    public List<Account> getAllAccounts(){
        return repository.findAll();
    }

    @PostMapping(value = "/")
    public Account createAccount(@Valid @RequestBody Account account){
        account.set_id(ObjectId.get());
        account.setPassword(bCode.encode(account.getPassword()));
        repository.save(account);
        return account;
    }

    @PostMapping(value = "/upload/{id}")
    public String addFileToUser(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") ObjectId id){
        Account account = repository.findBy_id(id);
        if(!NullChecker.NullChecker(account)) {
            try {
                mongoTemplate.upsert(
                        Query.query(where("_id").is(id)),
                        Update.update(
                                "file",
                                new Binary(
                                        BsonBinarySubType.BINARY,
                                        multipartFile.getBytes()
                                )
                        ),
                        Account.class
                );

            } catch (IOException e) {
                e.printStackTrace();
                return Constants.fileAdditionFailed;
            }
            return Constants.fileAdditionSuccessful + account.getFirstName() + " " + account.getLastName();
        }
        return Constants.accountNotFound;
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<byte[]> getUserFile(@PathVariable("id") ObjectId id){
        Account account = repository.findBy_id(id);
        if(!NullChecker.NullChecker(account)) {
            Binary accountCV = account.getFile();
            if (!NullChecker.NullChecker(accountCV)) {
                FileOutputStream fileOutputStream = null;
                if (!NullChecker.NullChecker(accountCV)) {

                    try {
                        fileOutputStream = new FileOutputStream(Constants.cvSavedNameLocal);
                        fileOutputStream.write(accountCV.getData());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println(Constants.fileNotExist);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (!NullChecker.NullChecker(fileOutputStream)) {
                            {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    System.out.println(Constants.fileStreamNotClosed);
                                }
                            }
                        }
                    }
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentLength(accountCV.getData().length);
                headers.set("Content-Disposition", "attachment; filename=" + account.getFirstName() + account.getLastName() + ".pdf");
                return new ResponseEntity<>(accountCV.getData(), headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


   /* @GetMapping(value = "/retrieve/{id}")
    public String retrieveFileFromUser(@PathVariable("id") ObjectId id){
        Account account = repository.findBy_id(id);
        if(!NullChecker.NullChecker(account)) {
            Binary cv = account.getFile();
            if (!NullChecker.NullChecker(cv)) {
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(account.getFirstName() + account.getLastName() + ".pdf");
                        fileOutputStream.write(cv.getData());

                    } catch (FileNotFoundException e) {
                        System.out.println(e.toString());
                        return Constants.fileNotExist;
                    } catch (IOException e) {
                        System.out.println(e.toString());
                    } finally {
                        if (fileOutputStream != null) {
                            {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    return Constants.fileStreamNotClosed;
                                }
                            }
                        }
                    }
                return Constants.fileRetrievalSuccess;
            }
            return Constants.accountNotFound;
        }
        return Constants.fileNotExist;
    }*/

    @GetMapping(value = "/{id}")
    public Account getAccountById(@PathVariable("id") ObjectId id, HttpServletResponse response){
        if (NullChecker.NullChecker(repository.findBy_id(id))){
            response.setStatus(404);
        }
        return repository.findBy_id(id);
    }

    @GetMapping(value = "/role/{role}")
    public List<Account> getAccountByRole(@PathVariable("role") Role.Roles search, HttpServletResponse response){
        if (NullChecker.NullChecker(repository.findByUserRole(search))){
            response.setStatus(404);
        }
        return repository.findByUserRole(search);
    }

    @PostMapping(value = "/mail/{email}")
    public Account getAccountByEmail(@PathVariable("email") String email, HttpServletResponse response){
        Account retrieved = repository.findByEmail(email);
        if (NullChecker.NullChecker(retrieved)){
            response.setStatus(404);}
        return retrieved;
    }

    @PutMapping(value = "/{id}")
    public String modifyAccountById(Principal principal, @PathVariable("id")ObjectId id, @Valid @RequestBody Account account){
        if(validOperation(principal, id)) {
            Account updatable = repository.findBy_id(id);
            account.set_id(id);
            updatable.updateFields(account);
            repository.save(updatable);
            return Constants.accountUpdated;
        }
        return Constants.notAuthorised;
    }
    
    @PutMapping(value = "/password/{id}")
    public String modifyPasswordById(Principal principal, @PathVariable("id")ObjectId id, @RequestBody PasswordObject passwords, HttpServletResponse response){
        Account updatable = repository.findBy_id(id);
        if(validOperation(principal, id) && bCode.matches(passwords.getOldPassword(), updatable.getPassword())) {
            updatable.setPassword(bCode.encode(passwords.getNewPassword()));
            repository.save(updatable);
            return Constants.accountUpdated;
        }
        else{
            response.setStatus(404);
        }
        return Constants.notAuthorised;
    }


    @DeleteMapping(value = "/{id}")
    public String deleteAccount(Principal principal, @PathVariable ObjectId id){
        if (validOperation(principal, id) && moreAdmins(id)) {
            repository.delete(repository.findBy_id(id));
            return Constants.accountDeleted;
        }
        return Constants.notAuthorised;
    }

    @DeleteMapping(value = "/deletecv/{id}")
    public String deleteCV(Principal principal, @PathVariable ObjectId id){
        if(validOperation(principal, id)){
            mongoTemplate.upsert(Query.query(where("_id").is(id)), Update.update("file",null), Account.class);
            return Constants.cvDeleted;
        }
        return Constants.cvCouldNotBeDeleted;
    }

    private boolean validOperation(Principal principal, ObjectId id){
        Account accountOfCall = repository.findByEmail(principal.getName());
        return (accountOfCall.getUserRole() == Roles.admin || accountOfCall.get_id().equals(id.toHexString()));
    }
    private boolean moreAdmins(ObjectId id){
        Account isAdmin = repository.findBy_id(id);
        List<Account> admins = repository.findByUserRole(Roles.admin);
        return !(isAdmin.getUserRole()==Roles.admin && admins.size() <2);
    }

}
