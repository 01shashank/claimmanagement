package com.hexaware.claimmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.policy;
import com.hexaware.claimmanagement.Entity.user;

@Repository
public interface userRepository extends JpaRepository<user,Integer>{
	
	

}
