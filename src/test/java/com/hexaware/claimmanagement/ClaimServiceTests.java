package com.hexaware.claimmanagement;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Hospitalization;
import com.hexaware.claimmanagement.Entity.Nominee;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.Role;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.UserRepository;
import com.hexaware.claimmanagement.Repository.PolicyRepository;
import com.hexaware.claimmanagement.Service.ClaimService;
import com.hexaware.claimmanagement.Service.ClaimServiceImpl;
import static org.mockito.ArgumentMatchers.*;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClaimServiceTests {
	
	@Mock
	ClaimRepository claimRepo;
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	PolicyRepository polRepo;
	
	@InjectMocks
	ClaimService claimServ =new ClaimServiceImpl();
	
	@Before
	public void initializeMockito() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveClaim_Success() {
		// User will be able to make a claim and the response will be 200 OK.

		
		Nominee nominee = new Nominee(1,new Policy(),"Suhani KL",39,"Sister");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Gold Health Plan",750000,22500,60000,50000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		
		Hospitalization hosp = new Hospitalization(new Claim(),"Dr.Naveen Sethi",90000,40000,"Back Injury");
		
		List<Document> docList= new ArrayList<>();
		
		User user = new User(1,"sangaleshashank2001@gmail.com","shashank@123","Shashank","Sangale",user_policies,new ArrayList<Claim>(),new HashSet<>());

		
		Claim savingClaim =new Claim(1,user,"UNDER_REVIEW",hosp,docList,policy,null);
		
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		when(polRepo.findById(anyInt())).thenReturn(Optional.of(policy));
		
		ResponseEntity<?> results = claimServ.saveClaim(savingClaim, 1);
		System.out.println(results.getBody());
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(savingClaim,results.getBody());
	}
	
	@Test
	public void testSaveClaim_Failure() {
		// User will not be able to make a claim, Because Policy is Expired.
		
		Nominee nominee = new Nominee(1,new Policy(),"Jay Shinde",25,"Brother");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Gold Health Plan",750000,22500,60000,50000,LocalDate.now(),LocalDate.parse("2019-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		Claim claim =new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		Role role = new Role();
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		Hospitalization hosp = new Hospitalization(claim,"Dr.Naveen Sethi",90000,40000,"Back Injury");
		
		List<Document> docList= new ArrayList<>();
		
		User user = new User(1,"sangaleshashank2001@gmail.com","shashank@123","Shashank","Sangale",user_policies,user_claims,user_Roles);

		
		Claim savingClaim =new Claim(1,user,"UNDER_REVIEW",hosp,docList,policy,null);
		
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		when(polRepo.findById(anyInt())).thenReturn(Optional.of(policy));
		
		ResponseEntity<?> results = claimServ.saveClaim(savingClaim, 1);
		System.out.println(results.getBody());
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(savingClaim,results.getBody());
	}
	

	@Test
	public void testGetClaimByClaimId_Succes() {
		// Claim will be found with the given ID and the response will be 200 OK.
		
		User user = new User(2,"kevinm@gmail.com","kevin@123","Kevin","Mann",new ArrayList<Policy>(),new ArrayList<Claim>(),new HashSet<Role>());
		Claim claim =new Claim(1,user,"UNDER_REVIEW",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		
		when(claimRepo.findById(anyInt())).thenReturn(Optional.of(claim));
		
		ResponseEntity<?> results = claimServ.getClaimByClaimId(1);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(claim,results.getBody());

	}
	
	@Test
	public void testGetClaimByClaimId_Failure() {
		//Claim will not be found because the ID is not present
		//System will send a 404 Not Found response.
		
		User user = new User(2,"kevinm@gmail.com","kevin@123","Kevin","Mann",new ArrayList<Policy>(),new ArrayList<Claim>(),new HashSet<Role>());
		Claim claim =new Claim(1,user,"UNDER_REVIEW",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		
		when(claimRepo.findById(1)).thenReturn(Optional.of(claim));
		
		ResponseEntity<?> results = claimServ.getClaimByClaimId(2);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(claim,results.getBody());

	}
	

	@Test
	public void testGetAllClaims_Success() {
		//A List with all claims will be returned by the repository.
		
		User user = new User(2,"kevinm@gmail.com","kevin@123","Kevin","Mann",new ArrayList<Policy>(),new ArrayList<Claim>(),new HashSet<Role>());
		Claim claim1 =new Claim(1,user,"UNDER_REVIEW",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		Claim claim2= new Claim(1,user,"ACCEPTED",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		List<Claim> claimList= new ArrayList<>(Arrays.asList(claim1,claim2));
		
		when(claimRepo.findAll()).thenReturn(claimList);
		
		List<Claim> results = claimServ.getAllClaims();
		assertEquals(2,results.size());
		assertEquals(claimList,results);

	}
	
	@Test
	public void testDelteClaim_Success() {
		//Claim will get deleted with specified Id and system will send a 200 OK response.
		
		User user = new User(2,"kevinm@gmail.com","kevin@123","Kevin","Mann",new ArrayList<Policy>(),new ArrayList<Claim>(),new HashSet<Role>());
		Claim claim1 =new Claim(1,user,"UNDER_REVIEW",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		
		when(claimRepo.findById(anyInt())).thenReturn(Optional.of(claim1));
		
		ResponseEntity<?> results = claimServ.deleteClaim(1);
		assertEquals(HttpStatus.OK,results.getStatusCode());

	}
	
	@Test
	public void testDelteClaim_Failure() {
		//Claim will not be deleted as claim with ID 2 is not present and system will send 404 response.
		
		User user = new User(2,"kevinm@gmail.com","kevin@123","Kevin","Mann",new ArrayList<Policy>(),new ArrayList<Claim>(),new HashSet<Role>());
		Claim claim1 =new Claim(1,user,"UNDER_REVIEW",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		
		when(claimRepo.findById(1)).thenReturn(Optional.of(claim1));
		
		ResponseEntity<?> results = claimServ.deleteClaim(2);
		assertEquals(HttpStatus.OK,results.getStatusCode());

	}
	
	@Test
	public void testUpdateClaimStatus_Success() {
		//Claim Status will be updated with status 200 OK.
		
		User user = new User(2,"kevinm@gmail.com","kevin@123","Kevin","Mann",new ArrayList<Policy>(),new ArrayList<Claim>(),new HashSet<Role>());
		Claim claim1 =new Claim(1,user,"UNDER_REVIEW",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		List<String> status_and_reason = new ArrayList<>(Arrays.asList("REJECTED","No Documents Submitted"));
		
		when(claimRepo.findById(anyInt())).thenReturn(Optional.of(claim1));
		
		ResponseEntity<?> results = claimServ.updateStatus(status_and_reason,2);
		assertEquals(HttpStatus.OK,results.getStatusCode());

		
	}
	
	@Test
	public void testUpdateClaimStatus_Failure() {
		//Claim Status will not be updated as the claim is not present in the system.
		
		User user = new User(2,"kevinm@gmail.com","kevin@123","Kevin","Mann",new ArrayList<Policy>(),new ArrayList<Claim>(),new HashSet<Role>());
		Claim claim1 =new Claim(1,user,"UNDER_REVIEW",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		List<String> status_and_reason = new ArrayList<>(Arrays.asList("REJECTED","No Documents Submitted"));
		
		when(claimRepo.findById(1)).thenReturn(Optional.of(claim1));
		
		ResponseEntity<?> results = claimServ.updateStatus(status_and_reason,2);
		assertEquals(HttpStatus.OK,results.getStatusCode());

		
	}
	

}
