package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;

@Service
public interface UserService {
	
	public User saveUser(User user);
	
	public List<User> getAllUsers();
	
	public User deleteUser(int user_Id);
	
	public User getUserbyId(int user_Id);
	
	public User getUserbyEmail(String userEmail);


}
