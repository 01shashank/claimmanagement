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

@Entity
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

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Document(String docname, String doctype, byte[] data) {
		super();
		this.docname = docname;
		this.doctype = doctype;
		this.data = data;
	}

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	
}
