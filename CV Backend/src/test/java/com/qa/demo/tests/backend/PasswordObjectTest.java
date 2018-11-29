package com.qa.demo.tests.backend;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.demo.NullChecker;
import com.qa.demo.domain.Account;
import com.qa.demo.domain.PasswordObject;
import com.relevantcodes.extentreports.LogStatus;

public class PasswordObjectTest extends BackendBaseTest{

    @Test
    public void setOldPasswordTest() {
    	test = report.startTest("Accessing Account Creation Direct");
        test.log(LogStatus.INFO, "Connecting to host");
    	String oldPass = "pass123";
    	PasswordObject passwordObj = new PasswordObject();
    	test.log(LogStatus.INFO, "Created new Password Object and Password");
    	passwordObj.setOldPassword(oldPass);
    	test.log(LogStatus.INFO, "Attempted to set Old Password");
    	if(oldPass.equals(passwordObj.getOldPassword())) {
	  		test.log(LogStatus.PASS, "Old Password successfully set");
	  	} else {
	  		test.log(LogStatus.FAIL, "Old Password failed to set");
	  	}
    	assertEquals(oldPass, passwordObj.getOldPassword());
    }

    @Test
    public void setNewPasswordTest() {
    	test = report.startTest("Accessing Account Creation Direct");
        test.log(LogStatus.INFO, "Connecting to host");
    	String newPass = "new123";
    	PasswordObject passwordObj = new PasswordObject(newPass, newPass);
    	test.log(LogStatus.INFO, "Created new Password Object and Password");
    	passwordObj.setNewPassword(newPass);
    	test.log(LogStatus.INFO, "Attempted to set New Password");
    	if(newPass.equals(passwordObj.getNewPassword())) {
	  		test.log(LogStatus.PASS, "New Password successfully set");
	  	} else {
	  		test.log(LogStatus.FAIL, "New Password failed to set");
	  	}
    	assertEquals(newPass, passwordObj.getNewPassword());
    }  
}
