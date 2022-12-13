package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.Role;
import com.hexaware.claimmanagement.Repository.RoleRepository;

@Component
public class RoleServiceImpl implements RoleService{
	
	@Autowired RoleRepository roleRepo;

	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}

}
