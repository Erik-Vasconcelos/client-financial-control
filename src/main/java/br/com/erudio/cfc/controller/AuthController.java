package br.com.erudio.cfc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.cfc.dto.AccountCredentialsDTO;
import br.com.erudio.cfc.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoint for authentication client")
public class AuthController {

	@Autowired
	private AuthService sevice;

 	@Operation(summary="Signin for return a token")
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO data) {
		if (checkIfParamsNonNull(data))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client requests");

		var token = sevice.signin(data);
		if (token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client requests");

		return ResponseEntity.ok(token);
	}

 	@Operation(summary="Refrest token for return a token")
	@PutMapping("/refresh/{username}")
	public ResponseEntity<?> signin(@PathVariable(name = "username") String username,
			@RequestHeader(name = "Authorization") String refreshToken) {
		if (checkIfParamsNonNull(username, refreshToken))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client requests");

		var token = sevice.refreshToken(username, refreshToken);
		if (token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client requests");

		return ResponseEntity.ok(token);
	}

	private boolean checkIfParamsNonNull(String username, String refreshToken) {
		return username == null || username.isBlank() || refreshToken == null || refreshToken.isBlank();
	}

	private boolean checkIfParamsNonNull(AccountCredentialsDTO data) {
		return data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null
				|| data.getPassword().isBlank();
	}
}
