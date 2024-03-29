package br.com.erudio.cfc.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.cfc.controller.ClientControllerV2;
import br.com.erudio.cfc.dto.v2.ClientDTOV2;
import br.com.erudio.cfc.exception.ObjectNotFoundException;
import br.com.erudio.cfc.exception.RequestWithNullObjectException;
import br.com.erudio.cfc.mapper.Mapper;
import br.com.erudio.cfc.model.Client;
import br.com.erudio.cfc.repository.ClientRepository;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-02
 */

@Service
public class ClientServiceV2 {

	private Logger logger = Logger.getLogger(ClientServiceV2.class.getName());

	@Autowired
	private ClientRepository clientRepository;

	public ClientDTOV2 insert(ClientDTOV2 clientDto) {
		if (clientDto == null)
			throw new RequestWithNullObjectException();

		if (clientDto.getAddresses() == null)
			throw new RequestWithNullObjectException("Not a address null allowed");
		clientDto.getAddresses().stream().forEach(a -> a.setClient(clientDto));

		logger.info("Create one client v2");
		Client client = clientRepository.save(Mapper.parseObject(clientDto, Client.class));

		ClientDTOV2 dto = Mapper.parseObject(client, ClientDTOV2.class);

		dto.add(linkTo(methodOn(ClientControllerV2.class).getClient(dto.getId())).withSelfRel());
		dto.add(linkTo(methodOn(ClientControllerV2.class).getClients()).withRel("clients"));

		return dto;
	}

	public ClientDTOV2 update(ClientDTOV2 clientDto) {
		if (clientDto == null)
			throw new RequestWithNullObjectException();

		if (clientDto.getAddresses() == null)
			throw new RequestWithNullObjectException("Not a address null allowed");
		clientDto.getAddresses().stream().forEach(a -> a.setClient(clientDto));

		clientRepository.findById(clientDto.getId())
				.orElseThrow(() -> new ObjectNotFoundException("No customers found with the id #" + clientDto.getId()));

		logger.info("update one client v2");
		Client client = clientRepository.save(Mapper.parseObject(clientDto, Client.class));

		ClientDTOV2 dto = Mapper.parseObject(client, ClientDTOV2.class);

		dto.add(linkTo(methodOn(ClientControllerV2.class).getClient(dto.getId())).withSelfRel());
		dto.add(linkTo(methodOn(ClientControllerV2.class).getClients()).withRel("clients"));

		return dto;
	}

	public ClientDTOV2 findById(Long id) {
		logger.info("find one client v2");

		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("No customers found with the id #" + id));

		ClientDTOV2 dto = Mapper.parseObject(client, ClientDTOV2.class);

		dto.add(linkTo(methodOn(ClientControllerV2.class).getClient(dto.getId())).withSelfRel());
		dto.add(linkTo(methodOn(ClientControllerV2.class).getClients()).withRel("clients"));

		return dto;
	}

	public List<ClientDTOV2> findAll() {
		logger.info("Find all clients v2");
		
		List<ClientDTOV2> list = Mapper.parseListObject(clientRepository.findAll(), ClientDTOV2.class);

		list.stream().forEach(c -> {
			c.add(linkTo(methodOn(ClientControllerV2.class).getClient(c.getId())).withSelfRel());
			c.add(linkTo(methodOn(ClientControllerV2.class).getClients()).withRel("clients"));
		});
		
		return list;
	}

	public void delete(Long id) {
		clientRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("No customers found with the id #" + id));

		logger.info("Delete one client v2");
		clientRepository.deleteById(id);
	}

}
