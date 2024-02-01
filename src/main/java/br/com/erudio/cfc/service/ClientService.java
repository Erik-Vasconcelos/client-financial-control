package br.com.erudio.cfc.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.cfc.dto.ClientDTO;
import br.com.erudio.cfc.exception.ObjectNotFoundException;
import br.com.erudio.cfc.exception.RequestWithNullObjectException;
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
		if (clientDto == null)
			throw new RequestWithNullObjectException();

		logger.info("Create one client");
		Client client = clientRepository.save(Mapper.parseObject(clientDto, Client.class));

		return Mapper.parseObject(client, ClientDTO.class);
	}

	public ClientDTO update(ClientDTO clientDto) {
		if (clientDto == null)
			throw new RequestWithNullObjectException();

		clientRepository.findById(clientDto.getId()).orElseThrow(
				() -> new ObjectNotFoundException("No customers found with the id #" + clientDto.getId()));

		logger.info("update one client");
		Client client = clientRepository.save(Mapper.parseObject(clientDto, Client.class));

		return Mapper.parseObject(client, ClientDTO.class);
	}

	public ClientDTO findById(Long id) {
		logger.info("find one client");

		Client client = clientRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("No customers found with the id #" + id));

		ClientDTO dto = Mapper.parseObject(client, ClientDTO.class);
		
		return dto;
	}

	public List<ClientDTO> findAll() {
		logger.info("Find all clients");

		return Mapper.parseListObject(clientRepository.findAll(), ClientDTO.class);
	}

	public void delete(Long id) {
		clientRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("No customers found with the id #" + id));

		logger.info("Delete one client");
		clientRepository.deleteById(id);
	}

}
