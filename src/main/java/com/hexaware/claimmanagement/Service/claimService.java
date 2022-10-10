package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Claim_status;
import com.hexaware.claimmanagement.Entity.Policy;

@Service
@Transactional
public interface claimService {
	
	public Claim saveClaim(Claim claim,int user_Id);
	
	public List<Claim> getAllClaims();
	
	public Claim updateClaim(int claim_id,Claim claim);
	
	public Claim deleteClaim(int claim_Id);
	
	public Claim updateStatus(Claim_status status, int  claim_id);
	
	//public void deleteTest(int claim_id);

}
