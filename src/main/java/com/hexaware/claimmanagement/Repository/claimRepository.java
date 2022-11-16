package com.hexaware.claimmanagement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Claim_status;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.User;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Integer>{
	

	
	@Modifying
	@Query(value="delete from Claim where claim_id = ?1", nativeQuery=true)
	void deleteById(int id);
	
	@Modifying
	@Query(value="update claim set status = ?1", nativeQuery=true)
	void updateStatus (Claim_status status);
	
	@Query(value="select c from Claim c where c.claim_status=?1")
	public List<Claim> getByClaimStatus(Claim_status claim_status);
	
	@Query(value="select c.doc from Claim c where c.claim_id=?1")
	public List<Document> getFileById(int claim_id);
	
}
