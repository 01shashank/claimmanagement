package com.hexaware.claimmanagement.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;

@Service
@Transactional
public interface UserService {
	
	public User saveUser(User user) ;
	
	public User saveAdmin(User user) ;
	
	public List<User> getAllUsers();
	
	public ResponseEntity<?> getUserbyId(int user_Id);
	
	public ResponseEntity<?> deleteUser(int user_Id) ;
	
	public ResponseEntity<?> getUserClaims(int user_Id);
	
	public Collection<? extends GrantedAuthority> getUserAuthorities(String username);

}
