package com.hexaware.claimmanagement.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Claim_status;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Service.ClaimService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ClaimController {
	
	@Autowired
	private ClaimService claimServ;
	

	
	@PostMapping("/saveclaim/{userEmail}")
	public Claim saveClaim(@RequestBody Claim claim, @PathVariable String userEmail) {
		return claimServ.saveClaim(claim,userEmail);
	}
	
	@PostMapping("/savedoc/{claim_id}")
	public List<Document> saveDocument( @PathVariable int claim_id,@RequestParam("file") MultipartFile[] files) {
		return claimServ.saveDocument(claim_id,files);
	}
	
	@GetMapping("/getallclaims")
	public List<Claim> getAllClaims(){
		return claimServ.getAllClaims();
	}
	
	@GetMapping("/getclaimbyid/{claim_id}")
	public Claim getClaimById(@PathVariable int claim_id) {
		return claimServ.getClaimById(claim_id);
	}
	
	@DeleteMapping("/deleteclaim/{claim_id}")
	public ResponseEntity<Claim> deleteClaim(@PathVariable int claim_id) {
		Claim claim1 = claimServ.deleteClaim(claim_id);
		if(claim1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.of(Optional.of(claim1));
		}
	}

	
	@PutMapping("/updateclaim/{claim_id}")
	public ResponseEntity<Claim> updateClaim(@PathVariable int claim_id, @RequestBody Claim claim) {
		Claim claim1 = claimServ.updateClaim(claim_id, claim);
		if(claim1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		}
		else {
			return ResponseEntity.of(Optional.of(claim1));
			}
		
	}
	
	@PutMapping("/updatestatus/{claim_id}/{status}")
	public ResponseEntity<Claim> updateStatus(@PathVariable String status,@PathVariable int claim_id) {
		Claim claim1 = claimServ.updateStatus(  status,claim_id);
		if(claim1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		}
		else {
			return ResponseEntity.of(Optional.of(claim1));
			}
	}
	
	@GetMapping("/getbystatus/{status}")
	public List<Claim> getByClaimStatus(@PathVariable String status){
		return claimServ.getClaimByStatus(status);
	}
	
	@GetMapping("/getdoc/{doc_id}")
	public ResponseEntity<ByteArrayResource> getFileById(@PathVariable int doc_id){
		Document doc= claimServ.getFileById(doc_id);
		
		return ResponseEntity.ok()
				.contentType(org.springframework.http.MediaType.parseMediaType(doc.getDoctype()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocname()+"\"")
				.body(new ByteArrayResource(doc.getData()));
				
	}
	
	@GetMapping("/finddoc/{claim_id}")
	public List<Document> getFiles(@PathVariable int claim_id){
		List<Document> docList= claimServ.getFiles(claim_id);
		return docList;
	}
	

}
