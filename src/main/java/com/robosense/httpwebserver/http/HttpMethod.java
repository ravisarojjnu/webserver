package com.robosense.httpwebserver.http;

public enum HttpMethod {
	GET("GET"), 
	HEAD("HEAD"),
	POST("POST"), 
	PUT("PUT"), 
	DELETE("DELETE"),
	TRACE("TRACE"), 
	CONNECT("CONNECT"), 
	UNRECOGNIZED(null); 
	
	private final String method;
	
	HttpMethod(String method){
		
		this.method=method;
		
	}

	public String getMethod() {
		return method;
	}

}
