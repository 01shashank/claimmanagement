package com.hexaware.claimmanagement.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hexaware.claimmanagement.Entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
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
		String password = user1.getPassword();
		user1.setUser_password(passwordEncoder.encode(password));
		
		Set<Role> userRoles = new HashSet<Role>();
		userRoles.add(userRepo.getUserRole());
		user1.setUser_roles(userRoles);
		return userRepo.save(user1);
	}
	
	@Override
	public User saveAdmin(User user1){
		String password = user1.getPassword();
		user1.setUser_password(passwordEncoder.encode(password));
		
		Set<Role> userRoles = new HashSet<Role>();
		userRoles.add(userRepo.getAdminRole());
		user1.setUser_roles(userRoles);
		return userRepo.save(user1);
	}
	
	
		


	@Override
	public List<User> getAllUsers() {
		List<User> list1 = userRepo.findAll();
		if(list1.size()!=0) {
			return list1;
			
		}
		else {
			throw new ResourceNotFoundException("No Users present in the system");	
		}
		
	}


	@Override
	public User deleteUser(int user_Id){
		User user1 = userRepo.findById(user_Id).orElseThrow(()->new ResourceNotFoundException("No user present with that id"));
		userRepo.delete(user1);
		return user1;
		
	}

	@Override
	public User getUserbyId(int user_Id) {
		User user1=userRepo.findById(user_Id).orElseThrow(()->new ResourceNotFoundException("No user present with that id"));
		return user1;
	}



	@Override
	public List<Claim> getUserClaims(int user_Id) {
		return userRepo.getUserClaims(user_Id);
	}



	@Override
	public User updateUser(int user_Id,User user) {
		
		User user2 = userRepo.findById(user_Id).orElseThrow(()->new ResourceNotFoundException("No user present with that id"));
		user2.setUser_Email(user.getUser_Email());
		String password = user2.getPassword();
		user2.setUser_password(passwordEncoder.encode(password));
		
		user2.setUser_first_name(user.getUser_first_name());
		user2.setUser_last_name(user.getUser_last_name());
		
		return userRepo.save(user2);
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getUserAuthorities(String username) {
		User user = userRepo.findByuserEmail(username);
		return user.getAuthorities();
	}
}
