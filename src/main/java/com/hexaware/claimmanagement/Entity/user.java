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


@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "user_Id")
@Entity
@Transactional
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

	
	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	private List<Policy> user_policies;
	
	
	@OneToMany(mappedBy="user",cascade = CascadeType.REMOVE)
	private List<Claim> user_claims;
	
	@ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinTable(
			  name = "user_roles", 
			  joinColumns = @JoinColumn(name = "user_Id"), 
			  inverseJoinColumns = @JoinColumn(name = "role_Id"))
	private Set<Role> user_roles = new HashSet<>();
	

	public User() {
		super();
	}

	public User(int user_Id,
			@Email(message = "Email Address is not valid!") String user_Email,
			@NotNull(message = "Password can't be null") String user_password,
			@NotNull(message = "First Name can't be null") String user_first_name,
			@NotNull(message = "Last Name can't be null") String user_last_name, List<Policy> user_policies,
			List<Claim> user_claims, Set<Role> user_roles) {
		super();
		this.user_Id= user_Id;
		this.user_Email = user_Email;
		this.user_password = user_password;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_policies = user_policies;
		this.user_claims = user_claims;
		this.user_roles = user_roles;
	}
	

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public String getUser_Email() {
		return user_Email;
	}

	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}

	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
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

	public Set<Role> getUser_roles() {
		return user_roles;
	}

	public void setUser_roles(Set<Role> user_roles) {
		this.user_roles = user_roles;
	}


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
