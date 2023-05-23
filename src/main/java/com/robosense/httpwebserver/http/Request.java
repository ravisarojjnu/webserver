package com.robosense.httpwebserver.http;

import java.io.BufferedReader;
import java.util.Map;

/**
 * @author ravisaroj
 *
 */
public class Request {
	
	private Method method;
	private String path;
	private String protocalVersion;
	private Map<String,String> header;
	private String payload;
	private BufferedReader payloadBuffer;
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getProtocalVersion() {
		return protocalVersion;
	}
	public void setProtocalVersion(String protocalVersion) {
		this.protocalVersion = protocalVersion;
	}
	public Map<String,String> getHeader() {
		return header;
	}
	public void setHeader(Map<String,String> header) {
		this.header = header;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public BufferedReader getPayloadBuffer() {
		return payloadBuffer;
	}
	public void setPayloadBuffer(BufferedReader payloadBuffer) {
		this.payloadBuffer = payloadBuffer;
	}
	

}
