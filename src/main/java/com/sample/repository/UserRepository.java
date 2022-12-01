package com.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByUserName(String userName);
}
