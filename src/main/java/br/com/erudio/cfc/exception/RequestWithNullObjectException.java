package br.com.erudio.cfc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-01
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequestWithNullObjectException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RequestWithNullObjectException() {
		super("Not a object null allowed");
	}

	public RequestWithNullObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestWithNullObjectException(String message) {
		super(message);
	}
	
}
