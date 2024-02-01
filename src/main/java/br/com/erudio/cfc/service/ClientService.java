package br.com.erudio.cfc.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.cfc.dto.ClientDTO;
import br.com.erudio.cfc.mapper.Mapper;
import br.com.erudio.cfc.model.Client;
import br.com.erudio.cfc.repository.ClientRepository;

/**
 * @author Erik Vasconcelos
 * @since 2024-01-31
 */

@Service
public class ClientService {

	private Logger logger = Logger.getLogger(ClientService.class.getName());

	@Autowired
	private ClientRepository clientRepository;

	public ClientDTO insert(ClientDTO clientDto) {
		logger.info("Create one client");
		Client client = clientRepository.save(Mapper.parseObject(clientDto, Client.class));

		return Mapper.parseObject(client, ClientDTO.class);
	}

	public ClientDTO update(ClientDTO clientDto) {
		logger.info("update one client");
		Client client = clientRepository.save(Mapper.parseObject(clientDto, Client.class));

		return Mapper.parseObject(client, ClientDTO.class);
	}

	public ClientDTO findById(Long id) {
		logger.info("find one client");

		return  Mapper.parseObject(clientRepository.findById(id).get(), ClientDTO.class);
	}

	public List<ClientDTO> findAll() {
		logger.info("Find all clients");

		return Mapper.parseListObject(clientRepository.findAll(), ClientDTO.class);
	}

	public void delete(Long id) {
		logger.info("Delete one client");

		clientRepository.deleteById(id);
	}

}
