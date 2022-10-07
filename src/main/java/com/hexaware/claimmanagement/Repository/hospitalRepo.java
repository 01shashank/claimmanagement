package com.hexaware.claimmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Hospitalization;

@Repository
public interface hospitalRepo extends JpaRepository<Hospitalization,Integer> {

}
