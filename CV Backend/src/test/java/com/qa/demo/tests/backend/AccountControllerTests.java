package com.qa.demo.tests.backend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit4.SpringRunner;

import com.qa.demo.Interoperability.AccountController;
import com.qa.demo.domain.Account;
import com.qa.demo.repository.AccountRepository;
import com.relevantcodes.extentreports.LogStatus;

@RunWith(SpringRunner.class)
public class AccountControllerTests extends BackendBaseTest
{
	@Mock
	private AccountRepository accountRepositoryMock; //STOP MOCKING ME JUNIT
	
	@InjectMocks
	private AccountController accountController;
	
	@Test
    public void GettingAllAccounts()
    {
        test = report.startTest("Get all accounts");        
        test.log(LogStatus.INFO, "Connecting to host");
        List<Account> mockUserList = new ArrayList<Account>();
        mockUserList.add(new Account("John", "Doe"));
        
		when(accountRepositoryMock.findAll()).thenReturn(mockUserList);
		
		System.out.println(accountController.getAllAccounts());
		
		assertEquals(accountController.getAllAccounts().size(), 1);
		
		
        
       // System.out.println(accountController.createAccount(new Account("Joe", "Bloggs")));
       // System.out.println("--TEST OUTPUT-- " + accountController.getAllAccounts());

        //assertEquals(true, testFunctions.CheckPageElementExists(By.name("password"), driver));

    }
}
