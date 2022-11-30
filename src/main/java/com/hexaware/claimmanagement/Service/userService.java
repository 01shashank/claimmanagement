package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;

@Service
public interface UserService {
	
	public User saveUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUserbyId(int user_Id);
	
	public User deleteUser(int user_Id) ;
	
	public List<Claim> getUserClaims(int user_Id);
	
	public User updateUser(int user_Id,User user);
	


}
