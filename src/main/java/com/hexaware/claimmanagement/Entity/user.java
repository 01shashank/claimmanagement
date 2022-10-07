package com.hexaware.claimmanagement.Entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "user_Id")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_Id")
	private int user_Id;
	
	@Column(name="first_name")
	private String user_first_name;
	
	@Column(name="last_name")
	private String usre_last_name;
	
	@Column(name="phone")
	private long user_phone;
	
	@Column(name="age")
	private int user_age;
	
	@Column(name="address")
	private String user_address;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Policy> user_policies;
	
	@OneToMany(mappedBy="user")
	@JsonManagedReference(value="user_claims")
	private List<Claim> user_claims;
	
	public User(int user_Id, String user_first_name, String usre_last_name, long user_phone, int user_age,
			String user_address, List<Policy> user_policies, List<Claim> user_claims) {
		super();
		this.user_Id = user_Id;
		this.user_first_name = user_first_name;
		this.usre_last_name = usre_last_name;
		this.user_phone = user_phone;
		this.user_age = user_age;
		this.user_address = user_address;
		this.user_policies = user_policies;
		this.user_claims = user_claims;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUsre_last_name() {
		return usre_last_name;
	}

	public void setUsre_last_name(String usre_last_name) {
		this.usre_last_name = usre_last_name;
	}

	public long getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(long user_phone) {
		this.user_phone = user_phone;
	}

	public int getUser_age() {
		return user_age;
	}

	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public List<Policy> getUser_policies() {
		return user_policies;
	}

	public void setUser_policies(List<Policy> user_policies) {
		this.user_policies = user_policies;
	}

	public List<Claim> getUser_claims() {
		return user_claims;
	}

	public void setUser_claims(List<Claim> user_claims) {
		this.user_claims = user_claims;
	}

	
}
