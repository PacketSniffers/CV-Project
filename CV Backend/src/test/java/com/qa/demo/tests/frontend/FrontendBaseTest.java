package com.qa.demo.tests.frontend;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.demo.tests.utility.EnumConstantVariable;
import com.qa.demo.tests.utility.TestFunctions;
import com.qa.demo.tests.utility.EnumConstantVariable.LoginTag;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class FrontendBaseTest
{
    protected static WebDriver driver = null;
    static private int runs = 0;
    public static ExtentReports report;
    public static ExtentTest test;
    protected static EnumConstantVariable constVariables;
    protected TestFunctions testFunctions;

    @BeforeClass
    public static void initialiser()
    {
    	constVariables = EnumConstantVariable.GetConstantVariables(LoginTag.joeBlogs);
    }

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
        
        testFunctions = PageFactory.initElements(driver, TestFunctions.class);
    }

    @After
    public void cleanUp()
    {
        report.flush();
        report.endTest(test);
        driver.close();
    }

    @AfterClass
    public static void tearDown()
    {
        driver.quit();
    }

}