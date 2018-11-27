package com.qa.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountCreationTester.class, LoginPageTester.class })
public class FrontEndTestSuite {

}
