package com.hexaware.claimmanagement.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hexaware.claimmanagement.Entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.UserRepository;

@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ClaimRepository claimRepo;
	
	

	@Override
	public User saveUser(User user1) {
		
//		String pass= user1.getUser_password();
//		String pass2= passwordEncoder.encode(pass);
//		System.out.println(this.passwordEncoder.encode(pass2));
//		user1.setUser_password(pass2);
		
		Set<Role> userRoles = new HashSet<Role>();
		userRoles.add(userRepo.getRole());
		user1.setUser_roles(userRoles);
		
		//List<Claim> list1= user1.getUser_claims();
		
		return userRepo.save(user1);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list1 = userRepo.findAll();
		if(list1.size()<=0) {
			return null;	
		}
		else {
			return list1;
		}
	}


	@Override
	public User deleteUser(int user_Id) {
		try {
			Optional<User> user1 = userRepo.findById(user_Id);
			if(user1==null){
				throw new Exception();
			}
			else {
				User user2 = user1.get();
				
//				List<Claim> claim1 =user2.getUser_claims();
//				claim1.forEach(claim->{
//					claimRepo.delete(claim);
//				});
//				
				
				userRepo.delete(user2);
				return user2;
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public User getUserbyId(int user_Id) {
		Optional<User> user1=userRepo.findById(user_Id);
		User user2 =user1.get();
		return user2;
	}

	@Override
	public User getUserbyEmail(String userEmail) {
		// TODO Auto-generated method stub
		User user1=userRepo.findByuserEmail(userEmail);
		return user1;
		
	}

}
