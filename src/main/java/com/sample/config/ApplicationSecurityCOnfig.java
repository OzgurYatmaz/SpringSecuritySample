package com.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.sample.auth.ApplicationUserService;

import static com.sample.config.ApplicationUserPermission.*;
import static com.sample.config.ApplicationUserRole.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//to enable @Preauthorize anotation
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
	
	@Autowired
	private ApplicationUserService applicationUserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable()//additional securşty with token for browsers is removed
			.authorizeHttpRequests()
			.antMatchers(AUTH_WHITELIST).permitAll()//white listed Urls
			.antMatchers("/api/**").hasRole(STUDENT.name())//only students can reach that path
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails user1=User.builder()
//				.username("Ozgur")
//				.password(passwordEncoder.encode("571"))
//		//		.roles(STUDENT.name())
//				.authorities(STUDENT.getGrantedAuthorities())
//				.build();
//		
//		UserDetails user2=User.builder()
//				.username("Muhammed")
//				.password(passwordEncoder.encode("622"))
//			//	.roles(ADMIN.name())
//				.authorities(ADMIN.getGrantedAuthorities())
//				.build();
//		
//		UserDetails user3=User.builder()
//				.username("Abdul Kadr")
//				.password(passwordEncoder.encode("632"))
//			//	.roles(ADMINTRAINEE.name())
//				.authorities(ADMINTRAINEE.getGrantedAuthorities())
//				.build();
//		return new InMemoryUserDetailsManager(user1, user2,user3);
//	}

}
