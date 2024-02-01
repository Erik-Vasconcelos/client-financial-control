package br.com.erudio.cfc.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.cfc.model.Client;
import br.com.erudio.cfc.repository.ClientRepository;

/**
 * @author Erik Vasconcelos
 * @since 31/01/2024
 */

@Service
public class ClientService {

	private Logger logger = Logger.getLogger(ClientService.class.getName());
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client create(Client client) {
		logger.info("Create one client");
		
		return clientRepository.save(client);
	}
	
	public Client update(Client client) {
		logger.info("update one client");
		
		return clientRepository.save(client);
	}
	
	public Client findById(Long id) {
		logger.info("find one client");
		
		return clientRepository.findById(id).get();
	}
	
	public List<Client> findAll() {
		logger.info("Find all clients");
		
		return clientRepository.findAll();
	}
	
	public void delete(Long id) {
		logger.info("Delete one client");
		
		clientRepository.deleteById(id);
	}
	
	
}
