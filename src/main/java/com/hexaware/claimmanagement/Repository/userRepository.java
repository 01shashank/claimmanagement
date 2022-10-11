package com.hexaware.claimmanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;

@Repository
public interface userRepository extends JpaRepository<User,Integer>{
	
	User findByuserEmail(String user_email_address);
	
	
	

}
