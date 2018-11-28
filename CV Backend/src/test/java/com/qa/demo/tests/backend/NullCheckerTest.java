package com.qa.demo.tests.backend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.qa.demo.NullChecker;
import com.qa.demo.domain.Account;

public class NullCheckerTest {
	
	  @Test
	    public void nullCheckTest() {
		  	Account accountObj = new Account();
	    
	    	assertEquals(false, NullChecker.NullChecker(accountObj));
	    }
}
