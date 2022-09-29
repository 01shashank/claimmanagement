package com.hexaware.claimmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.claimmanagement.Entity.policy;

public interface userRepository extends JpaRepository<policy,Integer>{
	
	

}
