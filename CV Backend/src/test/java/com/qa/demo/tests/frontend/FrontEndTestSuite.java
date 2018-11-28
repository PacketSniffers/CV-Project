package com.qa.demo.tests.frontend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountCreationTester.class, LoginPageTester.class , LoginTester.class})
public class FrontEndTestSuite {

}