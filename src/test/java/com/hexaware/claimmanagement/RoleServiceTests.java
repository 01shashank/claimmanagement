package com.hexaware.claimmanagement;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.hexaware.claimmanagement.Entity.Role;
import com.hexaware.claimmanagement.Repository.RoleRepository;
import com.hexaware.claimmanagement.Service.RoleService;
import com.hexaware.claimmanagement.Service.RoleServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleServiceTests {
	
	@Mock
	RoleRepository roleRepo;
	
	@InjectMocks
	RoleService roleServ =new RoleServiceImpl();
	
	@Before
	public void initializeMockito() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveRole_Success() {
		//Role will be saved.
		
		Role role1 = new Role(1,"ROLE_USER");
		Role role2 = new Role(2,"ROLE_ADMIN");
		Role role3 = new Role(3,"ROLE_NEW");
		
		when(roleRepo.save(role1)).thenReturn(role1);
		
		assertEquals(role1,roleServ.saveRole(role1));
	}
	
	@Test
	public void testSaveRole_Failure() {
		//Role will not be saved as we're passing null value.
		
		Role role1 = new Role(1,"ROLE_USER");
		
		when(roleRepo.save(role1)).thenReturn(role1);
		
		assertEquals(role1,roleServ.saveRole(null));
	}
	
	
	
	@Test
	public void testGetAllRoles_Success() {
		//A List of Roles will be returned as a Response.
		
		Role role1 = new Role(1,"ROLE_USER");
		Role role2 = new Role(2,"ROLE_ADMIN");
		
		List<Role> roles = Arrays.asList(role1,role2);
		
		when(roleRepo.findAll()).thenReturn(roles);
		
		assertEquals(roles,roleServ.getAllRoles());
	}
	
	@Test
	public void testGetAllRoles_Failure() {
		//A null value will be returned as there are no Roles present.
		
		Role role1 = new Role(1,"ROLE_USER");
		Role role2 = new Role(2,"ROLE_ADMIN");
		
		List<Role> roles = Arrays.asList(role1,role2);
		
		when(roleRepo.findAll()).thenReturn(null);
		
		assertEquals(roles,roleServ.getAllRoles());
	}
	
	
	
	

}
