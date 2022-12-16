package com.hexaware.claimmanagement.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

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
	
	public User getUserbyId(int user_Id);
	
	public User deleteUser(int user_Id) ;
	
	public List<Claim> getUserClaims(int user_Id);
	
	public User updateUser(int user_Id,User user);
	
	public Collection<? extends GrantedAuthority> getUserAuthorities(String username);

}
