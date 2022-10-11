package com.hexaware.claimmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.claimRepository;
import com.hexaware.claimmanagement.Repository.userRepository;

@Component
public class userServiceImpl implements userService{
	
	@Autowired
	private userRepository userRepo;
	
	@Autowired
	private claimRepository claimRepo;

	@Override
	public User saveUser(User user1) {
		List<Claim> list1= user1.getUser_claims();
		
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
		
			user3.setUser_first_name(user1.getUser_first_name());
			user3.setUsre_last_name(user1.getUsre_last_name());
			user3.setUser_policies(user1.getUser_policies());
			user3.setUser_claims(user1.getUser_claims());
			
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
				
				List<Claim> claim1 =user2.getUser_claims();
				claim1.forEach(claim->{
					claimRepo.delete(claim);
				});
				
				
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
