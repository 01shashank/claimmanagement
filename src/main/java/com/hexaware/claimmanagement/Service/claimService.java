package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Policy;

@Service
public interface claimService {
	
	public Claim saveClaim(Claim claim,int user_Id);
	
	public List<Claim> getAllClaims();
	
	//public Claim updateClaim(int claim_Id,Claim claim);
	
	public Claim deleteClaim(int claim_Id);

}
