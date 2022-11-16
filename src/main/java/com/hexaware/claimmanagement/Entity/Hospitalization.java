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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Hospitalization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hospital_Id;
	
	@OneToOne(mappedBy="hospitalization",fetch=FetchType.LAZY)
	private Claim claim;
	
	private String hospital_doctor;
	
	private int hospital_medical_expenses;
	
	private int hospital_non_medical_expenses;
	
	private String hospital_reason;



	public Hospitalization() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Hospitalization(Claim claim, String hospital_doctor, int hospital_medical_expenses,
			int hospital_non_medical_expenses, String hospital_reason) {
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
