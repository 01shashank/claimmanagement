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

import com.hexaware.claimmanagement.Entity.policy;
import com.hexaware.claimmanagement.Repository.policyRepository;
import com.hexaware.claimmanagement.Service.policyService;

@RestController
public class policyController {
	
	@Autowired
	private policyRepository polRepo;
	
	@Autowired
	private policyService polserv;
	
	@PostMapping("/savepolicy")
	public policy savePolicy(@RequestBody policy policy) {
		return polserv.savePolicy(policy);
	}
	
	@GetMapping("/getallpolicies")
	public ResponseEntity<List<policy>> getAllPolicies(){
		return polserv.getAllPolicies();
	}
	
	@DeleteMapping("/deletepolicy/{policy_Id}")
	public policy deletePolicy(@PathVariable int policy_Id) {
		return polserv.deletePolicy(policy_Id);
		
	}
	
	@PutMapping("/updatepolicy/{policy_Id}")
	public ResponseEntity<policy> updatePolicy(@PathVariable int policy_Id, @RequestBody policy policy) {
		policy policy1 = polserv.updatePolicy(policy_Id, policy);
		if(policy1==null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
			return ResponseEntity.of(Optional.of(policy1));
		
	}




}
