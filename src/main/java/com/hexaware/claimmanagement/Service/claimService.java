package com.hexaware.claimmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Claim_status;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Policy;

@Service
@Transactional
public interface ClaimService {
	
	public Claim saveClaim(Claim claim,String userEmail);
	
	public List<Document> saveDocument(int Claim_id,MultipartFile[] files);
	
	public List<Claim> getAllClaims();
	
	public Claim getClaimById(int claim_id);
	
	public Claim updateClaim(int claim_id,Claim claim);
	
	public Claim deleteClaim(int claim_Id);
	
	public Claim updateStatus(String status, int  claim_id);
	
	public List<Claim> getClaimByStatus(String status);
	
	public List<Document> getFiles(int claim_id);
	
	public Document getFileById(int doc_id);
	

}
