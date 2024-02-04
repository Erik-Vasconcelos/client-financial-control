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

/**
 * @author Erik Vasconcelos
 * @since 2024-01-31
 */

@RestController
@RequestMapping("/clients/v1")
public class ClientControllerV1 {

	@Autowired
	private ClientServiceV1 clientServiceV1;

	@GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTOV1> getClient(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(clientServiceV1.findById(id));
	}

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientDTOV1>> getClients() {
		return ResponseEntity.status(HttpStatus.OK).body(clientServiceV1.findAll());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTOV1> create(@RequestBody ClientDTOV1 client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientServiceV1.insert(client));
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTOV1> update(@RequestBody ClientDTOV1 client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientServiceV1.update(client));
	}

	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		clientServiceV1.delete(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
