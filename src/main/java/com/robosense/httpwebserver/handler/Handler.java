package com.robosense.httpwebserver.handler;

import java.io.IOException;

import com.robosense.httpwebserver.http.Request;
import com.robosense.httpwebserver.http.Response;

public interface Handler {
	
	public void handle(Request request, Response response) throws IOException;

}
