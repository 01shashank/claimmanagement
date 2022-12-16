package com.hexaware.claimmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.Role;
import com.hexaware.claimmanagement.Service.RoleService;

@RestController
@RequestMapping("/claimmanagement")
@CrossOrigin(origins = "http://localhost:3002")
public class RoleController {
	
	@Autowired private RoleService roleServ;
	
	@PostMapping("/admin/addrole")
	public Role saveRole(@RequestBody Role role) {
		return roleServ.saveRole(role);
	}
	
	@GetMapping("/admin/allroles")
	public List<Role> getAllRoles(){
		return roleServ.getAllRoles();
	}
}
