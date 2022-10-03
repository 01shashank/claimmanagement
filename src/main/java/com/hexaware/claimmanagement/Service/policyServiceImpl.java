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
	public List<policy> getAllPolicies(){
		List<policy> list1 = polRepo.findAll();
		if(list1.size()<=0) {
			return null;	
		}
		else {
			return list1;
		}
	}
	

	@Override
	public policy updatePolicy(int policy_Id, policy policy) {
		
		try {
			Optional<policy> policy1 = polRepo.findById(policy_Id);
			if(policy1==null) {
				throw new Exception();

			}
			else {
			policy policy2 = policy1.get(); 
			policy2.setPolicy_coverage(policy.getPolicy_coverage());
			policy2.setPolicy_name(policy.getPolicy_name());
			policy2.setPolicy_premium(policy.getPolicy_premium());
			
			polRepo.save(policy2);
			return policy2;
			}

			}
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public policy deletePolicy(int policy_Id) {
		try {
			Optional<policy> policy1 = polRepo.findById(policy_Id);
			if(policy1==null){
				throw new Exception();
			}
			else {
				policy policy2 = policy1.get();
				polRepo.delete(policy2);
				return policy2;
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}


	

}
