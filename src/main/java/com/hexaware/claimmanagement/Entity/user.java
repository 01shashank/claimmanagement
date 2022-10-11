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

@Entity
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "user_Id")
@Transactional
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_Id")
	private int user_Id;
	
	@Column(name="email")
	private String userEmail;
	
	@Column(name="password")
	private String user_password;
	
	@Column(name="first_name")
	private String user_first_name;
	
	@Column(name="last_name")
	private String usre_last_name;
	
	@ManyToMany
	@JsonIgnore
	private List<Policy> user_policies;
	
	@OneToMany(mappedBy="user",fetch = FetchType.EAGER)
	@JsonManagedReference(value="user_claims")
	private List<Claim> user_claims;
	
	@ManyToMany(fetch= FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Set<Role> user_roles = new HashSet<>();
	
	public User(int user_Id, String userEmail, String user_password, String user_first_name,
			String usre_last_name, List<Policy> user_policies, List<Claim> user_claims, Set<Role> user_roles) {
		super();
		this.user_Id = user_Id;
		this.userEmail = userEmail;
		this.user_password = user_password;
		this.user_first_name = user_first_name;
		this.usre_last_name = usre_last_name;
		this.user_policies = user_policies;
		this.user_claims = user_claims;
		this.user_roles = user_roles;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	public String getUsre_last_name() {
		return usre_last_name;
	}

	public void setUsre_last_name(String usre_last_name) {
		this.usre_last_name = usre_last_name;
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
		return this.userEmail;
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
