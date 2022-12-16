package com.hexaware.claimmanagement.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
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
@CrossOrigin(origins="http://localhost:3002")
@RequestMapping("/claimmanagement")
@Transactional
public class UserController {
	
	@Autowired UserService userServ;
	@Autowired UserRepository userRepo;
	
	
	@PostMapping("/adduser")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user1){
		return new ResponseEntity<>(userServ.saveUser(user1),HttpStatus.CREATED);
	}
	
	@PostMapping("/admin/addadmin")
	public ResponseEntity<User> saveAdmin(@Valid @RequestBody User user1){
		return new ResponseEntity<>(userServ.saveAdmin(user1),HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/allusers")
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

	@PutMapping("/admin/updateuser/{user_Id}")
	public User updateUser(@PathVariable int user_Id,@RequestBody User user){
		return userServ.updateUser(user_Id,user);
	}
	
	
	@DeleteMapping("/admin/deleteuser/{user_Id}")
	public User deleteUser(@PathVariable int user_Id) {
		
			User user1 = userServ.deleteUser(user_Id);
			return user1;
	}
	
	@GetMapping("/admin/totalusers")
	public Long getUserCount(){
		return userRepo.count();
	}
	
	@GetMapping("/user/userclaims/{user_Id}")
	public List<Claim> getUserClaims(@PathVariable int user_Id){
		return userServ.getUserClaims(user_Id);
	}
	
	
	@GetMapping("/user/useremail/{user_Email}")
	public User getByUserEmail(@PathVariable String user_Email) {
		return userRepo.findByuserEmail(user_Email);
	}
	
	@GetMapping("/user/authorities/{username}")
	public Collection<? extends GrantedAuthority> getUserAuthorities(@PathVariable String username){
		return userServ.getUserAuthorities(username);
	}
	
	@GetMapping("/getuserid/{username}")
	public int getUserId(@PathVariable String username) {
		User user = userRepo.findByuserEmail(username);
		if(user==null) {
			throw new ResourceNotFoundException("No user found");
		}
		else {
			return user.getUser_Id();
		}
	}
	
	

}
