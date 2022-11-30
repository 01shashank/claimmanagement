package com.hexaware.claimmanagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.DocumentRepository;

@Component
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private DocumentRepository docRepo;
	
	@Autowired
	private ClaimRepository claimRepo;
	
	@Override
	public List<Document> saveDocument(int Claim_id, MultipartFile[] files) {
		System.out.println("above try");
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
			claimRepo.save(claim);
			return docList;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Document> getFiles(int claim_id) {
		
		List<Document> docList = claimRepo.getFileById(claim_id);
		return docList;
	}

	@Override
	public Document getFileById(int doc_id) {
		
		Document doc= docRepo.getFileById(doc_id);
		return doc;
	}
	
	


}