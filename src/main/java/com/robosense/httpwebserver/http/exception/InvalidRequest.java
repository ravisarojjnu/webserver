package com.robosense.httpwebserver.http.exception;

public class InvalidRequest extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidRequest(String errmessage){
		
		super(errmessage);
	}
	

}
