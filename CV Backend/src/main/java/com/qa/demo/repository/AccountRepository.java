package com.qa.demo.repository;

import com.qa.demo.domain.Account;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String>{
    Account findBy_id(ObjectId _id);
}
