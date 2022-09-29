package com.hexaware.claimmanagement.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="name")
	private String policyName;
	
	@Column(name="coverage")
	private int policy_coverage;
	
	@Column(name="premium")
	private int policy_premium;
	
	public policy(int id, String policyName, int policy_coverage, int policy_premium) {
		super();
		Id = id;
		this.policyName = policyName;
		this.policy_coverage = policy_coverage;
		this.policy_premium = policy_premium;
	}

	public policy() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicy_name(String policyName) {
		this.policyName = policyName;
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
