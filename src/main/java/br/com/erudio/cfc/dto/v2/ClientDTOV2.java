package br.com.erudio.cfc.dto.v2;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.erudio.cfc.model.enums.ClientStatus;
import br.com.erudio.cfc.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-01
 * 
 * Class ClientDTO second version
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({ "id", "firstName", "lastName", "birthDate", "telephone", "gender", "status", "addresses" })
public class ClientDTOV2 {

	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String telephone;
	private Gender gender;
	@JsonProperty("clientType")
	private ClientStatus status;
	private List<AddressDTO> addresses;

}
