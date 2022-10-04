package com.hexaware.claimmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Claim;

@Repository
public interface claimRepository extends JpaRepository<Claim,Integer>{

}
