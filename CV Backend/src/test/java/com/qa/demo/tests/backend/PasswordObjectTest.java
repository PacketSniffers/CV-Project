package com.qa.demo.tests.backend;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.demo.domain.Account;
import com.qa.demo.domain.PasswordObject;

public class PasswordObjectTest {

    @Test
    public void setOldPasswordTest() {
    	String oldPass = "pass123";
    	PasswordObject passwordObj = new PasswordObject();
    	passwordObj.setOldPassword(oldPass);
    	assertEquals(oldPass, passwordObj.getOldPassword());
    }

    @Test
    public void setNewPasswordTest() {
    	String newPass = "new123";
    	PasswordObject passwordObj = new PasswordObject(newPass, newPass);
    	passwordObj.setNewPassword(newPass);
    	assertEquals(newPass, passwordObj.getNewPassword());
    }
    
    
}
