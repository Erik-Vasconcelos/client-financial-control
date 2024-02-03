package br.com.erudio.cfc.unittest.v1;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import br.com.erudio.cfc.dto.v1.ClientDTOV1;
import br.com.erudio.cfc.model.Client;
import br.com.erudio.cfc.model.enums.ClientStatus;
import br.com.erudio.cfc.model.enums.Gender;

public class MockClient {

	private static final Integer TELEPHONE_SIZE = 11;

	public Client mockEntity() {
		return mockEntity(0);
	}

	public ClientDTOV1 mockDTO() {
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

		return client;
	}

	public ClientDTOV1 mockDTO(Integer number) {
		ClientDTOV1 dto = new ClientDTOV1();
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

		return dto;
	}

	public List<Client> mockEntityList() {
		List<Client> clients = new LinkedList<>();

		for (int i = 0; i < 15; i++) {
			clients.add(mockEntity(i));
		}

		return clients;
	}

	public List<ClientDTOV1> mockDTOList() {
		List<ClientDTOV1> dtos = new LinkedList<>();

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

}
