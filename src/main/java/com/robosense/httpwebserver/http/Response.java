package com.robosense.httpwebserver.http;

import java.io.IOException;
import java.io.OutputStream;

public class Response {
	private OutputStream outputStream;
	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

}
