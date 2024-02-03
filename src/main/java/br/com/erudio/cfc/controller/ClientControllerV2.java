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

import br.com.erudio.cfc.dto.v2.ClientDTOV2;
import br.com.erudio.cfc.service.ClientServiceV2;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-02
 */

@RestController
@RequestMapping("/clients/v2")
public class ClientControllerV2 {

	@Autowired
	private ClientServiceV2 clientService;

	@GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTOV2> getPerson(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
	}

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientDTOV2>> getPerson() {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTOV2> create(@RequestBody ClientDTOV2 client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.insert(client));
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTOV2> update(@RequestBody ClientDTOV2 client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.update(client));
	}

	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		clientService.delete(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
