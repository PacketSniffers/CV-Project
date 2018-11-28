package com.qa.demo.Interoperability;



import com.qa.demo.domain.Account;
import com.qa.demo.domain.PasswordObject;
import com.qa.demo.repository.AccountRepository;
import com.qa.demo.service.MongoUserDetailsService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

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

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @PostAuthorize("hasRole('ROLE_admin')")
    @GetMapping(value = "/getall")
    public List<Account> getAllAccounts(){
        return repository.findAll();
    }

    @PostMapping(value = "/")
    public Account createAccount(@Valid @RequestBody Account account){
        System.out.println("Account creation attempting");
        account.set_id(ObjectId.get());
        //account.setUserRole("user");
        //System.out.println(bCode.encode(account.getPassword()));
        account.setPassword(bCode.encode(account.getPassword()));
        repository.save(account);
        return account;
    }

//    @DeleteMapping(value = "/delete/{id}")
//    public String deleteFileFromUser(@PathVariable("id") ObjectId id){
//
//    }

    @PostMapping(value = "/upload/{id}")
    public String addFileToUser(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") ObjectId id){
        System.out.println("File uploading being attempted");
        Account account = repository.findBy_id(id);
        try {
            mongoTemplate.upsert(Query.query(where("_id").is(id)), Update.update("file",new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes())), Account.class);

        } catch (IOException e) {
            e.printStackTrace();
            return "File addition failed";
        }
        return "File addition successful to account:"+account.getFirstName()+" "+account.getLastName();
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<byte[]> getUserFile(@PathVariable("id") ObjectId id){
        System.out.println("File download being attempted");
        Account account = repository.findBy_id(id);
        Binary accountCV = repository.findBy_id(id).getFile();
        FileOutputStream fileOutputStream = null;
        if(accountCV != null){

            try {
                fileOutputStream = new FileOutputStream("CV.pdf");
                fileOutputStream.write(accountCV.getData());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File does not exist for user");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(fileOutputStream != null){{
                    try {
                        fileOutputStream.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("File stream could not be closed");
                    }
                }
                }
            }
        }
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.valueOf(accountCV.getContentType()));
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(accountCV.getData().length);
        headers.set("Content-Disposition", "attachment; filename="+account.getFirstName()+account.getLastName()+".pdf");
        return new ResponseEntity<>(accountCV.getData(), headers, HttpStatus.OK);
    }


    @GetMapping(value = "/retrieve/{id}")
    public String retrieveFileFromUser(@PathVariable("id") ObjectId id){
        Account account = repository.findBy_id(id);
        Binary cv = account.getFile();
        if(cv != null){
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream("C:\\Users\\Admin\\Desktop\\test\\springbootdemo\\public\\"+account.getFirstName()+account.getLastName()+".pdf");
                fileOutputStream.write(cv.getData());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "File does not exist for user";
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(fileOutputStream != null){{
                    try {
                        fileOutputStream.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        return "File stream could not be closed";
                        }
                    }
                }
            }
        }
        return "File retrieved successfully";
    }

    @GetMapping(value = "/{id}")
    public Account getAccountById(@PathVariable("id") ObjectId id){
        return repository.findBy_id(id);
    }

    @GetMapping(value = "/role/{role}")
    public List<Account> getAccountByRole(@PathVariable("role") String search){
        return repository.findByUserRole(search);
    }

    @PostMapping(value = "/mail/{email}")
    public Account getAccountByEmail(@PathVariable("email") String email, HttpServletResponse response){
        Account retrieved = repository.findByEmail(email);
        if (retrieved == null){
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
            return "Account Updated";
        }
        return "Not Authorised";
    }
    
    @PutMapping(value = "/password/{id}")
    public String modifyPasswordById(Principal principal, @PathVariable("id")ObjectId id, @RequestBody PasswordObject passwords, HttpServletResponse response){
        Account updatable = repository.findBy_id(id);
        if(validOperation(principal, id) && bCode.matches(passwords.getOldPassword(), updatable.getPassword())) {
            updatable.setPassword(bCode.encode(passwords.getNewPassword()));
            repository.save(updatable);
            return "Account Updated";
        }
        else{
            response.setStatus(404);
        }
        return "Not Authorised";
    }


    @DeleteMapping(value = "/{id}")
    public String deleteAccount(Principal principal, @PathVariable ObjectId id){
        if (validOperation(principal, id)) {
            repository.delete(repository.findBy_id(id));
            return "Account Deleted";
        }
        return "Not Authorised";
    }

    @DeleteMapping(value = "/deletecv/{id}")
    public String deleteCV(Principal principal, @PathVariable ObjectId id){
        if(validOperation(principal, id)){
            mongoTemplate.upsert(Query.query(where("_id").is(id)), Update.update("file",null), Account.class);
            return "CV was deleted";
        }
        return "CV could not be deleted";
    }

    private boolean validOperation(Principal principal, ObjectId id){
        Account accountOfCall = repository.findByEmail(principal.getName());
        return (accountOfCall.getUserRole().equalsIgnoreCase("admin") || accountOfCall.get_id().equals(id.toHexString())) ? true : false;
    }


}
