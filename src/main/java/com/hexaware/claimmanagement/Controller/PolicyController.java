package com.hexaware.claimmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Service.PolicyService;

@RestController
@RequestMapping("/claimmanagement")
@CrossOrigin(origins = "http://localhost:3000")
public class PolicyController {
	
	@Autowired PolicyService polServ;
	
	@PostMapping("policy/{user_id}")
	public Policy savePolicy(@RequestBody Policy policy,@PathVariable int user_id) {
		return polServ.savePolicy(policy,user_id );
	}
	
	@GetMapping("/userpolicies/{user_id}")
	public List<Policy> getUserPolicies(@PathVariable int user_id) {
		return polServ.getUserPolicies(user_id );
	}
	
	@DeleteMapping("/deletepolicy/{policy_id}")
	public Policy deletePolicy(@PathVariable int policy_id) {
		return polServ.deletePolicy(policy_id);
	}
	
	@GetMapping("/policy/{policy_id}")
	public Policy getPolicyById(@PathVariable int policy_id) {
		return polServ.getPolicyById(policy_id );
	}

}
