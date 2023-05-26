package com.robosense.httpwebserver.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.robosense.httpwebserver.config.CommonConfig;
import com.robosense.httpwebserver.http.Request;
import com.robosense.httpwebserver.http.Response;

public class StaticContentHandler implements Handler {

	public void handle(Request request, Response response) throws IOException {
	
		Path path=Paths.get(CommonConfig.properties.getProperty("server.data_folder"),request.getPath());
		OutputStream outputStream= response.getOutputStream();
	
		if(Files.exists(path)) {
			byte[] content = Files.readAllBytes(path);
			String mimeType=Files.probeContentType(path);
			long contentLength = Files.size(path);
			outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
			outputStream.write(("Content-Type: "+mimeType+"\r\n").getBytes());
			outputStream.write(("Content-Length: "+contentLength+"\r\n").getBytes());
			outputStream.write(("\r\n").getBytes());
			outputStream.write(content);
		}
		else {
			String responseStr="HTTP/1.1 404 Not Found\r\n"+
								"Content-Type: text/plain\r\n"+
								"Content-length: 9\r\n"+
								"\r\n"+
								"Not Found";
			outputStream.write(responseStr.getBytes());
		}
	}


}

