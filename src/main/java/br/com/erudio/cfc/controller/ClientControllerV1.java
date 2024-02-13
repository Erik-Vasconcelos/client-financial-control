package br.com.erudio.cfc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.cfc.dto.v1.ClientDTOV1;
import br.com.erudio.cfc.service.ClientServiceV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Erik Vasconcelos
 * @since 2024-01-31
 */

@RestController
@RequestMapping("/api/clients/v1")
@Tag(name = "Client V1", description = "Endpoint for Client version one")
public class ClientControllerV1 {

	@Autowired
	private ClientServiceV1 clientServiceV1;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find a client", description = "Find a client by id", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ClientDTOV1> getClient(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(clientServiceV1.findById(id));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find all clients", description = "Find all clients", responses = {
		@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", 
				array = @ArraySchema(schema = @Schema(implementation = ClientDTOV1.class))
		)),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<List<ClientDTOV1>> getClients() {
		return ResponseEntity.status(HttpStatus.OK).body(clientServiceV1.findAll());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create a client", description = "Create a client", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ClientDTOV1> create(@RequestBody ClientDTOV1 client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientServiceV1.insert(client));
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create a client", description = "Create a client", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ClientDTOV1> update(@RequestBody ClientDTOV1 client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientServiceV1.update(client));
	}

	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Delete client", description = "Delete client", responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		clientServiceV1.delete(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
