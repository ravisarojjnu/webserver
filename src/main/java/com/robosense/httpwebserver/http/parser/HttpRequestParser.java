package com.robosense.httpwebserver.http.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.robosense.httpwebserver.http.HttpMethod;
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

	public Request getRequest() {
		return request;
	}

	public void parse() throws Exception {

		BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		String firstLine = reader.readLine();
		if (firstLine != null) {
			this.parseRequestLine(firstLine);
			log.info("Received Request: " + request.getMethod() + " " + request.getPath() + " "
					+ request.getProtocalVersion());
			if (request.getMethod().equals(HttpMethod.UNRECOGNIZED))
				throw new UnrecognizedHttpMethod("Invalid HTTP 1.1 request. Request Method is UNRECOGNIZED");

			this.parseHeader(reader);
			request.setPayloadBuffer(reader);
			String remoteAddress = this.getHostInfo();
			request.setRemoteAddress(remoteAddress);
		} else {
			request.setMethod(HttpMethod.UNRECOGNIZED);
			log.error("server recived null request: ");
		}
	}
	
	private String getHostInfo() {
		InetAddress remoteSocket = socket.getInetAddress();
		return remoteSocket.getHostAddress();
		
	}

	private void parseRequestLine(String firstLine) throws Exception {

		String[] tokens = firstLine.split("\\s+");
		HttpMethod method;
		if (tokens.length < 3) {
			log.debug("Received: " + firstLine);
			throw new UnrecognizedHttpMethod("Invalid HTTP 1.1 request");
		}
		try {

			method = HttpMethod.valueOf(tokens[0]);
		} catch (Exception e) {
			method = HttpMethod.UNRECOGNIZED;
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
	
	public String toString() {
		
		
		return null;
		
	}

}
