package com.robosense.httpwebserver.http;

public enum Method {
	GET("GET"), 
	HEAD("HEAD"),
	POST("POST"), 
	PUT("PUT"), 
	DELETE("DELETE"),
	TRACE("TRACE"), 
	CONNECT("CONNECT"), 
	UNRECOGNIZED(null); 
	
	private final String method;
	
	Method(String method){
		
		this.method=method;
		
	}

	public String getMethod() {
		return method;
	}

}
