package com.hexaware.claimmanagement.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hexaware.claimmanagement.Entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.ExceptionHandling.ResourceNotFoundException;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.UserRepository;

@Component
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired UserRepository userRepo;
	@Autowired PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user1){
		
		String pass= user1.getUser_password();
		String pass2= passwordEncoder.encode(pass);
		System.out.println(this.passwordEncoder.encode(pass2));
		user1.setUser_password(pass2);
		
		
		Set<Role> userRoles = new HashSet<Role>();
		userRoles.add(userRepo.getUserRole());
		user1.setUser_roles(userRoles);
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
	public User deleteUser(int user_Id){
		
		try {
			Optional<User> user1 = userRepo.findById(user_Id);
			User user3 = user1.get();
			if(user3==null){
				throw new ResourceNotFoundException("No user present");
			}
			else {
				
				userRepo.delete(user3);
				return user3;
			}}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public User getUserbyId(int user_Id) {
		Optional<User> user1=userRepo.findById(user_Id);
		User user2 =user1.get();
		return user2;
	}



	@Override
	public List<Claim> getUserClaims(int user_Id) {
		return userRepo.getUserClaims(user_Id);
	}



	@Override
	public User updateUser(int user_Id,User user) {
		
		Optional<User> user1 = userRepo.findById(user_Id);
		User user2 = user1.get();
		user2.setUser_Email(user.getUser_Email());
		
		String pass= user.getUser_password();
		String pass2= passwordEncoder.encode(pass);
		System.out.println(this.passwordEncoder.encode(pass2));
		user2.setUser_password(pass2);
		
		user2.setUser_first_name(user.getUser_first_name());
		user2.setUser_last_name(user.getUser_last_name());
		
		return userRepo.save(user2);
		
	}
}
