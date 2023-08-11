package com.telstra.codechallenge.oldestusers.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ResponseErrorMessage {

	private int status;
	private Object error;
	private String message;
	public ResponseErrorMessage(int status, Object error, String message) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
	}	
}
