package com.hexaware.claimmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Repository.PolicyRepository;

@Component
public class PolicyServiceImpl implements PolicyService{
	
	@Autowired
	private PolicyRepository polRepo;
	
	@Override
	public Policy savePolicy(Policy policy) {
		return polRepo.save(policy);
	}
	
	@Override
	public List<Policy> getAllPolicies(){
		List<Policy> list1 = polRepo.findAll();
		if(list1.size()<=0) {
			return null;	
		}
		else {
			return list1;
		}
	}
	

	

	@Override
	public Policy deletePolicy(int policy_Id) {
		try {
			Optional<Policy> policy1 = polRepo.findById(policy_Id);
			if(policy1==null){
				throw new Exception();
			}
			else {
				Policy policy2 = policy1.get();
				polRepo.delete(policy2);
				return policy2;
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public Policy getPolicyById(int policy_id) {
		Optional<Policy> policy = polRepo.findById(policy_id);
		Policy policy1 = policy.get();
		return policy1;
	}


	

}
