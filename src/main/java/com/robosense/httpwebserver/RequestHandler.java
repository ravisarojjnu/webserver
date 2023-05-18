package com.robosense.httpwebserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.log4j.Logger;

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
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			log.debug("server recived 1: " + reader.readLine()+ requestId);
			log.debug("server recived 2: " + reader.readLine()+ requestId);
			log.debug("server recived 3: " + reader.readLine()+ requestId);
			socket.close();
		} catch (Exception e) {
			log.error("Runtime Error", e);
		}
		
	}

}
