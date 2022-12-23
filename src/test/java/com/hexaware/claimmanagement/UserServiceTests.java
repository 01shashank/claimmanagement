package com.hexaware.claimmanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Hospitalization;
import com.hexaware.claimmanagement.Entity.Nominee;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.Role;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.PolicyRepository;
import com.hexaware.claimmanagement.Repository.UserRepository;
import com.hexaware.claimmanagement.Service.UserService;
import com.hexaware.claimmanagement.Service.UserServiceImpl;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTests {
	
	@Mock UserRepository userRepo;
	
	@Mock ClaimRepository claimRepo;
	
	@Mock PolicyRepository polRepo;
	
	@Mock PasswordEncoder passwordEncoder;
	
	@InjectMocks UserService userServ = new UserServiceImpl();
	
	@Before
	public void initializeMockito() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveUser_Success() {
		//A user will be saved with response 200 OK.
		
		Nominee nominee = new Nominee(1,new Policy(),"Jay Shinde",25,"Brother");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Gold Health Plan",750000,22500,60000,50000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		Claim claim =new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		Role role = new Role(1,"ROLE_ADMIN");
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		
		User user = new User(1,"damodarp@yahoo.com","damodar@120","Damodar","Pattnaik",user_policies,user_claims,user_Roles);
		
		when(userRepo.save(user)).thenReturn(user);
		
		User results = userServ.saveUser(user);
		
		assertEquals(user,results);
		assertEquals(user.getUser_Email(),results.getUser_Email());
	}
	
	@Test
	public void testSaveUser_Failure() {
		//A user will not be saved as we're passing null value and hence the validation fails and exception is thrown.
		
		Nominee nominee = new Nominee(1,new Policy(),"Jay Shinde",25,"Brother");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Gold Health Plan",750000,22500,60000,50000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		Claim claim =new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		Role role = new Role(1,"ROLE_USER");
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		
		User user = new User(1,"damodarp@gmail.com","damodar@120","Damodar","Pattnaik",user_policies,user_claims,user_Roles);
		
		when(userRepo.save(user)).thenReturn(null);
		
		User results = userServ.saveUser(user);
		
		assertEquals(user,results);
		assertEquals(user.getUser_Email(),results.getUser_Email());
	}
	
	@Test
	public void testGetAllUsers_Success() {
		//A List of all users will be returned as a response.
		
		Nominee nominee1 = new Nominee(1,new Policy(),"Jay Shinde",25,"Brother");
		Nominee nominee2 = new Nominee(2,new Policy(),"Rakesh Hood",19,"Brother");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee1,nominee2));
		
		Policy policy1 =new Policy("Gold Health Plan",750000,22500,60000,50000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies1 = new ArrayList<>(Arrays.asList(policy1));
		
		Policy policy2 =new Policy("Premium Health Plan",1000000,25000,200000,150000,LocalDate.parse("2019-12-25"),LocalDate.parse("2020-12-24"),nomineeList,null,null);
		List<Policy> user_policies2 = new ArrayList<>(Arrays.asList(policy2));
		
		Claim claim1 =new Claim(1,new User(),"UNDER_REVIEW",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		List<Claim> user_claims1 = new ArrayList<>(Arrays.asList(claim1));
		
		Claim claim2 =new Claim(1,new User(),"ACCEPTED",new Hospitalization(),new ArrayList<Document>(),new Policy(),null);
		List<Claim> user_claims2 = new ArrayList<>(Arrays.asList(claim2));
		
		Role role = new Role();
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		
		User user1 = new User(1,"damodarp@yahoo.com","damodar@120","Damodar","Pattnaik",user_policies1,user_claims1,user_Roles);
		User user2 = new User(2,"rushikeshpatil@gmail.com","rushi@2001","Rushikesh","Patil",user_policies2,user_claims2,user_Roles);
		List<User> userList= new ArrayList<User>(Arrays.asList(user1,user2));
		when(userRepo.findAll()).thenReturn(userList);
		
		List<User> results = userServ.getAllUsers();
		assertEquals(userList,results);
		assertEquals(2,results.size());
		
	}
	
	@Test
	public void testGetAllUsers_Failure() {
		//Since the list contains null values the userList will return null;
		
		List<User> userList = new ArrayList<>(Arrays.asList(null));
		when(userRepo.findAll()).thenReturn(userList);
		
		List<User> results = userServ.getAllUsers();
		assertEquals(2,results.size());
		
	}
	
	@Test
	public void testGetUserById_Success() {
		//A user with particular Id will be returned with response 200 OK.
		
		Nominee nominee = new Nominee(1,new Policy(),"Swati Jain",34,"Wife");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Silver Health Plan",500000,20500,40000,20000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		Claim claim =new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		Role role = new Role(1,"ROLE_ADMIN");
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		
		User user = new User(1,"shravanjain@yahoo.com","shravan@120","Shravan","Jain",user_policies,user_claims,user_Roles);
		
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		
		ResponseEntity<?> results = userServ.getUserbyId(1);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(user,results.getBody());
		
	}
	
	@Test
	public void testGetUserById_Failure() {
		//404 Not Found response will be returned as user is not present.
		
		Nominee nominee = new Nominee(1,new Policy(),"Swati Jain",34,"Wife");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Silver Health Plan",500000,20500,40000,20000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		Claim claim =new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		Role role = new Role();
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		
		User user = new User(1,"shravanjain@yahoo.com","shravan@120","Shravan","Jain",user_policies,user_claims,user_Roles);
		
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		
		ResponseEntity<?> results = userServ.getUserbyId(2);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(user,results.getBody());
		
	}
	
	@Test
	public void testDeleteUser_Success() {
		//A user will be deleted successfully with response 200 OK.
		
		Nominee nominee = new Nominee(1,new Policy(),"Swati Jain",34,"Wife");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Silver Health Plan",500000,20500,40000,20000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		Claim claim =new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		Role role = new Role();
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		
		User user = new User(1,"shravanjain@yahoo.com","shravan@120","Shravan","Jain",user_policies,user_claims,user_Roles);
		
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		
		ResponseEntity<?> results = userServ.getUserbyId(1);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(user,results.getBody());
		
	}
	
	@Test
	public void testDeleteUser_Failure() {
		//As the user is not present, We'll get a 404 Not Found request.
		
		Nominee nominee = new Nominee(1,new Policy(),"Swati Jain",34,"Wife");
		List<Nominee> nomineeList= new ArrayList<>(Arrays.asList(nominee));
		
		Policy policy =new Policy("Silver Health Plan",500000,20500,40000,20000,LocalDate.now(),LocalDate.parse("2023-10-07"),nomineeList,null,null);
		List<Policy> user_policies = new ArrayList<>(Arrays.asList(policy));
		
		Claim claim =new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		Role role = new Role();
		Set<Role> user_Roles = new HashSet<>();
		user_Roles.add(role);
		
		
		User user = new User(1,"shravanjain@yahoo.com","shravan@120","Shravan","Jain",user_policies,user_claims,user_Roles);
		
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		
		ResponseEntity<?> results = userServ.getUserbyId(2);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(user,results.getBody());
		
	}
	
	@Test
	public void testUserClaims_Success() {
		//A list of claims of a particular user will be returned.
		
		Claim claim = new Claim();
		List<Claim> user_claims = new ArrayList<>(Arrays.asList(claim));
		
		
		User user = new User(1,"xaviers@yahoo.com","xavier@120","Xavier","Suzen",new ArrayList<Policy>(),user_claims,new HashSet<>());
		
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		ResponseEntity<?> results = userServ.getUserClaims(1);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		assertEquals(user_claims,results.getBody());
		
	}
	
	@Test
	public void testUserClaims_Failure() {
		//No list is returned as claims are null for the user.
		
		User user = new User(1,"xaviers@yahoo.com","xavier@120","Xavier","Suzen",new ArrayList<Policy>(),new ArrayList<Claim>(Arrays.asList(null)),new HashSet<>());
		
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		
		ResponseEntity<?> results = userServ.getUserClaims(1);
		assertEquals(HttpStatus.OK,results.getStatusCode());
		
	}
	
	
}
