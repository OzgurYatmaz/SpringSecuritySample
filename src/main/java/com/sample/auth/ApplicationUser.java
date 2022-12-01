package com.sample.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sample.config.ApplicationUserRole;
import com.sample.entity.User;

public class ApplicationUser implements UserDetails {

	private final Set<SimpleGrantedAuthority> grantedAuthorities;
	private final String username;
	private final String password;
	private final boolean isAccountNonExpired;
	private final boolean isAccountNonLocked;
	private final boolean isCredentialsNonExpired;
	private final boolean isEnabled;
	
	
	public ApplicationUser(User user) {
//		this.grantedAuthorities =Arrays.stream(user.getRoles().split(","))
//									.map(SimpleGrantedAuthority::new)
//									.collect(Collectors.toSet());
		this.grantedAuthorities =ApplicationUserRole.valueOf(user.getRoles()).getGrantedAuthorities();
				 
		this.username = user.getUserName();
		this.password = user.getPassword();
		this.isAccountNonExpired = user.isActive();
		this.isAccountNonLocked = user.isActive();
		this.isCredentialsNonExpired = user.isActive();
		this.isEnabled = user.isActive();
	}

	 

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
