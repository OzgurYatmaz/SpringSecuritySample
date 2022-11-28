package com.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.sample.config.ApplicationUserPermission.*;
import static com.sample.config.ApplicationUserRole.*;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityCOnfig extends WebSecurityConfigurerAdapter {
	
	private static final String[] AUTH_WHITELIST = {
			"/","index","/css/*","/js/*",
	        "/authenticate",
	        "/swagger-resources/**",
	        "/swagger-ui/**",
	        "/v3/api-docs/**",
	        "/webjars/**"
	};
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers(AUTH_WHITELIST).permitAll()//white listed Urls
			.antMatchers("/api/**").hasRole(STUDENT.name())//only students can reach that path
			.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
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
		//		.roles(STUDENT.name())
				.authorities(STUDENT.getGrantedAuthorities())
				.build();
		
		UserDetails user2=User.builder()
				.username("Muhammed")
				.password(passwordEncoder.encode("622"))
			//	.roles(ADMIN.name())
				.authorities(ADMIN.getGrantedAuthorities())
				.build();
		
		UserDetails user3=User.builder()
				.username("Abdul Kadr")
				.password(passwordEncoder.encode("632"))
			//	.roles(ADMINTRAINEE.name())
				.authorities(ADMINTRAINEE.getGrantedAuthorities())
				.build();
		return new InMemoryUserDetailsManager(user1, user2,user3);
	}

}
