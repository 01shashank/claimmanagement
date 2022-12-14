package com.hexaware.claimmanagement.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Hospitalization;
import com.hexaware.claimmanagement.Entity.Nominee;
import com.hexaware.claimmanagement.Entity.Policy;

@Service
@Transactional
public interface ClaimService {
	
	public ResponseEntity<?> saveClaim(Claim claim,int userId);
	
	public List<Claim> getAllClaims();
	
	public List<Claim> getPendingClaims();
	
	public List<Claim> getApprovedClaims();
	
	public List<Claim> getRejectedClaims();
	
	public  ResponseEntity<?> getClaimByClaimId(int claim_id);
	
	public ResponseEntity<?> deleteClaim(int claim_Id);
	
	public ResponseEntity<?> updateStatus(List<String> status_and_reason, int  claim_id);
	
	
	

}
