package com.hexaware.claimmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Insured;

@Repository
public interface insuredRepo extends JpaRepository<Insured,Integer> {
	
	@Modifying
	@Query(value="delete from Insured where insured_id = ?1", nativeQuery=true)
	void deleteById (int id);

}
