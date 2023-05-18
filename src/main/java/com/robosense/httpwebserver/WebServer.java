package com.robosense.httpwebserver;
import org.apache.log4j.Logger;

public class WebServer {
	
	private static Logger log = Logger.getLogger(WebServer.class);
	
	public static void main(String args[]) {
		System.out.println("web server dev in progress...");
		log.info("web server dev in progress...");
		log.info(System.getProperty("user.dir"));
		
	}

}
