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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
public class Hospitalization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hospital_Id;
	
	@OneToOne(mappedBy="hospitalization")
	private Claim claim;
	
	@NotNull(message="Doctor's name cannot be null")
	private String hospital_doctor;
	
	@NotNull(message="You need to enter the medical expenses")
	private int hospital_medical_expenses;
	
	@NotNull(message="You need to enter the Non-medical expense")
	private int hospital_non_medical_expenses;
	
	@NotNull(message="You need to enter the reason of hospitalization")
	private String hospital_reason;

	public Hospitalization() {
		super();
	}

	public Hospitalization( Claim claim,
			@NotNull(message = "Doctor's name cannot be null") String hospital_doctor,
			@NotNull(message = "You need to enter the medical expenses") int hospital_medical_expenses,
			@NotNull(message = "You need to enter the Non-medical expense") int hospital_non_medical_expenses,
			@NotNull(message = "You need to enter the reason of hospitalization") String hospital_reason) {
		super();
		this.claim = claim;
		this.hospital_doctor = hospital_doctor;
		this.hospital_medical_expenses = hospital_medical_expenses;
		this.hospital_non_medical_expenses = hospital_non_medical_expenses;
		this.hospital_reason = hospital_reason;
	}

	public int getHospital_Id() {
		return hospital_Id;
	}

	public void setHospital_Id(int hospital_Id) {
		this.hospital_Id = hospital_Id;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public String getHospital_doctor() {
		return hospital_doctor;
	}

	public void setHospital_doctor(String hospital_doctor) {
		this.hospital_doctor = hospital_doctor;
	}

	public int getHospital_medical_expenses() {
		return hospital_medical_expenses;
	}

	public void setHospital_medical_expenses(int hospital_medical_expenses) {
		this.hospital_medical_expenses = hospital_medical_expenses;
	}

	public int getHospital_non_medical_expenses() {
		return hospital_non_medical_expenses;
	}

	public void setHospital_non_medical_expenses(int hospital_non_medical_expenses) {
		this.hospital_non_medical_expenses = hospital_non_medical_expenses;
	}

	public String getHospital_reason() {
		return hospital_reason;
	}

	public void setHospital_reason(String hospital_reason) {
		this.hospital_reason = hospital_reason;
	}
	
	
	
}
