package com.qa.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.qa.demo.domain.Account;
import com.qa.demo.repository.AccountRepository;

@Component
public class MongoUserDetailsService implements UserDetailsService
{
	@Autowired
	  private AccountRepository repository;
	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Account user = repository.findByEmail(email);
	    if(user == null) {
	      throw new UsernameNotFoundException("User not found");
	    }
		  List<SimpleGrantedAuthority> authorities = null;
	    switch (user.getUserRole()) {
			case ("admin"):
				authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_admin"));
				break;
			case ("user"):
				authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_user"));
				break;
			default:
				System.out.println("wat");
			}
		  for(SimpleGrantedAuthority e: authorities){
		  	System.out.println(e);
		  }
		  return new User(user.getEmail(), user.getPassword( ), authorities);
	  }
}
