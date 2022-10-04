package com.hexaware.claimmanagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.userRepository;
import com.hexaware.claimmanagement.Service.userService;

@RestController
public class userController {
	
	@Autowired userService userServ;
	
	@PostMapping("/saveuser")
	public User saveUser(@RequestBody User user1){
		return userServ.saveUser(user1);
	}
	
	@GetMapping("/getallusers")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> list1 = userServ.getAllUsers();
		if(list1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
		return ResponseEntity.of(Optional.of(list1));
		}
	}
	
	@DeleteMapping("/deleteuser/{user_Id}")
	public ResponseEntity<User> deleteUser(@PathVariable int user_Id) {
		User user1 = userServ.deleteUser(user_Id);
		if(user1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.of(Optional.of(user1));
		}
	}
	
	@PutMapping("/updateuser/{user_Id}")
	public ResponseEntity<User> updatePolicy(@PathVariable int user_Id, @RequestBody User user) {
		User user1 = userServ.updateUser(user_Id, user);
		if(user1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		}
		else {
			return ResponseEntity.of(Optional.of(user1));
			}
		
	}
	
	
	
	

}
