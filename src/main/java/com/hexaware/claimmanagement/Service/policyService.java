package com.hexaware.claimmanagement.Service;

import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.policy;

@Service
public interface policyService {
	
	public policy savePolicy(policy policy);
	
	public policy updatePolicyName(String policyName,String changedName);
	
	public policy deletePolicy(String policyName);

}
