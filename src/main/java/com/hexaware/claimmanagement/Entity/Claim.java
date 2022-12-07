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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "claim_id")
@Entity
@Transactional
public class Claim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int claim_id;
	
	@ManyToOne
	@JoinColumn(name = "user_Id")
	@JsonIgnore
	private User user;
	
	private String claim_status;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Hospitalization hospitalization;
	
	@OneToMany( cascade = CascadeType.ALL)
	private List<Document> doc;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Policy policy;
	
	private String claim_rejection_reason;
	
	

	public Claim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Claim(int claim_id, User user, String claim_status, Hospitalization hospitalization, List<Document> doc,
			Policy policy, String claim_rejection_reason) {
		super();
		this.claim_id = claim_id;
		this.user = user;
		this.claim_status = claim_status;
		this.hospitalization = hospitalization;
		this.doc = doc;
		this.policy = policy;
		this.claim_rejection_reason = claim_rejection_reason;
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

	public String getClaim_status() {
		return claim_status;
	}

	public void setClaim_status(String claim_status) {
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

	public String getClaim_rejection_reason() {
		return claim_rejection_reason;
	}

	public void setClaim_rejection_reason(String claim_rejection_reason) {
		this.claim_rejection_reason = claim_rejection_reason;
	}
	
	
	
}
