package com.qa.demo.tests.frontend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.relevantcodes.extentreports.LogStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTester extends BaseTest
{

    @Test
    public void LoginTest()
    {
    	test = report.startTest("Logging in to service");
        test.log(LogStatus.INFO, "Connecting to host");
        driver.get(constVariables.getWebsiteURL());     
        
        Login();
        
        assertEquals(true, driver.getCurrentUrl().contains("/userprofile?id="));
    }
    
    @Test
    public void LoginVerify()
    {
    	test = report.startTest("Log in verification");
        test.log(LogStatus.INFO, "Connecting to host");
        driver.get(constVariables.getWebsiteURL());  
        
        Login();
        
        if(testFunctions.GetPageElementText(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div/div[2]/h5[1]"), driver)
        		.equals(constVariables.getFirstName() + " " + constVariables.getLastName()))
        {
        	test.log(LogStatus.PASS, "Profile page verified");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Incorrect page details, Expected Joe Bloggs but was instead " + 
        	testFunctions.GetPageElementText(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div/div[2]/h5[1]"), driver));
        }
        
        assertEquals(constVariables.getFirstName() + " " + constVariables.getLastName(),testFunctions.GetPageElementText(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div/div[2]/h5[1]"), driver));
        
    }
    
    public void Login()
    {
    	if(testFunctions.InputIntoField("email", constVariables.getEmail(), driver))
        {
        	test.log(LogStatus.PASS, "Email entered");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Failed to input email");
        }
        
        if(testFunctions.InputIntoField("password", constVariables.getPassword(), driver))
        {
        	test.log(LogStatus.PASS, "password entered");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Failed to input password");
        }
        
        test.log(LogStatus.INFO, "submiting login");
        
        try
        {
        	driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/center/form/div[4]/button")).click();
        	test.log(LogStatus.PASS, "Submited details");
        }
        catch(Exception e)
        {
        	test.log(LogStatus.FAIL, "Could not find submit button");
        }
        
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(driver.getCurrentUrl().contains("/userprofile?id="))
        {
        	test.log(LogStatus.PASS, "Login successful");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Login failed, actual page url is " + driver.getCurrentUrl());
        }
        
    }
}