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
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Repository.policyRepository;
import com.hexaware.claimmanagement.Service.policyService;

@RestController
public class policyController {
	
	@Autowired
	private policyRepository polRepo;
	
	@Autowired
	private policyService polserv;
	
	@PostMapping("/savepolicy")
	public Policy savePolicy(@RequestBody Policy policy) {
		return polserv.savePolicy(policy);
	}
	
	@GetMapping("/getallpolicies")
	public ResponseEntity<List<Policy>> getAllPolicies(){
		List<Policy> list1 = polserv.getAllPolicies();
		if(list1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
		return ResponseEntity.of(Optional.of(list1));
		}
	}
	
	@DeleteMapping("/deletepolicy/{policy_Id}")
	public ResponseEntity<Policy> deletePolicy(@PathVariable int policy_Id) {
		Policy policy1 = polserv.deletePolicy(policy_Id);
		if(policy1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.of(Optional.of(policy1));
		}
	}
	
	@PutMapping("/updatepolicy/{policy_Id}")
	public ResponseEntity<Policy> updatePolicy(@PathVariable int policy_Id, @RequestBody Policy policy) {
		Policy policy1 = polserv.updatePolicy(policy_Id, policy);
		if(policy1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		}
		else {
			return ResponseEntity.of(Optional.of(policy));
			}
		
	}




}
