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
	public ResponseEntity<?> deleteUser(int user_Id){
		Optional<User> userOp = userRepo.findById(user_Id);
		User user = userOp.get();
		if(user==null) {
			throw new ResourceNotFoundException("No user present with that id");
		}
		else {
			userRepo.delete(user);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		
		
	}

	@Override
	public ResponseEntity<?> getUserbyId(int user_Id) {
		Optional<User> userOp = userRepo.findById(user_Id);
		User user = userOp.get();
		if(user==null) {
			throw new ResourceNotFoundException("No user present with that id");
		}
		else {
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		
	}



	@Override
	public ResponseEntity<?> getUserClaims(int user_Id) {
		List<Claim> user_claims_list =  userRepo.getUserClaims(user_Id);
		if(user_claims_list==null) {
			return new ResponseEntity<>("No claims found",HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(user_claims_list,HttpStatus.OK);
		}
	}



	@Override
	public Collection<? extends GrantedAuthority> getUserAuthorities(String username) {
		User user = userRepo.findByuserEmail(username);
		return user.getAuthorities();
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
}
