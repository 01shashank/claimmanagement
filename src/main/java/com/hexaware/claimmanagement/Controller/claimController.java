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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/claimmanagement")
@Transactional
public class ClaimController {
	
	@Autowired private ClaimService claimServ;
	@Autowired private ClaimRepository claimRepo;

	
	@PostMapping("/claim/{user_Id}")
	public Claim saveClaim(@Valid @RequestBody Claim claim, @PathVariable int user_Id) {
		return claimServ.saveClaim(claim,user_Id);
	}

	
	@GetMapping("/admin/allclaims")
	public List<Claim> getAllClaims(){
		return claimServ.getAllClaims();
	}
	
	
	@GetMapping("/claim/{claim_id}")
	public Claim getClaimById(@PathVariable int claim_id) {
		Claim claim= claimServ.getClaimByClaimId(claim_id);
		return claim;
	}
	
	
	@PutMapping("/claim/update/{claim_id}")
	public Claim updateClaim(@PathVariable int claim_id, @RequestBody Claim claim1){
		Claim claim= claimServ.updateClaim(claim_id,claim1);
		return claim;
	}
	

	@DeleteMapping("/admin/deleteclaim/{claim_id}")
	public Claim deleteClaim(@PathVariable int claim_id) {
		Claim claim= claimServ.deleteClaim(claim_id);
		return claim;
		
	}
	
	@PutMapping("/admin/changeclaimstatus/{claim_id}")
	public Claim updateStatus(@RequestBody List<String> status_and_reason ,@PathVariable int claim_id){
		Claim claim1 = claimServ.updateStatus(status_and_reason,claim_id);
		return claim1;
	}
	
	
	@DeleteMapping("/claim/removenominee/{nominee_id}")
	public Nominee removeNominee(@PathVariable int nominee_id){
		Nominee nominee= claimServ.removeNominee(nominee_id);
		return nominee;
	}
	
	@GetMapping("/admin/totalclaims")
	public Long getClaimCount(){
		return claimRepo.count();
	}
	
	@GetMapping("/admin/pendingclaims")
	public int pendingClaims() {
		return claimRepo.pendingClaims();
	}
	
	@GetMapping("/admin/approvedclaims")
	public int acceptedClaims() {
		return claimRepo.accptedClaims();
	}
	
	@GetMapping("/admin/rejectedclaims")
	public int rejectedClaims() {
		return claimRepo.rejectedClaims();
	}
	
	
}
