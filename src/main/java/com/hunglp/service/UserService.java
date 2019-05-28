package com.hunglp.service;


import com.hunglp.domain.User;


public interface UserService {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	boolean checkUserExists(String username, String email);
	
	boolean checkUserNameExists(String username);
	
	boolean checkEmailExists(String email);
	
	void save(User user);
	
	User findByUsernamePassword(String username, String password);
	
	String getSaltByUsername(String username);

}
