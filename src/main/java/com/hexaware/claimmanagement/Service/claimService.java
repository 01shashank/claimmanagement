package com.hexaware.claimmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Hospitalization;
import com.hexaware.claimmanagement.Entity.Policy;

@Service
@Transactional
public interface ClaimService {
	
	public Claim saveClaim(Claim claim,int userId);
	
	public List<Claim> getAllClaims();
	
	
	public Claim getClaimByClaimId(int claim_id);
	
	
	public Claim updateClaim(int claim_id, Claim claim);
	
	public Claim deleteClaim(int claim_Id);
	
	public Claim updateStatus(String status, int  claim_id, String claim_rejection_reason);
	
	
	
	

}
