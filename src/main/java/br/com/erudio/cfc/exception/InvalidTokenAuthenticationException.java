package br.com.erudio.cfc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-14
 */

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class InvalidTokenAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public InvalidTokenAuthenticationException(String msg) {
		super(msg);
	}

	public InvalidTokenAuthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
