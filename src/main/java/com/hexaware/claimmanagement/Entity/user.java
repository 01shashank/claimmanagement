package com.hexaware.claimmanagement.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class user {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
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
	
	@ManyToMany @Fetch(FetchMode.JOIN)
	//@Cascade({CascadeType.DETACH})
	@Column(name="policies_enrolled")
	private List<policy> user_policies;

	public user() {
		super();
		// TODO Auto-generated constructor stub
	}

	public user(int user_Id, String user_first_name, String usre_last_name, long user_phone, int user_age,
			String user_address, List<policy> user_policies) {
		super();
		this.user_Id = user_Id;
		this.user_first_name = user_first_name;
		this.usre_last_name = usre_last_name;
		this.user_phone = user_phone;
		this.user_age = user_age;
		this.user_address = user_address;
		this.user_policies = user_policies;
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

	public List<policy> getUser_policies() {
		return user_policies;
	}

	public void setUser_policies(List<policy> user_policies) {
		this.user_policies = user_policies;
	}

}
