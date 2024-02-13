package br.com.erudio.cfc.integrationtest.cors;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.erudio.cfc.model.enums.ClientStatus;
import br.com.erudio.cfc.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
