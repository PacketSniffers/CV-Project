package com.qa.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestFunctions {
    public boolean CheckPageElementExists(String elementName, WebDriver driver)
    {
        try
        {
            driver.findElement(By.name(elementName));
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

    public String InputAccountCreationDetails(String firstName, String lastName, String email, String password, WebDriver driver)
    {
        WebElement firstNameField;
        WebElement lastNameField;
        WebElement emailField;
        WebElement passwordField;

        try
        {
            firstNameField = driver.findElement(By.name("firstName"));
            lastNameField = driver.findElement(By.name("lastName"));
            emailField = driver.findElement(By.name("email"));
            passwordField = driver.findElement(By.name("password"));
        }
        catch(Exception e)
        {
            return "Field not found";
        }

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/center/form/div[6]/button")).click();
        return "Success";
    }
}
