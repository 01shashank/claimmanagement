package com.hexaware.claimmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.policy;
import com.hexaware.claimmanagement.Repository.policyRepository;

@Component
public class policyServiceImpl implements policyService{
	
	@Autowired
	private policyRepository polRepo;
	
	@Override
	public policy savePolicy(policy policy) {
		// TODO Auto-generated method stub
		
		return polRepo.save(policy);
	}


	@Override
	public policy updatePolicyName(String policyName, String changedName) {
		
		List<policy> po1 = polRepo.findBypolicyName(policyName);
		po1.forEach(policy->{
			policy.setPolicy_name(changedName);
			polRepo.save(policy);
		});
		return null;
		
	}


	@Override
	public policy deletePolicy(String policyName) {
		// TODO Auto-generated method stub
		List<policy> po1 = polRepo.findBypolicyName(policyName);
		po1.forEach(policy->{
			polRepo.delete(policy);
		});
		return null;
	}

}
