package com.qa.demo.tests.backend;


import static org.junit.Assert.assertEquals;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.demo.domain.Account;

public class AccountTest {
    Account accountObj = new Account();
    static int count = 1;

    @BeforeClass
    public static void before() {
        System.out.println("\nWelcome to the Tests");
    }

    @Before
    public void setup() {
        System.out.println("Test: " + count);
    }

    @After
    public void teardown() {
       count++;
    }
    
    @Test
    public void emailTest() {
    	String email = "brad@mail.com";
    	accountObj.setEmail(email);
    	assertEquals(email, accountObj.getEmail());
    }

    @Test
    public void passwordTest() {
    	String password = "Password123";
    	accountObj.setPassword(password);
    	assertEquals(password, accountObj.getPassword());
    }
    
    @Test
    public void firstNameTest() {
    	String name = "Brad";
    	accountObj.setFirstName(name);
    	assertEquals(name, accountObj.getFirstName());
    }
    
    @Test
    public void lastNameTest() {
    	String email = "Hudson-Grant";
    	accountObj.setLastName(email);
    	assertEquals(email, accountObj.getLastName());
    }
    
    @Test
    public void userRolesTest() {
    	String role = "admin";
    	accountObj.setUserRole(role);
    	assertEquals(role, accountObj.getUserRole());
    }
    
    @Test
    public void fileTest() {
    	String fileName = "t";
    	byte[] testByte = fileName.getBytes();
    	Binary file = new Binary(testByte);
    	accountObj.setFile(file);
    	assertEquals(file, accountObj.getFile());
    }
    
    @Test
    public void _IDTest() {
    	ObjectId _id = new ObjectId();
    	accountObj.set_id(_id);
    	assertEquals(_id.toHexString(), accountObj.get_id());
    }
    
    @Test
    public void updateFirstNameTest() {
    	String newFName = "Edna";
    	String newLName = "";
    	String newEmail = "";
    	Account inAccount = new Account(newFName, newLName);
    	inAccount.setEmail(newEmail);
    	accountObj.updateFields(inAccount);
    	assertEquals(newFName, accountObj.getFirstName());
    }
    
    @Test
    public void updateLastNameTest() {
    	String newFName = "";
    	String newLName = "Mode";
    	String newEmail = "";
    	Account inAccount = new Account(newFName, newLName);
    	inAccount.setEmail(newEmail);
    	accountObj.updateFields(inAccount);
    	assertEquals(newLName, accountObj.getLastName());
    }
    @Test
    public void updateEmailTest() {
    	String newFName = "";
    	String newLName = "";
    	String newEmail = "edna@mode.com";
    	Account inAccount = new Account(newFName, newLName);
    	inAccount.setEmail(newEmail);
    	accountObj.updateFields(inAccount);
    	assertEquals(newEmail, accountObj.getEmail());
    }
    
}