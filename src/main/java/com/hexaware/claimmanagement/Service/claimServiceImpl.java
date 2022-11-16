package com.hexaware.claimmanagement.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Claim_status;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Hospitalization;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.DocumentRepository;
import com.hexaware.claimmanagement.Repository.HospitalRepository;
import com.hexaware.claimmanagement.Repository.PolicyRepository;
import com.hexaware.claimmanagement.Repository.UserRepository;

@Component
public class ClaimServiceImpl implements ClaimService{
	
	@Autowired
	private ClaimRepository claimRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private HospitalRepository hospRepo;
	
	@Autowired
	private PolicyRepository polRepo;
	
	@Autowired
	private DocumentRepository docRepo;

	@Override
	public Claim saveClaim(Claim claim,String userEmail) {
		
//		User user1 = userRepo.findByuserEmail(userEmail);
//		Claim claim1 = claim;
//		claim1.setUser(user1);
//		//Policy policy1 = claim1.getPolicy();
//		int policy_id = policy1.getPolicy_Id();
//		Optional<Policy> policy3 = polRepo.findById(policy_id);
//		Policy policy4 = policy3.get();
//		//claim1.setPolicy(policy4);
//		System.out.println(policy1.getPolicyName());
//		claim1.setClaim_status(Claim_status.UNDER_REVIEW);
		
		
		//claimRepo.save(claim1)
		return null;
	}
	
	@Override
	public List<Document> saveDocument(int Claim_id, MultipartFile[] files) {
		List<Document> docList= new ArrayList();
		Claim claim= claimRepo.getById(Claim_id);
		try {
			for(MultipartFile file:files) {
			
				String filename = file.getOriginalFilename();
				Document doc = new Document(filename,file.getContentType(),file.getBytes());
				docRepo.save(doc);
				docList.add(doc);
			
			}
			System.out.println(docList);
			claim.setDoc(docList);
			return docList;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
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
		try {
			Optional<Claim> claim1 = claimRepo.findById(claim_id);
			if(claim1==null) {
				throw new Exception();

			}
			else {
			Claim claim2 = claim1.get();
			claim2.setHospitalization(claim.getHospitalization());
			
			claimRepo.save(claim2);
			return claim2;
			}

			}
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	@Override
	public Claim updateStatus(String status,int claim_id) {
		
		
		try {
			Optional<Claim> claim1 = claimRepo.findById(claim_id);
			if(claim1==null) {
				throw new Exception();

			}
			else {
			Claim claim2 = claim1.get(); 
			Claim_status c_status = Claim_status.valueOf(status);
			claim2.setClaim_status(c_status);
			
			claimRepo.save(claim2);
			return claim2;
			}

			}
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public Claim getClaimById(int claim_id) {
		Optional<Claim> policy = claimRepo.findById(claim_id);
		return policy.get();
	}
	
	@Override
	public List<Claim> getClaimByStatus(String claim_status) {

		Claim_status c_status = Claim_status.valueOf(claim_status);
		List<Claim> claims = claimRepo.getByClaimStatus(c_status);
		return claims;
	}

	@Override
	public List<Document> getFiles(int claim_id) {
		// TODO Auto-generated method stub
		List<Document> docList = claimRepo.getFileById(claim_id);
		return docList;
	}

	@Override
	public Document getFileById(int doc_id) {
		Document doc= docRepo.getFileById(doc_id);
		return doc;
	}
	
	

	


}
