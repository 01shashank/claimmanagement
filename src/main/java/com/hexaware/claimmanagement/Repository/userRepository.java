package com.hexaware.claimmanagement.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Entity.Role;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	@Query("select u from User u where u.user_Email=?1")
	public User findByuserEmail(String userEmail);
	
	@Query("select r from Role r where r.role_name='USER'")
	public Role getRole();
	
	
	

}
