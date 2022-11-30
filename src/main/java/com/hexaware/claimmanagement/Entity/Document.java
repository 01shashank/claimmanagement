package com.hexaware.claimmanagement.Entity;

import java.io.StringReader;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int doc_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Claim claim;
	
	private String docname;
	
	private String doctype;
	
	@Lob
	private byte[] data;

}
