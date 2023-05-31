package com.robosense.httpwebserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.robosense.httpwebserver.config.CommonConfig;
import com.robosense.httpwebserver.handler.ApiRequestHandler;
import com.robosense.httpwebserver.handler.Handler;
import com.robosense.httpwebserver.handler.StaticContentHandler;
import com.robosense.httpwebserver.http.Method;
import com.robosense.httpwebserver.http.Request;
import com.robosense.httpwebserver.http.Response;
import com.robosense.httpwebserver.http.parser.HttpRequestParser;

public class RequestHandler implements Runnable {

	private static Logger log = Logger.getLogger(RequestHandler.class);

	private Socket socket;

	private String requestId;

	public RequestHandler(Socket socket, String requestId) {
		this.socket = socket;
		this.requestId = requestId;
	}

	@Override
	public void run() {
		try {
			Response response=new Response();
			response.setOutputStream(socket.getOutputStream());
			HttpRequestParser httpReqParser = new HttpRequestParser(socket);
			httpReqParser.parse();
			Request request = httpReqParser.getRequest();
			if (!request.getMethod().equals(Method.UNRECOGNIZED)) {
				String requestType = this.getRequestType(request);
				printRequest(request, requestType);
				if (requestType.equals("STAIC_CONTENT")) {
					Handler handler = new StaticContentHandler();
					handler.handle(request,response);

				} else {
					Handler handler = new ApiRequestHandler();
				}
			}
		} catch (Exception e) {
			Response response=new Response();
			try {
				
				response.setOutputStream(socket.getOutputStream());
				setServerError(response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			log.error("Runtime Error", e);
		}
		finally{
			
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private String getRequestType(Request request) {
		Properties properties = CommonConfig.properties;

		String apiBase = (String) properties.get("server.api_base");
		String path = request.getPath();
		if (path.contains(apiBase)) {
			return "API";
		} else {
			return "STAIC_CONTENT";
		}

	}

	private void printRequest(Request request, String requestType) {
		StringBuilder message = new StringBuilder();
		Date timestamp = new java.util.Date();
		message.append("[");
		message.append(timestamp.toString());
		message.append("] Received Request: ");
		message.append("Request Type[");
		message.append(requestType);
		message.append("] ");
		message.append("HOST[");
		message.append(request.getRemoteAddress());
		message.append("] | Method[");
		message.append(request.getMethod());
		message.append("] | Path[");
		message.append(request.getPath());
		message.append("] ");
		log.info(message);

	}
	
	private void setServerError(Response response) throws IOException {
		
		OutputStream outputStream = response.getOutputStream();
		
		String responseStr="HTTP/1.1 500 Internal Server Error\r\n"+
				"Content-Type: text/plain\r\n"+
				"Content-length: 21\r\n"+
				"\r\n"+
				"Internal Server Error";
		outputStream.write(responseStr.getBytes());
		log.info(responseStr);
		
	}

}
