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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospitalization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hospital_Id;
	
	@OneToOne(mappedBy="hospitalization",fetch=FetchType.LAZY)
	private Claim claim;
	
	@NotNull(message="Doctor's name cannot be null")
	private String hospital_doctor;
	
	@NotNull(message="You need to enter the medical expenses")
	private int hospital_medical_expenses;
	
	@NotNull(message="You need to enter the Non-medical expense")
	private int hospital_non_medical_expenses;
	
	@NotNull(message="You need to enter the reason of hospitalization")
	private String hospital_reason;

}
