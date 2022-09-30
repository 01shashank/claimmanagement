package com.hexaware.claimmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.policy;
import com.hexaware.claimmanagement.Repository.policyRepository;

@Component
public class policyServiceImpl implements policyService{
	
	@Autowired
	private policyRepository polRepo;
	
	@Override
	public policy savePolicy(policy policy) {
		return polRepo.save(policy);
	}
	
	@Override
	public List<policy> getAllPolicies(){
		return polRepo.findAll();
	}
	

	@Override
	public ResponseEntity<policy> updatePolicy(int policy_Id, policy policy) {
		Optional<policy> po1 = polRepo.findById(policy_Id).orElseThrow(()->);
		policy po2 = po1.get();
		po2.setPolicy_coverage(policy.getPolicy_coverage());
		po2.setPolicy_name(policy.getPolicyName());
		po2.setPolicy_premium(policy.getPolicy_premium());
		
		polRepo.save(po2);
		return po2;
	}


	@Override
	public policy deletePolicy(int policy_Id) {
		Optional<policy> po1 = polRepo.findById(policy_Id);
		policy po2 = po1.get();
		
		polRepo.delete(po2);
		return po2;
	}


	

}
