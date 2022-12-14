package com.hexaware.claimmanagement.Controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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
import com.hexaware.claimmanagement.Entity.Nominee;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.NomineeRepository;
import com.hexaware.claimmanagement.Service.ClaimService;
import com.hexaware.claimmanagement.ExceptionHandling.ResourceNotFoundException;

@RestController
@CrossOrigin(origins ="http://localhost:5000")
@RequestMapping("/claimmanagement")
@Transactional
public class ClaimController {
	
	@Autowired private ClaimService claimServ;
	@Autowired private ClaimRepository claimRepo;

	
	@PostMapping("/claim/{user_Id}")
	public ResponseEntity<?> saveClaim(@Valid @RequestBody Claim claim, @PathVariable int user_Id) {
		return claimServ.saveClaim(claim,user_Id);
		
	}

	
	@GetMapping("/admin/allclaims")
	public List<Claim> getAllClaims(){
		return claimServ.getAllClaims();
	}
	
	@GetMapping("/admin/pendingclaims")
	public List<Claim> getPendingClaims(){
		return claimRepo.getPendingClaims();
	}
	
	@GetMapping("/admin/approvedclaims")
	public List<Claim> getApprovedClaims() {
		return claimRepo.getApprovedClaims();
	}
	
	@GetMapping("/admin/rejectedclaims")
	public List<Claim> getRejectedClaims() {
		return claimRepo.getRejectedClaims();
	}
	
	
	@GetMapping("/claim/{claim_id}")
	public ResponseEntity<?> getClaimById(@PathVariable int claim_id) {
		return claimServ.getClaimByClaimId(claim_id);
		 
	}
	

	@DeleteMapping("/admin/deleteclaim/{claim_id}")
	public ResponseEntity<?> deleteClaim(@PathVariable int claim_id) {
		return claimServ.deleteClaim(claim_id);
		
	}
	
	@PutMapping("/admin/changeclaimstatus/{claim_id}")
	public ResponseEntity<?> updateStatus(@RequestBody List<String> status_and_reason ,@PathVariable int claim_id){
		return claimServ.updateStatus(status_and_reason,claim_id);
	}
	
	
	@GetMapping("/admin/totalclaimscount")
	public int getAllClaimsCount(){
		return claimServ.getAllClaims().size();
	}
	
	@GetMapping("/admin/pendingclaimscount")
	public int getpendingClaimsCount() {
		return claimRepo.getPendingClaims().size();
	}
	
	@GetMapping("/admin/approvedclaimscount")
	public int getacceptedClaimsCount() {
		return claimRepo.getApprovedClaims().size();
	}
	
	@GetMapping("/admin/rejectedclaimscount")
	public int getrejectedClaimsCount() {
		return claimRepo.getRejectedClaims().size();
	}
	
	
	
	
}
