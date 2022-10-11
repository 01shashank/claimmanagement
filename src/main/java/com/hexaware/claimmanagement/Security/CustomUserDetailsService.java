package com.hexaware.claimmanagement.Security;

import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.userRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private userRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		//Optional<User> userop =this.userRepo.findByEmail(username);
		User user = this.userRepo.findByuserEmail(username);
		return user;
	}

}
