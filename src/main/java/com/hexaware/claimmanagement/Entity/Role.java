package com.hexaware.claimmanagement.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
public class Role{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int role_Id;
	
	@Column(name="name")
	private String role_name;
	
	public Role() {
		super();
	}

	


	public Role(int role_Id, String role_name) {
		super();
		this.role_Id = role_Id;
		this.role_name = role_name;
	}




	public int getRole_Id() {
		return role_Id;
	}

	public void setRole_Id(int role_Id) {
		this.role_Id = role_Id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	
}
