package com.hexaware.claimmanagement.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "claim_id")
public class Claim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int claim_id;
	
	@ManyToOne
	@JsonBackReference(value="user_claims")
	@JoinColumn(name = "user_Id")
	private User user;
	
	@Column(name="status")
	private Claim_status claim_status;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Policy policy;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Insured insured;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Hospitalization hospitalization;
	
	public Claim(int claim_id, User user, Claim_status claim_status, Policy policy, Insured insured,
			Hospitalization hospitalization) {
		super();
		this.claim_id = claim_id;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
