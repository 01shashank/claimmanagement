package com.hexaware.claimmanagement.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Hospitalization;
import com.hexaware.claimmanagement.Entity.Nominee;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.DocumentRepository;
import com.hexaware.claimmanagement.Repository.HospitalRepository;
import com.hexaware.claimmanagement.Repository.NomineeRepository;
import com.hexaware.claimmanagement.Repository.PolicyRepository;
import com.hexaware.claimmanagement.Repository.UserRepository;

@Component
@Transactional
public class ClaimServiceImpl implements ClaimService{
	
	@Autowired private ClaimRepository claimRepo;
	
	@Autowired private UserRepository userRepo;
	
	@Autowired private PolicyRepository polRepo;
	
	@Autowired private HospitalRepository hospRepo;
	
	@Autowired private NomineeRepository nomRepo;
	
	
	@Autowired
	private DocumentRepository docRepo;

	@Override
	public Claim saveClaim(Claim claim,int userId) {
		
		Optional<User> user = userRepo.findById(userId);
		User user1= user.get();
		claim.setUser(user1);
		Policy policy1 = claim.getPolicy();
		policy1.setUser(user1);
		List<Nominee> nominee=  claim.getPolicy().getNominee();
		nominee.forEach(nom->{
			nom.setPolicy(policy1);
			nomRepo.save(nom);
		});		
		polRepo.save(policy1);
		claim.setClaim_status("UNDER_REVIEW");
		
		
		return claimRepo.save(claim);
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
				System.out.println("In the else part");
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

	@Override
	public Claim updateClaim(int claim_id, Claim claim) {
		Optional<Claim> claim1 = claimRepo.findById(claim_id);
		Claim claim2 = claim1.get();
		Policy policy1 = claim2.getPolicy();
		Hospitalization hospitalization1 = claim2.getHospitalization();
		
		Policy policy = claim.getPolicy();
		Hospitalization hospitalization = claim.getHospitalization();
		
		policy1.setPolicyName(policy.getPolicyName());
		policy1.setPolicy_type(policy.getPolicy_type());
		policy1.setPolicy_premium(policy.getPolicy_premium());
		policy1.setPolicy_coverage(policy.getPolicy_coverage());
		policy1.setPolicy_start_date(policy.getPolicy_start_date());
		policy1.setPolicy_end_date(policy.getPolicy_end_date());
		
		
		hospitalization1.setHospital_doctor(hospitalization.getHospital_doctor());
		hospitalization1.setHospital_medical_expenses(hospitalization.getHospital_medical_expenses());
		hospitalization1.setHospital_non_medical_expenses(hospitalization.getHospital_non_medical_expenses());
		hospitalization1.setHospital_reason(hospitalization.getHospital_reason());
		
		hospRepo.save(hospitalization1);
		polRepo.save(policy1);
		claimRepo.save(claim2);
		return claim2;
	}
	
	
	
	@Override
	public Claim updateStatus(String status,int claim_id,String claim_rejection_reason) {
		
			Optional<Claim> claim1 = claimRepo.findById(claim_id);
			Claim claim2 = claim1.get(); 
			
			claim2.setClaim_status(status);
			claim2.setClaim_rejection_reason(claim_rejection_reason);
			
			claimRepo.save(claim2);
			return claim2;

	}
	






	@Override
	public Claim getClaimByClaimId(int claim_id) {
		Optional<Claim> claim = claimRepo.findById(claim_id);
		Claim claim1 = claim.get();
		//System.out.println(claim1.g);
		return claim1;
	}
	


}
