package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.Policy;

@Service
public interface PolicyService {
	
	public Policy savePolicy(Policy policy,int user_Id);
	
	public List<Policy> getUserPolicies(int user_Id);

	public Policy getPolicyById(int policy_id);
	
	public Policy deletePolicy(int policy_id);
	

}
