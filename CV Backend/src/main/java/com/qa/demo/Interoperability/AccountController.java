package com.qa.demo.Interoperability;

import com.qa.demo.domain.Account;
import com.qa.demo.repository.AccountRepository;
import com.qa.demo.service.MongoUserDetailsService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        Account account = repository.findBy_id(id);
        try {
            mongoTemplate.upsert(Query.query(where("_id").is(id)), Update.update("file",new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes())), Account.class);

        } catch (IOException e) {
            e.printStackTrace();
            return "File addition failed";
        }
        return "File addition successful to account:"+account.getFirstName()+" "+account.getLastName();
    }

    @GetMapping(value = "/retrieve/{id}")
    public String retrieveFileFromUser(@PathVariable("id") ObjectId id){
        Account account = repository.findBy_id(id);
        Binary cv = account.getFile();
        if(cv != null){
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(account.getFirstName()+account.getLastName()+".pdf");
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

    @PostMapping(value = "/mail/{email}")
    public Account getAccountByEmail(@PathVariable("email") String email, HttpServletResponse response){
        Account retrieved = repository.findByEmail(email);
        if (retrieved == null){
            response.setStatus(404);}
        return retrieved;
    }

    @PutMapping(value = "/{id}")
    public String modifyAccountById(@PathVariable("id")ObjectId id, @Valid @RequestBody Account account){
    	Account updatable = repository.findBy_id(id);
        account.set_id(id);
        updatable.updateFields(account);
        repository.save(updatable);
        return "Account Updated";
    }
    
    @PutMapping(value = "/password/{id}")
    public String modifyPasswordById(@PathVariable("id")ObjectId id, @Valid @RequestBody Account account){
        account.set_id(id);
        account.setPassword(bCode.encode(account.getPassword()));
        repository.save(account);
        return "Account Updated";
    }


    @DeleteMapping(value = "/{id}")
    public String deleteAccount(@PathVariable ObjectId id){
        repository.delete(repository.findBy_id(id));
        return "Account Deleted";
    }

}
