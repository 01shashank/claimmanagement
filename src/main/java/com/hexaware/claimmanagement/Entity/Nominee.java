package com.hexaware.claimmanagement.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nominee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nominee_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private Policy policy;

	@NotNull(message="Nominee name can't be empty")
	private String policy_user_nominee_name;
	
	@NotNull(message="Nominee age can't be empty")
	private int policy_user_nominee_age;
	
	@NotNull(message="Nominee Relationship can't be empty")
	private String policy_user_nominee_relationship;


}
