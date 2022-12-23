package com.hexaware.claimmanagement.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Document;

@Service
@Transactional
public interface DocumentService {
	
	public ResponseEntity<?> saveDocument(int Claim_id,MultipartFile[] files);
	
	public List<Document> getFiles(int claim_id);
	
	public Document getFileById(int doc_id);

}
