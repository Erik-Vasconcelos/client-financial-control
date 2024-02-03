package br.com.erudio.cfc.dto.v2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.erudio.cfc.model.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-02
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {
	
	private Long id;
	private String street;
	private Integer number;
	private String neighborhood;
	private String city;
	private State state;
	private String postalCode;
	private String complement;
	@JsonIgnore
	private ClientDTOV2 client;
	
}
