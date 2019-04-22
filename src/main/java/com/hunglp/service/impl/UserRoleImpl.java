package com.hunglp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hunglp.dao.UserRoleDao;
import com.hunglp.domain.security.UserRole;
import com.hunglp.service.UserRoleService;


@Service
public class UserRoleImpl implements UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public void save(UserRole userRole) {
		userRoleDao.save(userRole);
		
	}
	
}
