package com.qa.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestFunctions 
{
	
	public boolean checkLoginPageElementExists(String elementName, WebDriver driver)
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
	
	public boolean followTextLink(String text, WebDriver driver)
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
}
