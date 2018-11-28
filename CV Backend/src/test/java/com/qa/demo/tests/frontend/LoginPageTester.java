package com.qa.demo.tests.frontend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.demo.tests.utility.TestFunctions;
import com.relevantcodes.extentreports.LogStatus;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginPageTester extends BaseTest
{
    @Test
    public void accessHomePage()
    {
        test = report.startTest("Finding homepage");
        TestFunctions testFunctions = PageFactory.initElements(driver, TestFunctions.class);

        test.log(LogStatus.INFO, "Connecting to host");
        driver.get(constVariables.getWebsiteURL());

        if(testFunctions.CheckPageElementExists(By.name("password"), driver))
        {
            test.log(LogStatus.PASS, "password found");
        }
        else
        {
            test.log(LogStatus.FAIL,  "password not found");
        }

        assertEquals(true, testFunctions.CheckPageElementExists(By.name("password"), driver));

    }

    @Test
    public void AccessAccountCreation()
    {
        test = report.startTest("Create Account");
        TestFunctions testFunctions = PageFactory.initElements(driver, TestFunctions.class);

        driver.get(constVariables.getWebsiteURL());

        test.log(LogStatus.INFO, "Accessing account creation");

        if(testFunctions.FollowTextLink("Create account", driver))
        {
            test.log(LogStatus.PASS, "Link successfully followed");
        }
        else
        {
            test.log(LogStatus.FAIL, "Link not found, Link is either broken or missing");
        }

        if(driver.getCurrentUrl().equals(constVariables.getWebsiteURL() + "/createuser"))
        {
            test.log(LogStatus.PASS, "Account creation page loaded");
        }
        else
        {
            test.log(LogStatus.FAIL, "Account creation page failed to load or incorrect");
        }

        assertEquals(constVariables.getWebsiteURL() + "/createuser", driver.getCurrentUrl());
    }


}
