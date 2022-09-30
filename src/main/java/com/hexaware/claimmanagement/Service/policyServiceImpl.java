package com.hexaware.claimmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<List<policy>> getAllPolicies(){
		List<policy> list1 = polRepo.findAll();
		if(list1.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
		else {
			List<policy> list2 = polRepo.findAll();
			return ResponseEntity.of(Optional.of(list2));
			
		}
		
	}
	

	@Override
	public policy updatePolicy(int policy_Id, policy policy) {
		
		try {
			Optional<policy> policy1 = polRepo.findById(policy_Id);
			policy policy2 = policy1.get(); 
			policy2.setPolicy_coverage(policy.getPolicy_coverage());
			policy2.setPolicy_name(policy.getPolicy_name());
			policy2.setPolicy_premium(policy.getPolicy_premium());
			
			polRepo.save(policy2);
			
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		return policy;
	}

	@Override
	public policy deletePolicy(int policy_Id) {
		Optional<policy> po1 = polRepo.findById(policy_Id);
		policy po2 = po1.get();
		
		polRepo.delete(po2);
		return po2;
	}


	

}
