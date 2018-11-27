package com.qa.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.demo.tests.TestFunctions;
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
        driver.get(websiteURL);        
        TestFunctions testFunctions = PageFactory.initElements(driver, TestFunctions.class);
        
        if(testFunctions.InputIntoField("email", "JohnDoe@Email.com", driver))
        {
        	test.log(LogStatus.PASS, "Email entered");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Failed to input email");
        }
        
        if(testFunctions.InputIntoField("password", "Password123", driver))
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
        
        if(driver.getCurrentUrl().contains("/userprofile?id="))
        {
        	test.log(LogStatus.PASS, "Login successful");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Login failed");
        }
        
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println(driver.getCurrentUrl());
        assertEquals(true, driver.getCurrentUrl().contains("/userprofile?id="));
    }
}