package com.hexaware.claimmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

}
