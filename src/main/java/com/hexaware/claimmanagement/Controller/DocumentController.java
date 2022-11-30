package com.hexaware.claimmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Service.DocumentService;

@RestController
@RequestMapping("/api")
public class DocumentController {
	
	@Autowired
	private DocumentService docServ;
	
	
	@PostMapping("/document/{claim_id}")
	public List<Document> saveDocument( @PathVariable int claim_id,@RequestParam("file") MultipartFile[] files) {
		return docServ.saveDocument(claim_id,files);
	}
	
	
	@GetMapping("/downloaddocument/{doc_id}")
	public ResponseEntity<ByteArrayResource> getFileById(@PathVariable int doc_id){
		Document doc= docServ.getFileById(doc_id);
		
		return ResponseEntity.ok()
				.contentType(org.springframework.http.MediaType.parseMediaType(doc.getDoctype()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocname()+"\"")
				.body(new ByteArrayResource(doc.getData()));
				
	}
	
	@GetMapping("/documentdetails/{claim_id}")
	public List<Document> getFiles(@PathVariable int claim_id){
		List<Document> docList= docServ.getFiles(claim_id);
		return docList;
	}
	  

}
