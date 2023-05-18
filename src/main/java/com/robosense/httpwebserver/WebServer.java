package com.robosense.httpwebserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Ravi Saroj
 * 
 */
public class WebServer {

	private static Logger log = Logger.getLogger(WebServer.class);
	private static final int DEFAULT_PORT = 8080;
	private static final int DEFAULT_NTHREAD = 5;

	public static void main(String args[]){

		Options options = new Options();

		Option port_input = new Option("p", "port", true,
				"port number on which webserver should listen. Valid Range is (0,65535). Default 8080");
		port_input.setOptionalArg(true);
		port_input.setType(Number.class);
		port_input.setArgs(1);
		port_input.setArgName("number (5000-65534)");
		options.addOption(port_input);

		Option thread_count_input = new Option("t", "nthread", true,
				"max number of thread webserver can create for requent handling. Default 5");
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
			Number port_input_val = (Number) cmd.getParsedOptionValue("p");
			Number thread_count_input_val = (Number) cmd.getParsedOptionValue("t");
			if (port_input_val != null) {

				if (!validatePort(port_input_val.intValue()))
					throw new ParseException("port number should be in range (0,65535)");
				log.info("web server will listen to the port: " + port_input_val);

			} else {
				log.info("web server will listen to the Default port: " + DEFAULT_PORT);
				port_input_val = DEFAULT_PORT;
			}

			if (thread_count_input_val != null) {

				if (!validatePort(thread_count_input_val.intValue()))
					throw new ParseException("max number of thread webserver can create is 1000)");
				log.info("max number of thread webserver can create for request handling: " + thread_count_input_val);

			} else {
				log.info("max number of Default thread webserver can create for request handling: " + DEFAULT_NTHREAD);
				thread_count_input_val = DEFAULT_NTHREAD;
			}

			new WebServer().start(port_input_val.intValue(), thread_count_input_val.intValue());

			log.info("web server dev in progress...");
		} catch (ParseException e) {

			log.error(e);
			formatter.printHelp("utility-name", options);
			System.exit(1);
		}
		
		catch(Exception e) {
			log.error(e);
			e.printStackTrace();
			System.exit(1);
		}
		

	}

	void start(int port, int thread) throws IOException {

		try {
			ServerSocket ss = new ServerSocket(port);
			log.info("Webserver started to listening on  port: " + port);
			log.info("To stop the server press ctrl+c ");
			ExecutorService exexutorService = Executors.newFixedThreadPool(thread);
			
			while(true) {
				String requestId = UUID.randomUUID().toString();
				exexutorService.submit(new RequestHandler(ss.accept(),requestId));
			}
			
			

		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		}
	}

	static boolean validatePort(int port) {

		if (port > 0 && port < 65535)
			return true;
		return false;

	}

	static boolean validateThreadCount(int nthread) {

		if (nthread > 0 && nthread < 1001)
			return true;
		return false;

	}

}
