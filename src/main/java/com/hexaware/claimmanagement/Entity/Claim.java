package com.hexaware.claimmanagement.Entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
		   property = "claim_id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int claim_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_Id")
	private User user;
	
	private String claim_status;
	
	@OneToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private Hospitalization hospitalization;
	
	@OneToMany( cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private List<Document> doc;
	
	@OneToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private Policy policy;
	
	private String claim_rejection_reason;
	
	
}
