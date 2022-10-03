package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.policy;
import com.hexaware.claimmanagement.Entity.user;

@Service
public interface userService {
	
	public user saveUser(user user);
	
	public List<user> getAllUsers();
	
	public user updateUser(int user_Id,user user1);
	
	public user deleteUser(int user_Id);


}
