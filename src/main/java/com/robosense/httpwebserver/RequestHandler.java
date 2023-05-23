package com.robosense.httpwebserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.robosense.httpwebserver.http.parser.HttpRequestParser;

public class RequestHandler implements Runnable {
	
	private static Logger log = Logger.getLogger(RequestHandler.class);

	private Socket socket;

	private String requestId;

	public RequestHandler(Socket socket, String requestId) {
		this.socket = socket;
		this.requestId=requestId;
	}

	@Override
	public void run() {
		try {
		
			HttpRequestParser httpReqParser = new HttpRequestParser(socket);
			httpReqParser.parse();
			socket.close();
		}
		catch(Exception e){
		
			
			log.error("Runtime Error", e);
		}
		
	}
	
	String getRequestType() {
		
		return requestId;
		
		
	}

}
