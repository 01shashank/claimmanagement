package com.hexaware.claimmanagement.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policy_Id;
	
	private String policyName;
	
	private int policy_coverage;
	
	private int policy_premium;
	
	private String policy_type;
	
	private int top_up_amount;
	
	private int policy_personal_accidental_cover;
	
	private String policy_user_nominee_name;
	
	private int policy_user_nominee_age;
	
	private String policy_user_nominee_relationship;
	
	private Date policy_start_date;
	
	private Date policy_end_date;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@OneToOne(mappedBy="policy",fetch= FetchType.LAZY)
	private Claim claim;
	
	
	public Policy() {
		super();
	}

	public Policy( String policyName, int policy_coverage, int policy_premium, String policy_type,
			int top_up_amount, int policy_personal_accidental_cover, String policy_user_nominee_name,
			int policy_user_nominee_age, String policy_user_nominee_relationship, Date policy_start_date,
			Date policy_end_date,List<User> policy_users, List<Claim> policy_claims) {
		super();
		this.policyName = policyName;
		this.policy_coverage = policy_coverage;
		this.policy_premium = policy_premium;
		this.policy_type = policy_type;
		this.top_up_amount = top_up_amount;
		this.policy_personal_accidental_cover = policy_personal_accidental_cover;
		this.policy_user_nominee_name = policy_user_nominee_name;
		this.policy_user_nominee_age = policy_user_nominee_age;
		this.policy_user_nominee_relationship = policy_user_nominee_relationship;
		this.policy_start_date = policy_start_date;
		this.policy_end_date = policy_end_date;
	}

	public int getPolicy_Id() {
		return policy_Id;
	}

	public void setPolicy_Id(int policy_Id) {
		this.policy_Id = policy_Id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
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

	public String getPolicy_type() {
		return policy_type;
	}

	public void setPolicy_type(String policy_type) {
		this.policy_type = policy_type;
	}

	public int getTop_up_amount() {
		return top_up_amount;
	}

	public void setTop_up_amount(int top_up_amount) {
		this.top_up_amount = top_up_amount;
	}

	public int getPolicy_personal_accidental_cover() {
		return policy_personal_accidental_cover;
	}

	public void setPolicy_personal_accidental_cover(int policy_personal_accidental_cover) {
		this.policy_personal_accidental_cover = policy_personal_accidental_cover;
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

	public Date getPolicy_start_date() {
		return policy_start_date;
	}

	public void setPolicy_start_date(Date policy_start_date) {
		this.policy_start_date = policy_start_date;
	}

	public Date getPolicy_end_date() {
		return policy_end_date;
	}

	public void setPolicy_end_date(Date policy_end_date) {
		this.policy_end_date = policy_end_date;
	}


	
}
