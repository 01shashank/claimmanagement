package com.hexaware.claimmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/policysave")
	public policy savePolicy(@RequestBody policy policy) {
		return polserv.savePolicy(policy);
	}
	
	@GetMapping("/getallpolicies")
	public List<policy> getAllPolicies(){
		return polRepo.findAll();
	}

}
