package com.hexaware.claimmanagement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.ExceptionHandling.ResourceNotFoundException;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.DocumentRepository;

@Component
@Transactional
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private DocumentRepository docRepo;
	
	@Autowired
	private ClaimRepository claimRepo;
	
	@Override
	public ResponseEntity<?> saveDocument(int Claim_id, MultipartFile[] files) {
		List<Document> docList= new ArrayList();
		
		Claim claim= claimRepo.findById(Claim_id).orElseThrow(()->new ResourceNotFoundException("No claim present"));
		
		try {
			
			for(MultipartFile file:files) {
				
				String filename = file.getOriginalFilename();
				
				Document doc = new Document(claim,filename,file.getContentType(),file.getBytes());
				docRepo.save(doc);
				docList.add(doc);
			
			}
			System.out.println(docList);
			claim.setDoc(docList);
			claimRepo.save(claim);
			return new ResponseEntity<>(docList,HttpStatus.OK);
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public List<Document> getFiles(int claim_id) {
		
		Claim claim= claimRepo.findById(claim_id).orElseThrow(()->new ResourceNotFoundException("No Claim Found"));
		List<Document> docList= claim.getDoc();
		System.out.println(docList);
		return docList;
	}

	@Override
	public Document getFileById(int doc_id) {
		
		Document doc= docRepo.getFileById(doc_id).orElseThrow(()->new ResourceNotFoundException("Document not available"));
		return doc;
	}
	
	


}
