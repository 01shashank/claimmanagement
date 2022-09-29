package com.hexaware.claimmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.policy;

@Repository
public interface policyRepository extends JpaRepository<policy,Integer>{
	
	public List<policy> findBypolicyName(String policy_name);

}
