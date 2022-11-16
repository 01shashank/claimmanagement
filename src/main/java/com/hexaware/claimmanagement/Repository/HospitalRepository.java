package com.hexaware.claimmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Hospitalization;

@Repository
public interface HospitalRepository extends JpaRepository<Hospitalization,Integer> {
	
	@Modifying
	@Query(value="delete from Hospitalization where hospital_id = ?1", nativeQuery=true)
	void deleteById (int id);

}
