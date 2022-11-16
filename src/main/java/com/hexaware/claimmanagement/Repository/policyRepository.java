package com.hexaware.claimmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Integer>{
		
	@Query(value="select id from Policy where name=?1")
	public int getIdFromName(String policyName);
	
	@Query("select p from Policy p where p.policyName=?1")
	public Policy findByPolicyName(String policyName);

}