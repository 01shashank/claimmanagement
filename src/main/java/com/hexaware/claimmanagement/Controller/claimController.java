package com.hexaware.claimmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Service.claimService;

@RestController
public class claimController {
	
	@Autowired
	private claimService claimServ;
	
	@PostMapping("/saveclaim")
	public Claim saveClaim(Claim claim) {
		return claimServ.saveClaim(claim);
	}
	
	@GetMapping("/getallclaims")
	public List<Claim> getAllClaims(){
		return claimServ.getAllClaims();
	}

}
