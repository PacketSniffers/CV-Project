package com.qa.demo.domain;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Document
public class Account {
    @Id
    private ObjectId _id;

    private String firstName;
    private String lastName;
    @Email
    private String email;
    @NotNull
    private String password;
    private Binary file;


    public Account(){};

    public Account(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Binary getFile() {
        return file;
    }

    public void setFile(Binary file) {
        this.file = file;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void updateFields(Account inAccount) {
    	if (!inAccount.email.equals(null)) {
    		this.email = inAccount.email;
    	}
    	if (!inAccount.firstName.equals(null)) {
    		this.firstName = inAccount.firstName;
    	}
    	if (!inAccount.lastName.equals(null)) {
    		this.lastName = inAccount.lastName;
    	}
    }
}
