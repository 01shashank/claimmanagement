package com.hexaware.claimmanagement.Entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "claim_id")
@Transactional
public class Claim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int claim_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value="user_claims")
	@JoinColumn(name = "user_Id")
	private User user;
	
	@Column(name="status")
	private Claim_status claim_status;

	
	@OneToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private Hospitalization hospitalization;
	
	@OneToMany( cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private List<Document> doc;
	
	@OneToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private Policy policy;
	
	public Claim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Claim( User user, Claim_status claim_status,
			Hospitalization hospitalization, List<Document> doc, Policy policy) {
		super();
		this.user = user;
		this.claim_status = claim_status;
		this.hospitalization = hospitalization;
		this.doc = doc;
		this.policy=policy;
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

	public Hospitalization getHospitalization() {
		return hospitalization;
	}

	public void setHospitalization(Hospitalization hospitalization) {
		this.hospitalization = hospitalization;
	}

	public List<Document> getDoc() {
		return doc;
	}

	public void setDoc(List<Document> doc) {
		this.doc = doc;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
	
	
}
