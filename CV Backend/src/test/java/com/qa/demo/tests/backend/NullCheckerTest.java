package com.qa.demo.tests.backend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.qa.demo.NullChecker;
import com.qa.demo.domain.Account;
import com.relevantcodes.extentreports.LogStatus;

public class NullCheckerTest extends BackendBaseTest{
	
	  @Test
	    public void nullCheckTest() {
		  	test = report.startTest("Accessing Account Creation Direct");
	        test.log(LogStatus.INFO, "Connecting to host");
		  	Account accountObj = new Account();
		  	test.log(LogStatus.INFO, "Creating dummy Account");
		  	if(NullChecker.NullChecker(accountObj)) {
		  		test.log(LogStatus.FAIL, "Incorrectly assumed was null");
		  	} else {
		  		test.log(LogStatus.PASS, "Correctly realised not Null");
		  	}
	    	assertEquals(false, NullChecker.NullChecker(accountObj));
	    }
}
