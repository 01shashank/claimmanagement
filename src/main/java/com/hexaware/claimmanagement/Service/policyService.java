package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.Policy;

@Service
public interface PolicyService {
	
	public Policy savePolicy(Policy policy);
	
	public List<Policy> getAllPolicies();
	
	public Policy getPolicyById(int policy_id);
	
	//public Policy updatePolicy(int policy_Id,Policy policy);
	
	public Policy deletePolicy(int policy_Id);
	

}
