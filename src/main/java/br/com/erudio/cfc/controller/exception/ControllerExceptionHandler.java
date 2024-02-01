package br.com.erudio.cfc.controller.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.erudio.cfc.exception.ObjectNotFoundException;
import br.com.erudio.cfc.exception.RequestWithNullObjectException;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-01
 */

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandadError> genericErroHandler(Exception ex, WebRequest request) {
		StandadError err = new StandadError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
				request.getDescription(false), Instant.now());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandadError> notFoundErroHandler(ObjectNotFoundException ex, WebRequest request) {
		StandadError err = new StandadError(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getDescription(false), Instant.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(RequestWithNullObjectException.class)
	public ResponseEntity<StandadError> objectNullErroHandler(RequestWithNullObjectException ex, WebRequest request) {
		StandadError err = new StandadError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				request.getDescription(false), Instant.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
