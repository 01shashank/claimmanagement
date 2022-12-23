package com.hexaware.claimmanagement.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Hospitalization;
import com.hexaware.claimmanagement.Entity.Nominee;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.ExceptionHandling.ResourceNotFoundException;
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
	public ResponseEntity<?> saveClaim(Claim claim,int userId) {
		
		Optional<User> user = userRepo.findById(userId);
		User user1= user.get();
		claim.setUser(user1);
		
		Policy policy = polRepo.findById(claim.getPolicy().getPolicy_Id()).orElseThrow(()->new ResourceNotFoundException("Policy Not Found"));
		
		LocalDate date = policy.getPolicy_end_date();
		LocalDate localDate = LocalDate.now();
		
		int val = date.compareTo(localDate);
		System.out.println(date+" "+localDate);
		
		if(val>0) {
			claim.setPolicy(policy);
			claim.setClaim_status("UNDER_REVIEW");
			
			claimRepo.save(claim);
			return new ResponseEntity<>(claim,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("The Policy is expired. You have to renew the policy to make a claim",HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<Claim> getAllClaims() {
		List<Claim> claimList = claimRepo.findAll();
		if(claimList.size()==0) {
			throw new ResourceNotFoundException("There are No claims");
		}
		else {
			return claimList;
		}
	}
	
	@Override
	public List<Claim> getPendingClaims() {
		List<Claim> pendingClaims = claimRepo.getPendingClaims();
		if(pendingClaims.size()==0) {
			throw new ResourceNotFoundException("There are No claims");
		}
		else {
			return pendingClaims;
		}
	}

	@Override
	public List<Claim> getApprovedClaims() {
		List<Claim> approvedClaims = claimRepo.getApprovedClaims();
		if(approvedClaims.size()==0) {
			throw new ResourceNotFoundException("There are No claims");
		}
		else {
			return approvedClaims;
		}
	}

	@Override
	public List<Claim> getRejectedClaims() {
		List<Claim> rejectedClaims = claimRepo.getRejectedClaims();
		if(rejectedClaims.size()==0) {
			throw new ResourceNotFoundException("There are No claims");
		}
		else {
			return rejectedClaims;
		}
	}

	@Override
	public ResponseEntity<?> deleteClaim(int claim_id) {
		Optional<Claim> claimOp = claimRepo.findById(claim_id);
		Claim claim = claimOp.get();
		if(claim==null) {
			throw new ResourceNotFoundException("No Policy found");
		}
		else {
			claimRepo.delete(claim);
			return new ResponseEntity<>(claim,HttpStatus.OK);
		}
	}
	
	
	@Override
	public ResponseEntity<?> updateStatus(List<String> status_and_reason,int claim_id) {
		
			Optional<Claim> claimOp = claimRepo.findById(claim_id);
			Claim claim2 = claimOp.get();
			if(claim2==null) {
				throw new ResourceNotFoundException("Claim is not present"); 
			}
			else {
				claim2.setClaim_status(status_and_reason.get(0));
				claim2.setClaim_rejection_reason(status_and_reason.get(1));
					
				claimRepo.save(claim2);
				return new ResponseEntity(claim2,HttpStatus.OK);
			}
	}
	


	@Override
	public ResponseEntity<?> getClaimByClaimId(int claim_id) {
		Optional<Claim> claimOp = claimRepo.findById(claim_id);
		Claim claim = claimOp.get();
		if(claim==null) {
			throw new ResourceNotFoundException("Claim is not present");
		}
		else {
			return new ResponseEntity<>(claim,HttpStatus.OK);
		}
	}



}

