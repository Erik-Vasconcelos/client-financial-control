package br.com.erudio.cfc.dto.v1;

import java.time.LocalDate;

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
 * Class ClientDTO first version
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({"id", "firstName", "lastName", "birthDate", "telephone", "gender", "status"})
public class ClientDTOV1 {

	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String telephone;
	private Gender gender;
	@JsonProperty("clientType")
	private ClientStatus status;

}
