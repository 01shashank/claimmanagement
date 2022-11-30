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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policy_Id;
	
	@NotNull(message="Policy name can't be empty")
	private String policyName;

	@NotNull(message="Policy coverage can't be empty")
	private int policy_coverage;
	
	@NotNull(message="Policy premium can't be empty")
	private int policy_premium;
	
	@NotNull(message="Policy Type can't be empty")
	private String policy_type;
	
	@NotNull(message="Top-up Amount can't be empty")
	private int top_up_amount;
	
	@NotNull(message="Accidental cover can't be empty")
	private int policy_personal_accidental_cover;
	
	
	@NotNull(message="Policy start date can't be empty")
	private Date policy_start_date;
	
	@NotNull(message="Policy end date can't be empty")
	private Date policy_end_date;
	
	@JsonManagedReference
	@OneToMany(mappedBy="policy",cascade=CascadeType.ALL)
	private List<Nominee> nominee;
	
	@JsonManagedReference
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@OneToOne(mappedBy="policy",fetch= FetchType.LAZY)
	private Claim claim;
	
	
}
