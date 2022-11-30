package com.hexaware.claimmanagement.ExceptionHandling;

public class ExceptionResponse {
	
	private  String errormessage;
	
	private  String requestedURI;

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getRequestedURI() {
		return requestedURI;
	}

	public void setRequestedURI(String requestedURI) {
		this.requestedURI = requestedURI;
	}

	
	
	
	

}
