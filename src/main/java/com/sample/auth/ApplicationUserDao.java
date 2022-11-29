package com.sample.auth;

import java.util.Optional;

public interface ApplicationUserDao {

	Optional<ApplicationUser> getApplicationUserByUserName(String UserName);
}
