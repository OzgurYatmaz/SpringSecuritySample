package com.sample.auth;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static com.sample.config.ApplicationUserRole.*;
import com.google.common.collect.Lists;

@Repository("fakeRepo")
public class FakeApplicationUserDaoImpl implements ApplicationUserDao {

	private Logger logger =Logger.getLogger(FakeApplicationUserDaoImpl.class);
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public FakeApplicationUserDaoImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public Optional<ApplicationUser> getApplicationUserByUserName(String userName) {
		logger.info("User "+ userName+" is being checked in db");
		System.out.println("User "+ userName+" is being checked in db");
		return getApplicationUsers()
				.stream()
				.filter(u -> userName.equals(u.getUsername()))
				.findFirst();
	}
	
	private List<ApplicationUser> getApplicationUsers(){
		List<ApplicationUser> aplicationUsers=Lists.newArrayList(new ApplicationUser("Ozgur", passwordEncoder.encode("571"), STUDENT.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("Muhammed", passwordEncoder.encode("622"), ADMIN.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("Abdul Kadr", passwordEncoder.encode("632"), ADMINTRAINEE.getGrantedAuthorities(), true, true, true, true));
		
		return aplicationUsers;
	}

}
