package com.robosense.httpwebserver.http.exception;

public class UnrecognizedHttpMethod extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public UnrecognizedHttpMethod(String errmessage){
		
		super(errmessage);
	}
	

}
