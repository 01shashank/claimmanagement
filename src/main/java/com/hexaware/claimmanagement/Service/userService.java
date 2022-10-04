package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;

@Service
public interface userService {
	
	public User saveUser(User user);
	
	public List<User> getAllUsers();
	
	public User updateUser(int user_Id,User user1);
	
	public User deleteUser(int user_Id);


}
