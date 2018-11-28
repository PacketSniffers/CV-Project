package com.qa.demo.tests.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestFunctions {
    public boolean CheckPageElementExists(By element, WebDriver driver)
    {
        try
        {
            driver.findElement(element);
        }
        catch(Exception e)
        {
            return false;
        }

        return true;
    }

    public boolean FollowTextLink(String text, WebDriver driver)
    {
        WebElement linkText;
        try
        {
            linkText = driver.findElement(By.linkText(text));
        }
        catch(Exception e)
        {
            return false;
        }

        linkText.click();

        return true;
    }

    public boolean InputIntoField(String fieldName, String textForField, WebDriver driver)
    {
        WebElement inputField;
        try
        {
            inputField = driver.findElement(By.name(fieldName));
        }
        catch(Exception e)
        {
            return false;
        }

        inputField.sendKeys(textForField);

        return true;
    }
    
    public String GetPageElementText(By element, WebDriver driver)
    {
    	WebElement webElement;
    	
    	try
    	{
    		webElement = driver.findElement(element);
    	}
    	catch(Exception e)
    	{
    		return "Element not found";
    	}
    	
    	return webElement.getText();
    }
}
