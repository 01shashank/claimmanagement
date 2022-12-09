package com.hexaware.claimmanagement.ExceptionHandling;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -907945484961161074L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}
	
	
	

}
