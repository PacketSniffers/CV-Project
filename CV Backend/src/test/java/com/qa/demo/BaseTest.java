package com.qa.demo;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest
{
	public WebDriver driver = null;
	static private int runs = 0;
	public static ExtentReports report;
	public static ExtentTest test;
	protected static String websiteURL = "http://localhost:3000";
	
	@Before
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		if(runs < 1)
		{
			report = new ExtentReports("src/main/resources/selenium-reports/AssessmentReport.html", true);
		}
		runs++;
	}
	
	@After
	public void cleanUp()
	{
		driver.close();
	}
}
