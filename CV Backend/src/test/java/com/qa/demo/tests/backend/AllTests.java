package com.qa.demo.tests.backend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountControllerTests.class, AccountTest.class, MongoUserDetailsServiceTest.class })
public class AllTests {

}
