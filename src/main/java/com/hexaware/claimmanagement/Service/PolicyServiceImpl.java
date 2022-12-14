package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.ExceptionHandling.ResourceNotFoundException;
import com.hexaware.claimmanagement.Repository.NomineeRepository;
import com.hexaware.claimmanagement.Repository.PolicyRepository;
import com.hexaware.claimmanagement.Repository.UserRepository;


@Component
public class PolicyServiceImpl implements PolicyService{
	
	@Autowired PolicyRepository polRepo;
	
	@Autowired UserRepository userRepo;
	
	@Autowired NomineeRepository nomRepo;

	@Override
	public Policy savePolicy(Policy policy,int user_Id)  {
		User user= userRepo.findById(user_Id).orElseThrow(()-> new ResourceNotFoundException("User is not present"));
		policy.setUser(user);
		String policy_name = policy.getPolicyName();
		if(policy_name.equals("Standard Health Plan")){
			System.out.println("in standard");
			policy.setPolicy_premium(17500);
			policy.setPolicy_coverage(250000);
		}
		else if(policy_name.equals("Silver Health Plan")) {
			System.out.println("in silver");
			policy.setPolicy_premium(20000);
			policy.setPolicy_coverage(450000);
		}
		else if(policy_name.equals("Gold Health Plan")) {
			System.out.println("in gold");
			policy.setPolicy_premium(22500);
			policy.setPolicy_coverage(750000);
		}
		else if(policy_name.equals("Premium Health Plan")) {
			System.out.println("in premium");
			policy.setPolicy_premium(25000);
			policy.setPolicy_coverage(1100000);
		}
		
		Policy policy1= polRepo.save(policy);
		policy.getNominee().forEach(nom->{
			nom.setPolicy(policy1);
			nomRepo.save(nom);
		});	
		return policy1;
		
	}

	@Override
	public List<Policy> getUserPolicies(int user_Id) {
		User user= userRepo.findById(user_Id).orElseThrow(()-> new ResourceNotFoundException("User is not present"));
		
		return user.getUser_policies();
	}

	@Override
	public Policy getPolicyById(int policy_id) {
		Policy policy = polRepo.findById(policy_id).orElseThrow(()-> new ResourceNotFoundException("No Policy found"));
		return policy;
	}

	@Override
	public Policy deletePolicy(int policy_id) {
		Policy policy = polRepo.findById(policy_id).orElseThrow(()-> new ResourceNotFoundException("No Policy found"));
		polRepo.delete(policy);
		return policy;
	}

}
