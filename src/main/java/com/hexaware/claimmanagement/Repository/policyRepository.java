package com.hexaware.claimmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.policy;

@Repository
public interface policyRepository extends JpaRepository<policy,Integer>{
		
	@Query(value="select id from policy where name=?1")
	public int getIdFromName(String policy_name);

}