package com.hexaware.claimmanagement.Entity;

import java.time.LocalDate;
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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policy_Id;
	
	@NotNull(message="Policy name can't be empty")
	private String policyName;

	private int policy_coverage;
	
	private int policy_premium;
	
	
	@NotNull(message="Top-up Amount can't be empty")
	private int top_up_amount;
	
	@NotNull(message="Accidental cover can't be empty")
	private int policy_personal_accidental_cover;
	
	
	@NotNull(message="Policy start date can't be empty")
	private LocalDate policy_start_date;
	
	@NotNull(message="Policy end date can't be empty")
	private LocalDate policy_end_date;
	
	
	@OneToMany(mappedBy="policy",cascade=CascadeType.REMOVE)
	private List<Nominee> nominee;
	
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@OneToOne(mappedBy="policy")
	@JsonIgnore
	private Claim claim;

	public Policy() {
		super();
	}

	public Policy(@NotNull(message = "Policy name can't be empty") String policyName,
			@NotNull(message = "Policy coverage can't be empty") int policy_coverage,
			@NotNull(message = "Policy premium can't be empty") int policy_premium,
			@NotNull(message = "Top-up Amount can't be empty") int top_up_amount,
			@NotNull(message = "Accidental cover can't be empty") int policy_personal_accidental_cover,
			@NotNull(message = "Policy start date can't be empty") LocalDate policy_start_date,
			@NotNull(message = "Policy end date can't be empty") LocalDate policy_end_date, List<Nominee> nominee, User user,
			Claim claim) {
		super();
		this.policyName = policyName;
		this.policy_coverage = policy_coverage;
		this.policy_premium = policy_premium;
		this.top_up_amount = top_up_amount;
		this.policy_personal_accidental_cover = policy_personal_accidental_cover;
		this.policy_start_date = policy_start_date;
		this.policy_end_date = policy_end_date;
		this.nominee = nominee;
		this.user = user;
		this.claim = claim;
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

	public LocalDate getPolicy_start_date() {
		return policy_start_date;
	}

	public void setPolicy_start_date(LocalDate policy_start_date) {
		this.policy_start_date = policy_start_date;
	}

	public LocalDate getPolicy_end_date() {
		return policy_end_date;
	}

	public void setPolicy_end_date(LocalDate policy_end_date) {
		this.policy_end_date = policy_end_date;
	}

	public List<Nominee> getNominee() {
		return nominee;
	}

	public void setNominee(List<Nominee> nominee) {
		this.nominee = nominee;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}
	
	
	
}
