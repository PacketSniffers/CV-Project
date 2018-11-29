package com.qa.demo.tests.backend;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.demo.domain.Account;
import com.qa.demo.domain.Role.Roles;
import com.qa.demo.repository.AccountRepository;
import com.qa.demo.service.MongoUserDetailsService;
import com.relevantcodes.extentreports.LogStatus;

@RunWith(SpringRunner.class)
public class MongoUserDetailsServiceTest extends BackendBaseTest {
	@Mock
	private AccountRepository accountRepositoryMock; // STOP MOCKING ME JUNIT

	@InjectMocks
	private MongoUserDetailsService mongoUserDetailsService;

	@Test
	public void loadUserByUsernameTest() {
		test = report.startTest("Load user by username test");
		List<Account> mockUserList = new ArrayList<Account>();
		Account accountObj = new Account("John", "Doe");

		when(accountRepositoryMock.findByEmail("brad@mail.com")).thenReturn(accountObj);

		accountObj.setEmail("brad@mail.com");
		accountObj.setPassword("Password123");
		accountObj.setUserRole(Roles.user);
		mockUserList.add(accountObj);
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_user"));

		if(new User(accountObj.getEmail(), accountObj.getPassword(), authorities) == mongoUserDetailsService.loadUserByUsername(accountObj.getEmail()))
		{
			test.log(LogStatus.PASS, "User successfully retrieved");
		}
		assertEquals(new User(accountObj.getEmail(), accountObj.getPassword(), authorities),
				mongoUserDetailsService.loadUserByUsername(accountObj.getEmail()));
	}

	@Test
	public void loadUserByUsernameAdminTest() {
		test = report.startTest("Get all accounts");
		test.log(LogStatus.INFO, "Connecting to host");
		List<Account> mockUserList = new ArrayList<Account>();
		Account accountObj = new Account("John", "Doe");

		when(accountRepositoryMock.findByEmail("brad@mail.com")).thenReturn(accountObj);

		accountObj.setEmail("brad@mail.com");
		accountObj.setPassword("Password123");
		accountObj.setUserRole(Roles.admin);
		mockUserList.add(accountObj);
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_admin"));
		;

		assertEquals(new User(accountObj.getEmail(), accountObj.getPassword(), authorities),
		mongoUserDetailsService.loadUserByUsername(accountObj.getEmail()));
	} 

	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsernameNullTest() {
		test = report.startTest("Get all accounts");
		test.log(LogStatus.INFO, "Connecting to host");
		List<Account> mockUserList = new ArrayList<Account>();
		Account accountObj = new Account("John", "Doe");

		when(accountRepositoryMock.findByEmail("brad@mail.com")).thenReturn(accountObj);

		accountObj.setPassword("Password123");
		mockUserList.add(accountObj);
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_admin"));
		

		mongoUserDetailsService.loadUserByUsername(accountObj.getEmail());
	}


}
