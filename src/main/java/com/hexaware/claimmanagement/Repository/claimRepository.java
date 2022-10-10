package com.hexaware.claimmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Claim_status;

@Repository
public interface claimRepository extends JpaRepository<Claim,Integer>{
	

	
	@Modifying
	@Query(value="delete from Claim where claim_id = ?1", nativeQuery=true)
	void deleteById(int id);
	
	@Modifying
	@Query(value="update claim set status = ?1", nativeQuery=true)
	void updateStatus (Claim_status status);
}
