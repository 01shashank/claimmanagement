package com.hexaware.claimmanagement.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
public class Nominee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nominee_id;
	
	@ManyToOne
	@JsonIgnore
	private Policy policy;

	@NotNull(message="Nominee name can't be empty")
	private String policy_user_nominee_name;
	
	@NotNull(message="Nominee age can't be empty")
	private int policy_user_nominee_age;
	
	@NotNull(message="Nominee Relationship can't be empty")
	private String policy_user_nominee_relationship;

	public Nominee(int nominee_id, Policy policy,
			@NotNull(message = "Nominee name can't be empty") String policy_user_nominee_name,
			@NotNull(message = "Nominee age can't be empty") int policy_user_nominee_age,
			@NotNull(message = "Nominee Relationship can't be empty") String policy_user_nominee_relationship) {
		super();
		this.nominee_id = nominee_id;
		this.policy = policy;
		this.policy_user_nominee_name = policy_user_nominee_name;
		this.policy_user_nominee_age = policy_user_nominee_age;
		this.policy_user_nominee_relationship = policy_user_nominee_relationship;
	}

	public Nominee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNominee_id() {
		return nominee_id;
	}

	public void setNominee_id(int nominee_id) {
		this.nominee_id = nominee_id;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getPolicy_user_nominee_name() {
		return policy_user_nominee_name;
	}

	public void setPolicy_user_nominee_name(String policy_user_nominee_name) {
		this.policy_user_nominee_name = policy_user_nominee_name;
	}

	public int getPolicy_user_nominee_age() {
		return policy_user_nominee_age;
	}

	public void setPolicy_user_nominee_age(int policy_user_nominee_age) {
		this.policy_user_nominee_age = policy_user_nominee_age;
	}

	public String getPolicy_user_nominee_relationship() {
		return policy_user_nominee_relationship;
	}

	public void setPolicy_user_nominee_relationship(String policy_user_nominee_relationship) {
		this.policy_user_nominee_relationship = policy_user_nominee_relationship;
	}
	
	
}
