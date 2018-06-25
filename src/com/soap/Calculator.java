package com.soap;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;

@WebService
public class Calculator {
	
	
	
	@WebMethod
	public int add(int a, int b) {
		return a + b;
	}

	@WebMethod
	public int sub(int a, int b) {
		return a - b;
	}
	
	@WebMethod
	public int mul(int a, int b) {
		return a * b;
	}
	
	@WebMethod
	public int div(int a, int b) {
		return a / b;
	}
}
