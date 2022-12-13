package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.Role;

@Service
public interface RoleService {
	
	public Role saveRole(Role role);
	
	public List<Role> getAllRoles();

}
