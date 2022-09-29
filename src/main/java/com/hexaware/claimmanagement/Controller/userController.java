package com.hexaware.claimmanagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.claimmanagement.Entity.policy;
import com.hexaware.claimmanagement.Repository.userRepository;

@RestController
public class userController {
	
	@Autowired
	private userRepository userRepo;
	
	@RequestMapping("/hello")
	public String getHello() {
		return "Hello world";
	}
	
	@RequestMapping("/policy")
	public List<policy> getPolicies(){
		return userRepo.findAll();
	}
	
	@RequestMapping("/printpolicy")
	public void printpolicy() {
		List<policy> list1=userRepo.findAll();
		//System.out.println(list1);
		list1.forEach(policy->{
			System.out.println(policy);
		});
		policy po1 = new policy(5,"ultra premium",1200000,40000);
		userRepo.save(po1);
		
		Optional<policy> po2 = userRepo.findById(5);
		policy po3 = po2.get();
		
		po3.setPolicy_name("Ultra Premium care plan");
		userRepo.save(po3);
	}
	

	
	
	

}
