package com.robosense.httpwebserver;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;



/**
 * 
 * @author Ravi Saroj
 * 
 */
public class WebServer {
	
	private static Logger log = Logger.getLogger(WebServer.class);
	private static final String DEFAULT_PORT="8080";
	private static final int DEFAULT_NTHREAD=5;
	
	public static void main(String args[]) {
		
		Options options = new Options();

        Option port_input = new Option("p", "port", true, "port number on which webserver should listen. Valid Range is (0,65535). Default 8080");
        port_input.setOptionalArg(true);
        port_input.setType(Number.class);
        port_input.setArgs(1);
        port_input.setArgName("number (5000-65534)");
        options.addOption(port_input);
        
        Option thread_count_input = new Option("t", "nthread", true, "max number of thread webserver can create for requent handling. Default 5");
        thread_count_input.setOptionalArg(true);
        thread_count_input.setType(Number.class);
        thread_count_input.setArgs(1);
        thread_count_input.setArgName("number (1-1000)");
        options.addOption(thread_count_input);
        

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
            Number port_input_val = (Number)cmd.getParsedOptionValue("p");
            Number thread_count_input_val = (Number) cmd.getParsedOptionValue("t");
            if (port_input_val!=null) {
            	
            	if(!validatePort(port_input_val.intValue()))
            		throw new ParseException("port number should be in range (0,65535)");
            	log.info("web server will listen to the port: "+port_input_val);
            	
            }
            else 
            	log.info("web server will listen to the Default port: "+DEFAULT_PORT);
            
            if (thread_count_input_val!=null) {
            	
            	if(!validatePort(thread_count_input_val.intValue()))
            		throw new ParseException("max number of thread webserver can create is 1000)");
            	log.info("max number of thread webserver can create for request handling: "+thread_count_input_val);
            	
            }
            else {
            	log.info("max number of Default thread webserver can create for request handling: "+DEFAULT_NTHREAD);
            	thread_count_input_val=DEFAULT_NTHREAD;
            }
            
            
            	
            log.info("web server dev in progress...");
        } catch (ParseException e) {
        	
        	log.error(e);
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
		log.info(System.getProperty("user.dir"));
		
	}
	
	void start() {
		
		
		try {
			ServerSocket ss = new ServerSocket();
			log.info("max number of Default thread webserver can create for request handling: "+DEFAULT_NTHREAD);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static boolean validatePort(int port) {
		
		if (port>0 && port<65535) 
			return true;
		return false;
	
	}
	
static boolean validateThreadCount(int nthread) {
		
		if (nthread>0 && nthread<1001) 
			return true;
		return false;
	
	}
	

}
