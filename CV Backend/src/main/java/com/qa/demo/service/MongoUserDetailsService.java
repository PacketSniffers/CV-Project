package com.qa.demo.service;

import java.util.*;

import com.qa.demo.Constants;
import com.qa.demo.NullChecker;
import com.qa.demo.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.qa.demo.domain.Account;
import com.qa.demo.repository.AccountRepository;

import static com.qa.demo.domain.Role.*;

@Component
public class MongoUserDetailsService implements UserDetailsService
{
	@Autowired
	  private AccountRepository repository;
	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Account user = repository.findByEmail(email);
	    if(NullChecker.NullChecker(user)) {
	      throw new UsernameNotFoundException("User not found");
	    }
	    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	    switch (user.getUserRole()) {
	        case user:
                authorities.add(new SimpleGrantedAuthority(Constants.userRole));
                break;
			case admin:
				authorities.add(new SimpleGrantedAuthority(Constants.adminRole));
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
