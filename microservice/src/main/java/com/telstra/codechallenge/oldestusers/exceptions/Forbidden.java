package com.telstra.codechallenge.oldestusers.exceptions;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import io.micrometer.common.lang.Nullable;

public class Forbidden extends HttpClientErrorException {


	private static final long serialVersionUID = 1L;

	public Forbidden(String statusText, HttpHeaders headers, byte[] body,
			Charset responseCharset) {
		super(HttpStatus.FORBIDDEN, statusText, headers, body, responseCharset);
		// TODO Auto-generated constructor stub
	}

	public Forbidden(String message, String statusText, HttpHeaders headers, byte[] body,@Nullable Charset responseCharset) {
		super(message,HttpStatus.FORBIDDEN, statusText, headers, body, responseCharset);
	}

	
}
