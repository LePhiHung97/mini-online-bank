package com.hunglp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hunglp.domain.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer>{
	
	@Query("SELECT r FROM Role r WHERE name = :name")
	Role findByName(String name);
}
