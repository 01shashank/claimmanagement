package com.hexaware.claimmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Nominee;

@Repository
public interface NomineeRepository extends JpaRepository<Nominee,Integer> {

}
