package com.sample.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

	private final ApplicationUserDao userService;
	
	
	@Autowired
	public ApplicationUserService(@Qualifier("fakeRepo") 
								  ApplicationUserDao userService) {
		this.userService = userService;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userService
				.getApplicationUserByUserName(username)
				.orElseThrow(() 
						-> new UsernameNotFoundException("The user "+username+" is not found!"));
	}

}
