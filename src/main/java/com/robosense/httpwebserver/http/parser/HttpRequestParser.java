package com.robosense.httpwebserver.http.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.robosense.httpwebserver.http.Method;
import com.robosense.httpwebserver.http.Request;
import com.robosense.httpwebserver.http.exception.UnrecognizedHttpMethod;

public class HttpRequestParser {

	private static Logger log = Logger.getLogger(HttpRequestParser.class);

	private Socket socket;
	private Request request;

	public HttpRequestParser(Socket socket) {
		this.socket = socket;
		this.request = new Request();

	}

	public void parse() throws Exception {
//		InputStreamReader red = new InputStreamReader(this.socket.getInputStream());
//		int ch = red.read();
//		while(ch!=-1) {
//			System.out.print((char)ch);
//			ch = red.read();
//		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		String firstLine = reader.readLine();
		if (firstLine != null) {
			this.parseRequestLine(firstLine);
			log.info("Received Request: " + request.getMethod() + " " + request.getPath() + " "
					+ request.getProtocalVersion());
			if (request.getMethod().equals(Method.UNRECOGNIZED))
				throw new UnrecognizedHttpMethod("Invalid HTTP 1.1 request. Request Method is UNRECOGNIZED");

			this.parseHeader(reader);
			request.setPayloadBuffer(reader);
		} else {
			log.debug("server recived null request: ");
		}
	}

	private void parseRequestLine(String firstLine) throws Exception {

		String[] tokens = firstLine.split("\\s+");
		Method method;
		if (tokens.length < 3) {
			log.debug("Received: " + firstLine);
			throw new UnrecognizedHttpMethod("Invalid HTTP 1.1 request");
		}
		try {

			method = Method.valueOf(tokens[0]);
		} catch (Exception e) {
			method = Method.UNRECOGNIZED;
			log.error(e);
			log.debug(firstLine);
		}
		request.setMethod(method);
		request.setPath(tokens[1]);
		request.setProtocalVersion(tokens[2]);

	}

	private void parseHeader(BufferedReader reader) throws IOException {
		String str = reader.readLine();
		Map<String, String> header = new HashMap<>();
		while (!str.equals("")) {
			String[] tokens = str.split(": ");
			if (tokens.length > 1)
				header.put(tokens[0], String.join("", Arrays.copyOfRange(tokens, 1, tokens.length)));
			else
				header.put(tokens[0], null);
			str = reader.readLine();
		}
		
		request.setHeader(header);
		
	}

}