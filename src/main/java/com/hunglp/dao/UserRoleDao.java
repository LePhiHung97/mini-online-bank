package com.hunglp.dao;

import org.springframework.data.repository.CrudRepository;

import com.hunglp.domain.security.UserRole;

public interface UserRoleDao extends CrudRepository<UserRole, Long> {
	
}
