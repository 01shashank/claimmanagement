package com.hexaware.claimmanagement.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Service.ClaimService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ClaimController {
	
	@Autowired private ClaimService claimServ;
	@Autowired private ClaimRepository claimRepo;
	

	
	@PostMapping("/claim/{user_Id}")
	public Claim saveClaim(@Valid @RequestBody Claim claim, @PathVariable int user_Id) {
		return claimServ.saveClaim(claim,user_Id);
	}

	
	@GetMapping("/claims")
	public List<Claim> getAllClaims(){
		return claimServ.getAllClaims();
	}
	
	@GetMapping("/totalclaims")
	public Long getClaimCount(){
		return claimRepo.count();
	}
	
	
	
	
	@GetMapping("/claim/{claim_id}")
	public Claim getClaimById(@PathVariable int claim_id) {
		return claimServ.getClaimByClaimId(claim_id);
	}
	
	
	@DeleteMapping("/claim/{claim_id}")
	public ResponseEntity<Claim> deleteClaim(@PathVariable int claim_id) {
		Claim claim1 = claimServ.deleteClaim(claim_id);
		if(claim1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.of(Optional.of(claim1));
		}
	}
	

	
	@PutMapping("/claim/{claim_id}")
	public Claim updateClaim(@PathVariable int claim_id, @RequestBody Claim claim) {
		Claim claim1 = claimServ.updateClaim(claim_id, claim);
		return claim1;
		
	}
	
	
	@PutMapping("/claimstatus/{claim_id}")
	public Claim updateStatus(@RequestBody String claim_rejection_reason ,@RequestBody String status, @PathVariable int claim_id) {
		Claim claim1 = claimServ.updateStatus(status,claim_id,claim_rejection_reason);
			return claim1;
	}
	
	
}
