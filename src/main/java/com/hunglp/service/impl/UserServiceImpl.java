package com.hunglp.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hunglp.dao.RoleDao;
import com.hunglp.dao.UserDao;
import com.hunglp.domain.User;
import com.hunglp.domain.security.UserRole;
import com.hunglp.security.HashHelper;
import com.hunglp.service.AccountService;
import com.hunglp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder passwordEnCoder;

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public boolean checkUserExists(String username, String email) {
		if (checkUserNameExists(username) || checkEmailExists(email))
			return true;
		return false;
	}

	@Override
	public boolean checkUserNameExists(String username) {
		if (findByUsername(username) != null)
			return true;
		return false;
	}

	@Override
	public boolean checkEmailExists(String email) {
		if (findByEmail(email) != null)
			return true;
		return false;
	}

	@Override
	public void save(User user) {
		// Ma hoa password
		String encryptedPassword = "";
		byte[] salt;
		try {
			salt = HashHelper.getSalt();
			encryptedPassword = HashHelper.getSecurePassword(user.getPassword(), salt);
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			
			e.printStackTrace();
		}
		user.setPassword(encryptedPassword);

		// Set Role
		Set<UserRole> userRoles = new HashSet<UserRole>();
		userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
		user.setUserRoles(userRoles);

		// Set Account-type : primary & savings
		user.setSavingsAccount(accountService.createSavingsAccount());
		user.setPrimaryAccount(accountService.createPrimaryAccount());

		userDao.save(user);
	}

	@Override
	public User findByUsernamePassword(String username, String password,String salt) {
		return userDao.findByUsernamePassword(username, password, salt);
	}

	@Override
	public String getSaltByUsername(String username) {
		return userDao.getSaltByUsername(username);
	}

}
