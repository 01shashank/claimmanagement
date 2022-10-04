package com.hexaware.claimmanagement.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Claim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int claim_id;
	
	@Column(name="status")
	private Claim_status claim_status;
	
	@OneToOne
	private Policy policy;
	
	@OneToOne
	private Insured insured;
	
	@OneToOne
	private Hospitalization hospitalization;
	
	public Claim(int claim_id, Claim_status claim_status, Policy policy, Insured insured, Hospitalization hospitalization) {
		super();
		this.claim_id = claim_id;
		this.claim_status = claim_status;
		this.policy = policy;
		this.insured = insured;
		this.hospitalization = hospitalization;
	}

	public Claim() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public int getClaim_id() {
		return claim_id;
	}

	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}

	public Claim_status getClaim_status() {
		return claim_status;
	}

	public void setClaim_status(Claim_status claim_status) {
		this.claim_status = claim_status;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Insured getInsured() {
		return insured;
	}

	public void setInsured(Insured insured) {
		this.insured = insured;
	}

	public Hospitalization getHospitalization() {
		return hospitalization;
	}

	public void setHospitalization(Hospitalization hospitalization) {
		this.hospitalization = hospitalization;
	}


	

}
