package com.hexaware.claimmanagement.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Insured {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int insured_Id;
	
	@Column(name="name")
	private String insured_name;
	
	@Column(name="phone")
	private String insured_phone;
	
	@Column(name="age")
	private int insured_age;
	
	@Column(name="relationship")
	private String insured_relationship;
	
	public Insured(int insured_Id, String insured_name, String insured_phone, int insured_age,
			String insured_relationship) {
		super();
		this.insured_Id = insured_Id;
		this.insured_name = insured_name;
		this.insured_phone = insured_phone;
		this.insured_age = insured_age;
		this.insured_relationship = insured_relationship;
	}

	public Insured() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public int getInsured_Id() {
		return insured_Id;
	}

	public void setInsured_Id(int insured_Id) {
		this.insured_Id = insured_Id;
	}

	public String getInsured_name() {
		return insured_name;
	}

	public void setInsured_name(String insured_name) {
		this.insured_name = insured_name;
	}

	public String getInsured_phone() {
		return insured_phone;
	}

	public void setInsured_phone(String insured_phone) {
		this.insured_phone = insured_phone;
	}

	public int getInsured_age() {
		return insured_age;
	}

	public void setInsured_age(int insured_age) {
		this.insured_age = insured_age;
	}

	public String getInsured_relationship() {
		return insured_relationship;
	}

	public void setInsured_relationship(String insured_relationship) {
		this.insured_relationship = insured_relationship;
	}



}
