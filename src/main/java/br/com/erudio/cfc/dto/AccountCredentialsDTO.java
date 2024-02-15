package br.com.erudio.cfc.dto;

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
public class AccountCredentialsDTO {

	private String username;
	private String password;
	
}
