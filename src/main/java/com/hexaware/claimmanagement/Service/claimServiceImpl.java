package com.hexaware.claimmanagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Repository.claimRepository;

@Component
public class claimServiceImpl implements claimService{
	
	@Autowired
	private claimRepository claimRepo;

	@Override
	public Claim saveClaim(Claim claim) {
		return claimRepo.save(claim);
	}

	@Override
	public List<Claim> getAllClaims() {
		return claimRepo.findAll();
	}

}
