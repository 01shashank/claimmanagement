package com.hexaware.claimmanagement.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policy_Id;
	
	@Column(name="name")
	private String policy_name;
	
	@Column(name="coverage")
	private int policy_coverage;
	
	@Column(name="premium")
	private int policy_premium;
	
	public Policy(int policy_id, String policy_name, int policy_coverage, int policy_premium) {
		super();
		policy_Id = policy_id;
		this.policy_name = policy_name;
		this.policy_coverage = policy_coverage;
		this.policy_premium = policy_premium;
	}

	public Policy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPolicy_Id() {
		return policy_Id;
	}

	public void setPolicy_Id(int policy_Id) {
		this.policy_Id = policy_Id;
	}

	public String getPolicy_name() {
		return policy_name;
	}

	public void setPolicy_name(String policy_name) {
		this.policy_name = policy_name;
	}

	public int getPolicy_coverage() {
		return policy_coverage;
	}

	public void setPolicy_coverage(int policy_coverage) {
		this.policy_coverage = policy_coverage;
	}

	public int getPolicy_premium() {
		return policy_premium;
	}

	public void setPolicy_premium(int policy_premium) {
		this.policy_premium = policy_premium;
	}
	

	

	
	
	
	

}
