package br.com.erudio.cfc.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.cfc.repository.ClientRepository;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-14
 */

@Service
public class ClientAuthService implements UserDetailsService {

	private Logger logger = Logger.getLogger(ClientAuthService.class.getName());

	@Autowired
	private ClientRepository clientRepository;
	
	public ClientAuthService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Find client by username");
		
		var client = clientRepository.findByUsername(username);

		if (client != null) {
			return client;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}

	}

}
