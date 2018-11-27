package com.qa.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.relevantcodes.extentreports.LogStatus;
import com.qa.demo.tests.TestFunctions;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginPageTester extends BaseTest
{
	private String searchTerm = "password";
	
	@Test
	public void accessHomePage() 
	{
		test = report.startTest("Finding homepage");
		TestFunctions testFunctions = PageFactory.initElements(driver, TestFunctions.class);
		
		test.log(LogStatus.INFO, "Connecting to host");
		driver.get(websiteURL);
		
		if(testFunctions.CheckPageElementExists(searchTerm, driver))
		{
			test.log(LogStatus.PASS, "Element " + searchTerm + " found");
		}
		else
		{
			test.log(LogStatus.FAIL,  "Element " + searchTerm + " not found");
		}
		
		assertEquals(true, testFunctions.CheckPageElementExists(searchTerm, driver));
		
	}
	
	@Test
	public void AccessAccountCreation()
	{
		test = report.startTest("Create Account");
		TestFunctions testFunctions = PageFactory.initElements(driver, TestFunctions.class);
		
		driver.get(websiteURL);
		
		test.log(LogStatus.INFO, "Accessing account creation");
		
		if(testFunctions.FollowTextLink("Create account", driver))
		{
			test.log(LogStatus.PASS, "Link successfully followed");
		}
		else
		{
			test.log(LogStatus.FAIL, "Link not found, Link is either broken or missing");
		}
		
		if(driver.getCurrentUrl().equals(websiteURL + "/createuser"))
		{
			test.log(LogStatus.PASS, "Account creation page loaded");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account creation page failed to load or incorrect");
		}
		
		assertEquals(websiteURL + "/createuser", driver.getCurrentUrl());		
	}

	
}
