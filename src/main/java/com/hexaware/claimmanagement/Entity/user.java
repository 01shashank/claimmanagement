package com.hexaware.claimmanagement.Entity;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "user_Id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_Id;
	
	@Column(name="email", unique=true)
	@Email(message="Email Address is not valid!")
	private String user_Email;
	
	@Column(name="password",unique=true)
	@NotNull(message="Password can't be null")
	private String user_password;
	
	@NotNull(message="First Name can't be null")
	private String user_first_name;
	
	@NotNull(message="Last Name can't be null")
	private String user_last_name;

	@JsonBackReference
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Policy> user_policies;
	
	//FetchType is lazy as we need not to load the data at time of entering a user.
	//FetchType needs to be set to Eager when we need to load the data at time of entering a user.
	//JsonManagedReference is a forward reference used for serialization and JsonBackReference is omitted during serialization.
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	//@JsonManagedReference(value="user_claims")
	private List<Claim> user_claims;
	
	//ManyToMany Relationship needs to be written with JoinTable annotation
	@ManyToMany(fetch= FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinTable(
			  name = "user_roles", 
			  joinColumns = @JoinColumn(name = "user_Id"), 
			  inverseJoinColumns = @JoinColumn(name = "role_Id"))
	@JsonIgnore
	private Set<Role> user_roles = new HashSet<>();
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<SimpleGrantedAuthority> authorities =this.user_roles.stream().map((role)-> new SimpleGrantedAuthority(role.getRole_name())).collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user_password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user_Email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	
}
