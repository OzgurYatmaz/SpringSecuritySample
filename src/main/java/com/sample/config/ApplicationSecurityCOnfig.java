package com.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityCOnfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/","index","/css/*","/js/*").permitAll()//white listed Urls
			.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())//only students can reach that path
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails user1=User.builder()
				.username("Ozgur")
				.password(passwordEncoder.encode("571"))
				.roles(ApplicationUserRole.STUDENT.name())
				.build();
		
		UserDetails user2=User.builder()
				.username("Muhammed")
				.password(passwordEncoder.encode("622"))
				.roles(ApplicationUserRole.ADMIN.name())
				.build();
		
		UserDetails user3=User.builder()
				.username("Abdul Kadr")
				.password(passwordEncoder.encode("632"))
				.roles(ApplicationUserRole.ADMINTRAINEE.name())
				.build();
		return new InMemoryUserDetailsManager(user1, user2,user3);
	}

}
