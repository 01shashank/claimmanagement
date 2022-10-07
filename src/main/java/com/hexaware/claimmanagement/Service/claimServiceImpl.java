package com.hexaware.claimmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Hospitalization;
import com.hexaware.claimmanagement.Entity.Insured;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.claimRepository;
import com.hexaware.claimmanagement.Repository.hospitalRepo;
import com.hexaware.claimmanagement.Repository.insuredRepo;
import com.hexaware.claimmanagement.Repository.policyRepository;
import com.hexaware.claimmanagement.Repository.userRepository;

@Component
public class claimServiceImpl implements claimService{
	
	@Autowired
	private claimRepository claimRepo;
	
	@Autowired
	private userRepository userRepo;
	
	@Autowired
	private hospitalRepo hospRepo;
	
	@Autowired
	private insuredRepo insRepo;
	
	@Autowired
	private policyRepository polRepo;

	@Override
	public Claim saveClaim(Claim claim,int user_Id) {
		Optional<User> user = userRepo.findById(user_Id);
		User user1 = user.get();
		Claim claim1 = claim;
		claim1.setUser(user1);
		
		return claimRepo.save(claim1);
	}

	@Override
	public List<Claim> getAllClaims() {
		return claimRepo.findAll();
	}

	@Override
	public Claim deleteClaim(int claim_id) {
		try {
			Optional<Claim> claim1 = claimRepo.findById(claim_id);
			if(claim1==null){
				throw new Exception();
			}
			else {
				Claim claim2 = claim1.get();
				claimRepo.delete(claim2);
				return claim2;
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

}
