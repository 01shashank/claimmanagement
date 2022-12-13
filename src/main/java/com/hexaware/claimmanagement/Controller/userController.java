package com.hexaware.claimmanagement.Controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.ExceptionHandling.ResourceNotFoundException;
import com.hexaware.claimmanagement.Repository.UserRepository;
import com.hexaware.claimmanagement.Service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/claimmanagement")
@Transactional
public class UserController {
	
	@Autowired UserService userServ;
	@Autowired UserRepository userRepo;
	
	
	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user1){
		return new ResponseEntity<>(userServ.saveUser(user1),HttpStatus.CREATED);
	}
	
	@PostMapping("/admin")
	public ResponseEntity<User> saveAdmin(@Valid @RequestBody User user1){
		return new ResponseEntity<>(userServ.saveAdmin(user1),HttpStatus.CREATED);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> list1 = userServ.getAllUsers();
		if(list1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.of(Optional.of(list1));
		}
	}
	
	@GetMapping("/user/{user_Id}")
	public User getUserbyId(@PathVariable int user_Id) {
		return userServ.getUserbyId(user_Id);
	}
	
	
	@DeleteMapping("/user/delete/{user_Id}")
	public User deleteUser(@PathVariable int user_Id) {
		
			User user1 = userServ.deleteUser(user_Id);
			return user1;
	}
	
	@GetMapping("/totalusers")
	public Long getUserCount(){
		return userRepo.count();
	}
	
	@GetMapping("/userclaims/{user_Id}")
	public List<Claim> getUserClaims(@PathVariable int user_Id){
		return userServ.getUserClaims(user_Id);
	}
	
	@PutMapping("/user/update/{user_Id}")
	public User updateUser(@PathVariable int user_Id,@RequestBody User user){
		return userServ.updateUser(user_Id,user);
	}
	
	@GetMapping("/useremail/{user_Email}")
	public User getByUserEmail(@PathVariable String user_Email) {
		return userRepo.findByuserEmail(user_Email);
	}
	
	

}
