package com.hexaware.claimmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.userRepository;

@Component
public class userServiceImpl implements userService{
	
	@Autowired
	private userRepository userRepo;

	@Override
	public User saveUser(User user1) {
		return userRepo.save(user1);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list1 = userRepo.findAll();
		if(list1.size()<=0) {
			return null;	
		}
		else {
			return list1;
		}
	}

	@Override
	public User updateUser(int user_Id, User user1) {
		try {
			Optional<User> user2 = userRepo.findById(user_Id);
			if(user1==null) {
				throw new Exception();

			}
			else {
			User user3 = user2.get(); 
			
			user3.setUser_Id(user1.getUser_Id());
			user3.setUser_first_name(user1.getUser_first_name());
			user3.setUsre_last_name(user1.getUsre_last_name());
			user3.setUser_phone(user1.getUser_phone());
			user3.setUser_age(user1.getUser_age());
			user3.setUser_address(user1.getUser_address());
			user3.setUser_policies(user1.getUser_policies());
			
			userRepo.save(user3);
			return user3;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public User deleteUser(int user_Id) {
		try {
			Optional<User> user1 = userRepo.findById(user_Id);
			if(user1==null){
				throw new Exception();
			}
			else {
				User user2 = user1.get();
				userRepo.delete(user2);
				return user2;
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

}