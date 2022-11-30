package com.hexaware.claimmanagement.Controller.UserTestController;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.util.Arrays;

import com.hexaware.claimmanagement.Controller.UserController;
import com.hexaware.claimmanagement.Entity.User;

public class UserTestController {
	
	UserController userCont = new UserController();
	
	@Test
	public void getStringCont() {
		
		assertEquals("string",userCont.getString());
	}
	
//	public void getUserByIdTest() {
//		
//		private User user = new User;
//		assertEquals()
//	}
}
