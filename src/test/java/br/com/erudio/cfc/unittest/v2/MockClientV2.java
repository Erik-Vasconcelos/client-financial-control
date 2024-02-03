package br.com.erudio.cfc.unittest.v2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import br.com.erudio.cfc.dto.v2.AddressDTO;
import br.com.erudio.cfc.dto.v2.ClientDTOV2;
import br.com.erudio.cfc.model.Address;
import br.com.erudio.cfc.model.Client;
import br.com.erudio.cfc.model.enums.ClientStatus;
import br.com.erudio.cfc.model.enums.Gender;
import br.com.erudio.cfc.model.enums.State;

public class MockClientV2 {

	private static final Integer TELEPHONE_SIZE = 11;
	private static final Integer POSTAL_CODE_SIZE = 8;

	public Client mockEntity() {
		return mockEntity(0);
	}

	public ClientDTOV2 mockDTO() {
		return mockDTO(0);
	}

	public Client mockEntity(Integer number) {
		Client client = new Client();
		client.setId(number.longValue());
		client.setFirstName("First Name Test" + number);
		client.setLastName("Last Name Test" + number);
		client.setBirthDate(LocalDate.parse("1970-01-01").plusYears(number.longValue()));

		if (number % 2 == 0) {
			client.setGender(Gender.FEMALE);
			client.setStatus(ClientStatus.PREMIUM);
		} else {
			client.setGender(Gender.MALE);
			client.setStatus(ClientStatus.REGULAR);
		}

		client.setTelephone(generatedTelephone(number));
		
		Address address = new Address();
		address.setStreet("Street" + number);
		address.setNumber(number);
		address.setNeighborhood("Neichborhood" + number);
		address.setCity("City"+ number);
		address.setState(State.DF);
		address.setPostalCode(generatedPostalCode(number));
		address.setComplement("Complement"+number);
		address.setClient(client);

		client.setAddresses(Arrays.asList(address));

		return client;
	}

	public ClientDTOV2 mockDTO(Integer number) {
		ClientDTOV2 dto = new ClientDTOV2();
		dto.setId(number.longValue());
		dto.setFirstName("First Name Test" + number);
		dto.setLastName("Last Name Test" + number);
		dto.setBirthDate(LocalDate.parse("1970-01-01").plusYears(number.longValue()));

		if (number % 2 == 0) {
			dto.setGender(Gender.FEMALE);
			dto.setStatus(ClientStatus.PREMIUM);
		} else {
			dto.setGender(Gender.MALE);
			dto.setStatus(ClientStatus.REGULAR);
		}

		dto.setTelephone(generatedTelephone(number));
		
		AddressDTO addressDto = new AddressDTO();
		addressDto.setStreet("Street" + number);
		addressDto.setNumber(number);
		addressDto.setNeighborhood("Neichborhood" + number);
		addressDto.setCity("City"+ number);
		addressDto.setState(State.DF);
		addressDto.setPostalCode(generatedPostalCode(number));
		addressDto.setComplement("Complement"+number);
		addressDto.setClient(dto);

		dto.setAddresses(Arrays.asList(addressDto));

		return dto;
	}

	public List<Client> mockEntityList() {
		List<Client> clients = new LinkedList<>();

		for (int i = 0; i < 15; i++) {
			clients.add(mockEntity(i));
		}

		return clients;
	}

	public List<ClientDTOV2> mockDTOList() {
		List<ClientDTOV2> dtos = new LinkedList<>();

		for (int i = 0; i < 15 ; i++) {
			dtos.add(mockDTO(i));
		}

		return dtos;
	}

	public String generatedTelephone(Integer number) {
		String telephone = "";

		while (telephone.length() < TELEPHONE_SIZE) {
			telephone = telephone + number;

			if (telephone.length() > TELEPHONE_SIZE) {
				telephone = telephone.substring(0, TELEPHONE_SIZE);
			}
		}

		return telephone;
	}
	
	public String generatedPostalCode(Integer number) {
		String postalCode = "";
		
		while (postalCode.length() < POSTAL_CODE_SIZE) {
			postalCode = postalCode + number;
			
			if (postalCode.length() > POSTAL_CODE_SIZE) {
				postalCode = postalCode.substring(0, POSTAL_CODE_SIZE);
			}
		}
		
		return postalCode;
	}

}
