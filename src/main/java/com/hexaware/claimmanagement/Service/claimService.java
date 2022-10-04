package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.claimmanagement.Entity.Claim;

@Service
public interface claimService {
	
	public Claim saveClaim(Claim claim);
	
	public List<Claim> getAllClaims();

}
