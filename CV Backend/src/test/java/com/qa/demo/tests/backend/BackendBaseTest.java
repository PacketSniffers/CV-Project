package com.qa.demo.tests.backend;

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

public class BackendBaseTest
{
    static private int runs = 0;
    public static ExtentReports report;
    public static ExtentTest test;
    protected static EnumConstantVariable constVariables;

    @BeforeClass
    public static void initialiser()
    {
    	constVariables = EnumConstantVariable.GetConstantVariables(LoginTag.joeBlogs);
    }

    @Before
    public void setUp()
    {
        if(runs < 1)
        {
            report = new ExtentReports("src/main/resources/selenium-reports/AssessmentReport.html", true);
        }
        runs++;
    }

    @After
    public void cleanUp()
    {
        report.flush();
        report.endTest(test);
    }

    @AfterClass
    public static void tearDown()
    {
    }

}