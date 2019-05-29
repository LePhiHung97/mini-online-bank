package com.hunglp.dao;

import com.hunglp.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.email = :email")
	User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE username = :username AND password = :password")
	User findByUsernamePassword(String username, String password);

	@Query("SELECT u.salt FROM User u WHERE u.username = :username")
	String getSaltByUsername(String username);

}
