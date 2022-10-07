package com.hexaware.claimmanagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Service.claimService;

@RestController
public class claimController {
	
	@Autowired
	private claimService claimServ;
	
	@PostMapping("/saveclaim/{user_Id}")
	public Claim saveClaim(@RequestBody Claim claim, @PathVariable int user_Id) {
		return claimServ.saveClaim(claim,user_Id);
	}
	
	@GetMapping("/getallclaims")
	public List<Claim> getAllClaims(){
		return claimServ.getAllClaims();
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

}
