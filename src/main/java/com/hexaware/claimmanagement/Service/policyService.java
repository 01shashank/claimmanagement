package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.policy;

@Service
public interface policyService {
	
	public policy savePolicy(policy policy);
	
	public List<policy> getAllPolicies();
	
	public ResponseEntity<policy> updatePolicy(int policy_name,policy policy);
	
	public policy deletePolicy(int policy_Id);
	

}
