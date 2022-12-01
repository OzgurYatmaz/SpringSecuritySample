package com.sample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.auth.ApplicationUser;
import com.sample.entity.User;
import com.sample.repository.UserRepository;

@Service
public class ApplicationUserService implements UserDetailsService {

	private final UserRepository userRepository;
	
	
	@Autowired
	public ApplicationUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		 Optional<User> user= userRepository.findUserByUserName(userName);
		 
		 user.orElseThrow(() -> new UsernameNotFoundException("no user is found with user name "+ userName));
		 
		 return user.map(ApplicationUser::new).get();
	}
 

}
