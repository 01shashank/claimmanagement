package com.hexaware.claimmanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hexaware.claimmanagement.Service.PolicyService;
import com.hexaware.claimmanagement.Service.PolicyServiceImpl;
import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Nominee;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.Role;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.NomineeRepository;
import com.hexaware.claimmanagement.Repository.PolicyRepository;
import com.hexaware.claimmanagement.Repository.UserRepository;
import static org.mockito.ArgumentMatchers.*;



@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PolicyServiceTests {
	
	@Mock
	PolicyRepository polRepo;
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	NomineeRepository nomRepo;
	
	@InjectMocks
	PolicyService polServ =new PolicyServiceImpl();
	
	@Before
	public void initializeMockito() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSavePolicy_Success() {
		//Policy details will be saved for a particular user.
		
		Nominee nominee = new Nominee(1,new Policy(),"Jay Shinde",25,"Brother");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Gold Health Plan",750000,22500,60000,50000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		Claim claim =new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		Role role = new Role();
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		User user = new User(1,"sangaleshashank2001@gmail.com","shashank@123","Shashank","Sangale",user_policies,user_claims,user_Roles);
		
		Policy policy1 =new Policy("Standard Health Plan",250000,17500,75000,50000,LocalDate.now(),LocalDate.parse("2023-12-23"),nomineeList,user,claim);
		
		
				
		when(nomRepo.save(nominee)).thenReturn(nominee);
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		when(polRepo.save(policy1)).thenReturn(policy1);
		
		Policy results=polServ.savePolicy(policy1,1);
		assertEquals(policy1,results);
	}
	
	@Test
	public void testSavePolicy_Failure() {
		//Policy details will not be saved as we're passing a null value as a policy.
		
		Nominee nominee = new Nominee(1,new Policy(),"Jay Shinde",25,"Brother");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Gold Health Plan",750000,22500,60000,50000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		Claim claim =new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		Role role = new Role();
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		User user = new User(1,"sangaleshashank2001@gmail.com","shashank@123","Shashank","Sangale",user_policies,user_claims,user_Roles);
		
		Policy policy1 =new Policy("Standard Health Plan",250000,17500,75000,50000,LocalDate.now(),LocalDate.parse("2023-12-23"),nomineeList,user,claim);
		
		when(nomRepo.save(nominee)).thenReturn(nominee);
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		when(polRepo.save(policy1)).thenReturn(policy1);
		
		Policy results=polServ.savePolicy(null,1);
		assertEquals(policy1,results);
		assertEquals("Premium Health Plan",results.getPolicyName());
		assertEquals("500000",results.getPolicy_coverage());
	}
	
	@Test
	public void testPolicyById_Success() {
		//Policy will be found with an Id with response 200 OK.
		
		Nominee nominee1 = new Nominee(1,new Policy(),"Seema Singh",57,"Mother");
		Nominee nominee2 = new Nominee(1,new Policy(),"Dilip Singh",67,"Father");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee1,nominee2));
		Policy policy1 =new Policy("Gold Health Plan",750000,22500,75000,50000,LocalDate.now(),LocalDate.parse("2023-12-23"),nomineeList,new User(),new Claim());

		when(polRepo.findById(anyInt())).thenReturn(Optional.of(policy1));
		
		Policy results=polServ.getPolicyById(1);
		assertEquals(policy1,results);
		assertEquals("Gold Health Plan",results.getPolicyName());
		assertEquals(750000,results.getPolicy_coverage());
		
	}
	
	@Test
	public void testPolicyById_Failure() {
		//Policy will not be found as Policy details against Id 1 are not present.

		
		Nominee nominee = new Nominee(1,new Policy(),"Soham Mane",34,"Brother");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		Policy policy1 =new Policy("Standard Health Plan",250000,17500,75000,50000,LocalDate.now(),LocalDate.parse("2023-12-23"),nomineeList,new User(),new Claim());

		when(polRepo.findById(2)).thenReturn(Optional.of(policy1));
		
		Policy results=polServ.getPolicyById(1);
		assertEquals("Gold Health Plan",results.getPolicyName());
		assertEquals(1000000,results.getPolicy_coverage());
		
	}
	
	@Test
	public void testDeletePolicy_Success() {
		//Policy with given Id will be deleted with a response 200 OK.
		
		Nominee nominee = new Nominee(1,new Policy(),"Kishor Kadam",40,"Brother");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		Policy policy1 =new Policy("Standard Health Plan",250000,17500,75000,50000,LocalDate.now(),LocalDate.parse("2023-12-23"),nomineeList,new User(),new Claim());

		when(polRepo.findById(anyInt())).thenReturn(Optional.of(policy1));
		
		ResponseEntity<?> results=polServ.deletePolicy(1);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(policy1,results.getBody());
		
	}
	
	@Test
	public void testDeletePolicy_Failure() {
		//Policy will get deleted as the policy is not present and we'll get a 404 Not Found Response.
		
		Nominee nominee = new Nominee(1,new Policy(),"Kishor Kadam",40,"Brother");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		Policy policy1 =new Policy("Standard Health Plan",250000,17500,75000,50000,LocalDate.now(),LocalDate.parse("2023-12-23"),nomineeList,new User(),new Claim());

		when(polRepo.findById(2)).thenReturn(Optional.of(policy1));
		
		ResponseEntity<?> results=polServ.deletePolicy(1);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		
	}
	

}
