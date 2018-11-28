package com.qa.demo.tests.frontend;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

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
public class AccountCreationTester extends FrontendBaseTest
{
    private String accountCreationURL = "/createuser";
    private List<String> elementNames = Arrays.asList("firstName", "lastName", "email", "password");

    @Test
    public void AccessAccountCreationURL()
    {
        test = report.startTest("Accessing Account Creation Direct");
        test.log(LogStatus.INFO, "Connecting to host");

        boolean elementNotFound = false;

        TestFunctions testFunctions = PageFactory.initElements(driver, TestFunctions.class);
        driver.get(constVariables.getWebsiteURL()  + accountCreationURL);

        for(int i = 0; i < elementNames.size(); i++)
        {
            if(testFunctions.CheckPageElementExists(By.name(elementNames.get(i)), driver))
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

    @Test
    public void InputAccountCreationDetails()
    {
        test = report.startTest("Inputing account creation detailos");
        test.log(LogStatus.INFO, "Connecting to host");

        List<String> inputDetails = Arrays.asList(
        		constVariables.getFirstName(),
        		constVariables.getLastName(),
        		constVariables.getEmail(),
        		constVariables.getPassword()
        		);

        TestFunctions testFunctions = PageFactory.initElements(driver, TestFunctions.class);

        driver.get(constVariables.getWebsiteURL()  + accountCreationURL);

        for(int i = 0; i < elementNames.size(); i++)
        {
            if(testFunctions.InputIntoField(elementNames.get(i), inputDetails.get(i), driver))
            {
                test.log(LogStatus.PASS, "Detail " + elementNames.get(i) + " input successful");
            }
            else
            {
                test.log(LogStatus.FAIL, "Detail " + elementNames.get(i) + " input unsuccessful");
            }
        }

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/center/form/div[6]/button")).click();

        test.log(LogStatus.INFO, "Checking if creation successful");

        if(driver.getCurrentUrl().equals(constVariables.getWebsiteURL() + "/"))
        {
            test.log(LogStatus.PASS, "Account creation successful");
        }
        else
        {
            test.log(LogStatus.FAIL, "Account creation Failed");
        }

        assertEquals(constVariables.getWebsiteURL() + "/", driver.getCurrentUrl());

    }
}