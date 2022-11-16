package com.hexaware.claimmanagement.Controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Repository.PolicyRepository;
import com.hexaware.claimmanagement.Service.PolicyService;

@RestController
@CrossOrigin(origins = "http://localhost:4202")
public class PolicyController {
	
	@Autowired
	private PolicyRepository polRepo;
	
	@Autowired
	private PolicyService polserv;
	
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
	
	@GetMapping("/getpolicybyid/{policy_Id}")
	public ResponseEntity<Policy> getPolicyById(@PathVariable int policy_Id){
		Policy policy = polserv.getPolicyById(policy_Id);
		if(policy==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
		return ResponseEntity.of(Optional.of(policy));
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
	



}
