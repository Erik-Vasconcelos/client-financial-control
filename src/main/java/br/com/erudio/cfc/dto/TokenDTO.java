package br.com.erudio.cfc.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-14
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private boolean authenticated;
	private LocalDateTime created;
	private LocalDateTime expiration;
	private String accessToken;
	private String refeshToken;

}
