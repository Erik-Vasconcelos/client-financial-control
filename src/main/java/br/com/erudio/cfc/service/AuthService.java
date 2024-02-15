package br.com.erudio.cfc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.cfc.dto.AccountCredentialsDTO;
import br.com.erudio.cfc.dto.TokenDTO;
import br.com.erudio.cfc.model.Client;
import br.com.erudio.cfc.repository.ClientRepository;
import br.com.erudio.cfc.security.token.JwtTokenProvider;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-14
 */

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtTokenProvider provider;

	@Autowired
	private ClientRepository repository;

	public TokenDTO signin(AccountCredentialsDTO data) {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));

			Client client = repository.findByUsername(data.getUsername());

			TokenDTO token = new TokenDTO();

			if (client != null) {
				token = provider.createAccessToken(client.getUsername(), client.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + data.getUsername() + " not found!");
			}

			return token;
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}

	public TokenDTO refreshToken(String username, String refreshToken) {
		Client client = repository.findByUsername(username);

		TokenDTO token = new TokenDTO();

		if (client != null) {
			token = provider.createRefreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}

		return token;
	}

}
