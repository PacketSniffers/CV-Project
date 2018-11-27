package com.qa.demo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.demo.tests.TestFunctions;
import com.relevantcodes.extentreports.LogStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountCreationTester extends BaseTest
{
	private String accountCreationURL = "/createuser";
	
	@Test
	public void AccessAccountCreationURL()
	{
		test = report.startTest("Accessing Account Creation Direct");
		test.log(LogStatus.INFO, "Connecting to host");
		List<String> elementNames = Arrays.asList("firstName", "lastName", "email", "password");
		boolean elementNotFound = false;
		
		TestFunctions testFunctions = PageFactory.initElements(driver, TestFunctions.class);		
		driver.get(websiteURL + accountCreationURL);
		
		for(int i = 0; i < elementNames.size(); i++)
		{
			if(testFunctions.checkPageElementExists(elementNames.get(i), driver))
			{
				test.log(LogStatus.PASS, "Element " + elementNames.get(i) + " found");
			}
			else
			{
				test.log(LogStatus.FAIL,  "Element " + elementNames.get(i) + " not found");
				elementNotFound = true;
				break;
			}
		}
		
		assertEquals(false, elementNotFound);
		
	}
}
