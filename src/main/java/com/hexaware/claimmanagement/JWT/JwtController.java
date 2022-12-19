package com.hexaware.claimmanagement.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.ExceptionHandling.ResourceNotFoundException;
import com.hexaware.claimmanagement.Security.*;

@RestController
@RequestMapping("/claimmanagement")
@CrossOrigin(origins = "http://localhost:3000")
public class JwtController {
	
	@Autowired  AuthenticationManager authManager;
	
	@Autowired private JwtUtils jwtUtils;
	
	@Autowired private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest)throws Exception{
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		}
		catch(Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		UserDetails userdetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userdetails);
		System.out.println("JWT token: "+token);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username , String password) throws Exception{
		
		try {
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			
		}
		catch(Exception e) {
			throw new Exception("Username or Password is incorrect");
		}
		
	}

}
