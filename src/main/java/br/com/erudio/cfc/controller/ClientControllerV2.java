package br.com.erudio.cfc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.cfc.dto.v2.ClientDTOV2;
import br.com.erudio.cfc.service.ClientServiceV2;
import br.com.erudio.cfc.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-02
 */

@RestController
@RequestMapping("/api/clients/v2")
@Tag(name = "Client V2", description = "Endpoint for Client version two")
public class ClientControllerV2 {

	@Autowired
	private ClientServiceV2 clientService;

	@GetMapping(value = "/{id}", produces = { MediaType.JSON, MediaType.XML, MediaType.YAML })
	@Operation(summary = "Find a client v2", description = "Find a client by id v2", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<ClientDTOV2> getClient(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
	}

	@GetMapping(produces = { MediaType.JSON, MediaType.XML, MediaType.YAML })
	@Operation(summary = "Find all clients v2", description = "Find all clients v2", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", 
					array = @ArraySchema(schema = @Schema(implementation = ClientDTOV2.class))
			)),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public ResponseEntity<List<ClientDTOV2>> getClients() {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
	}

	@PostMapping(consumes = { MediaType.JSON, MediaType.XML, MediaType.YAML }, produces = { MediaType.JSON,
			MediaType.XML, MediaType.YAML })
	@Operation(summary = "Create a client v2", description = "Create a client v2", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ClientDTOV2> create(@RequestBody ClientDTOV2 client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.insert(client));
	}

	@PutMapping(consumes = { MediaType.JSON, MediaType.XML, MediaType.YAML }, produces = { MediaType.JSON,
			MediaType.XML, MediaType.YAML })
	@Operation(summary = "Create a client v2", description = "Create a client v2", responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<ClientDTOV2> update(@RequestBody ClientDTOV2 client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.update(client));
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete client v2", description = "Delete client v2", responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		clientService.delete(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
