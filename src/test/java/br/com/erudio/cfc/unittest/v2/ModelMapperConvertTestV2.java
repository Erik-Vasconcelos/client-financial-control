package br.com.erudio.cfc.unittest.v2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.erudio.cfc.dto.v2.ClientDTOV2;
import br.com.erudio.cfc.mapper.Mapper;
import br.com.erudio.cfc.model.Client;
import br.com.erudio.cfc.model.enums.ClientStatus;
import br.com.erudio.cfc.model.enums.Gender;
import br.com.erudio.cfc.model.enums.State;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-03
 */

class ModelMapperConvertTestV2 {

	MockClientV2 input;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockClientV2();
	}

	@Test
	void parseEntityToDTOTest() {
		ClientDTOV2 dto = Mapper.parseObject(input.mockEntity(1), ClientDTOV2.class);

		assertNotNull(dto);
		assertEquals(1L, dto.getId());
		assertEquals("First Name Test1", dto.getFirstName());
		assertEquals("Last Name Test1", dto.getLastName());
		assertEquals(LocalDate.parse("1971-01-01"), dto.getBirthDate());
		assertEquals(Gender.MALE, dto.getGender());
		assertEquals(ClientStatus.REGULAR, dto.getStatus());
		assertEquals("11111111111", dto.getTelephone());
		
		assertNotNull(dto.getAddresses());
		assertEquals("Street1", dto.getAddresses().get(0).getStreet());
		assertEquals(1, dto.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood1", dto.getAddresses().get(0).getNeighborhood());
		assertEquals("City1", dto.getAddresses().get(0).getCity());
		assertEquals(State.DF, dto.getAddresses().get(0).getState());
		assertEquals("11111111", dto.getAddresses().get(0).getPostalCode());
		assertEquals("Complement1", dto.getAddresses().get(0).getComplement());
		assertEquals(dto, dto.getAddresses().get(0).getClient());
	}

	@Test
	void parseEntityListToDTOListTest() {
		List<ClientDTOV2> outputList = Mapper.parseListObject(input.mockEntityList(), ClientDTOV2.class);
		assertNotNull(outputList);

		var outputOne = outputList.get(1);

		assertNotNull(outputOne);
		assertEquals(1L, outputOne.getId());
		assertEquals("First Name Test1", outputOne.getFirstName());
		assertEquals("Last Name Test1", outputOne.getLastName());
		assertEquals(LocalDate.parse("1971-01-01"), outputOne.getBirthDate());
		assertEquals(Gender.MALE, outputOne.getGender());
		assertEquals(ClientStatus.REGULAR, outputOne.getStatus());
		assertEquals("11111111111", outputOne.getTelephone());
		
		assertNotNull(outputOne.getAddresses());
		assertEquals("Street1", outputOne.getAddresses().get(0).getStreet());
		assertEquals(1, outputOne.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood1", outputOne.getAddresses().get(0).getNeighborhood());
		assertEquals("City1", outputOne.getAddresses().get(0).getCity());
		assertEquals(State.DF, outputOne.getAddresses().get(0).getState());
		assertEquals("11111111", outputOne.getAddresses().get(0).getPostalCode());
		assertEquals("Complement1", outputOne.getAddresses().get(0).getComplement());
		assertEquals(outputOne, outputOne.getAddresses().get(0).getClient());

		var outputFour = outputList.get(4);

		assertNotNull(outputFour);
		assertEquals(4L, outputFour.getId());
		assertEquals("First Name Test4", outputFour.getFirstName());
		assertEquals("Last Name Test4", outputFour.getLastName());
		assertEquals(LocalDate.parse("1974-01-01"), outputFour.getBirthDate());
		assertEquals(Gender.FEMALE, outputFour.getGender());
		assertEquals(ClientStatus.PREMIUM, outputFour.getStatus());
		assertEquals("44444444444", outputFour.getTelephone());
		
		assertNotNull(outputFour.getAddresses());
		assertEquals("Street4", outputFour.getAddresses().get(0).getStreet());
		assertEquals(4, outputFour.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood4", outputFour.getAddresses().get(0).getNeighborhood());
		assertEquals("City4", outputFour.getAddresses().get(0).getCity());
		assertEquals(State.DF, outputFour.getAddresses().get(0).getState());
		assertEquals("44444444", outputFour.getAddresses().get(0).getPostalCode());
		assertEquals("Complement4", outputFour.getAddresses().get(0).getComplement());
		assertEquals(outputFour, outputFour.getAddresses().get(0).getClient());

		var outputFourteen = outputList.get(14);

		assertNotNull(outputFourteen);
		assertEquals(14L, outputFourteen.getId());
		assertEquals("First Name Test14", outputFourteen.getFirstName());
		assertEquals("Last Name Test14", outputFourteen.getLastName());
		assertEquals(LocalDate.parse("1984-01-01"), outputFourteen.getBirthDate());
		assertEquals(Gender.FEMALE, outputFourteen.getGender());
		assertEquals(ClientStatus.PREMIUM, outputFourteen.getStatus());
		assertEquals("14141414141", outputFourteen.getTelephone());
		
		assertNotNull(outputFourteen.getAddresses());
		assertEquals("Street14", outputFourteen.getAddresses().get(0).getStreet());
		assertEquals(14, outputFourteen.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood14", outputFourteen.getAddresses().get(0).getNeighborhood());
		assertEquals("City14", outputFourteen.getAddresses().get(0).getCity());
		assertEquals(State.DF, outputFourteen.getAddresses().get(0).getState());
		assertEquals("14141414", outputFourteen.getAddresses().get(0).getPostalCode());
		assertEquals("Complement14", outputFourteen.getAddresses().get(0).getComplement());
		assertEquals(outputFourteen, outputFourteen.getAddresses().get(0).getClient());
	}

	@Test
	void parseDTOToEntityTest() {
		Client entity = Mapper.parseObject(input.mockDTO(1), Client.class);

		assertNotNull(entity);
		assertEquals(1L, entity.getId());
		assertEquals("First Name Test1", entity.getFirstName());
		assertEquals("Last Name Test1", entity.getLastName());
		assertEquals(LocalDate.parse("1971-01-01"), entity.getBirthDate());
		assertEquals(Gender.MALE, entity.getGender());
		assertEquals(ClientStatus.REGULAR, entity.getStatus());
		assertEquals("11111111111", entity.getTelephone());
		
		assertNotNull(entity.getAddresses());
		assertEquals("Street1", entity.getAddresses().get(0).getStreet());
		assertEquals(1, entity.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood1", entity.getAddresses().get(0).getNeighborhood());
		assertEquals("City1", entity.getAddresses().get(0).getCity());
		assertEquals(State.DF, entity.getAddresses().get(0).getState());
		assertEquals("11111111", entity.getAddresses().get(0).getPostalCode());
		assertEquals("Complement1", entity.getAddresses().get(0).getComplement());
		assertEquals(entity, entity.getAddresses().get(0).getClient());
	}

	@Test
	void parseDTOListToEntityListTest() {
		List<Client> outputList = Mapper.parseListObject(input.mockDTOList(), Client.class);
		assertNotNull(outputList);

		var outputOne = outputList.get(1);

		assertNotNull(outputOne);
		assertEquals(1L, outputOne.getId());
		assertEquals("First Name Test1", outputOne.getFirstName());
		assertEquals("Last Name Test1", outputOne.getLastName());
		assertEquals(LocalDate.parse("1971-01-01"), outputOne.getBirthDate());
		assertEquals(Gender.MALE, outputOne.getGender());
		assertEquals(ClientStatus.REGULAR, outputOne.getStatus());
		assertEquals("11111111111", outputOne.getTelephone());
		
		assertNotNull(outputOne.getAddresses());
		assertEquals("Street1", outputOne.getAddresses().get(0).getStreet());
		assertEquals(1, outputOne.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood1", outputOne.getAddresses().get(0).getNeighborhood());
		assertEquals("City1", outputOne.getAddresses().get(0).getCity());
		assertEquals(State.DF, outputOne.getAddresses().get(0).getState());
		assertEquals("11111111", outputOne.getAddresses().get(0).getPostalCode());
		assertEquals("Complement1", outputOne.getAddresses().get(0).getComplement());
		assertEquals(outputOne, outputOne.getAddresses().get(0).getClient());

		var outputFour = outputList.get(4);

		assertNotNull(outputFour);
		assertEquals(4L, outputFour.getId());
		assertEquals("First Name Test4", outputFour.getFirstName());
		assertEquals("Last Name Test4", outputFour.getLastName());
		assertEquals(LocalDate.parse("1974-01-01"), outputFour.getBirthDate());
		assertEquals(Gender.FEMALE, outputFour.getGender());
		assertEquals(ClientStatus.PREMIUM, outputFour.getStatus());
		assertEquals("44444444444", outputFour.getTelephone());
		
		assertNotNull(outputFour.getAddresses());
		assertEquals("Street4", outputFour.getAddresses().get(0).getStreet());
		assertEquals(4, outputFour.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood4", outputFour.getAddresses().get(0).getNeighborhood());
		assertEquals("City4", outputFour.getAddresses().get(0).getCity());
		assertEquals(State.DF, outputFour.getAddresses().get(0).getState());
		assertEquals("44444444", outputFour.getAddresses().get(0).getPostalCode());
		assertEquals("Complement4", outputFour.getAddresses().get(0).getComplement());
		assertEquals(outputFour, outputFour.getAddresses().get(0).getClient());

		var outputFourteen = outputList.get(14);

		assertNotNull(outputFourteen);
		assertEquals(14L, outputFourteen.getId());
		assertEquals("First Name Test14", outputFourteen.getFirstName());
		assertEquals("Last Name Test14", outputFourteen.getLastName());
		assertEquals(LocalDate.parse("1984-01-01"), outputFourteen.getBirthDate());
		assertEquals(Gender.FEMALE, outputFourteen.getGender());
		assertEquals(ClientStatus.PREMIUM, outputFourteen.getStatus());
		assertEquals("14141414141", outputFourteen.getTelephone());
		
		assertNotNull(outputFourteen.getAddresses());
		assertEquals("Street14", outputFourteen.getAddresses().get(0).getStreet());
		assertEquals(14, outputFourteen.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood14", outputFourteen.getAddresses().get(0).getNeighborhood());
		assertEquals("City14", outputFourteen.getAddresses().get(0).getCity());
		assertEquals(State.DF, outputFourteen.getAddresses().get(0).getState());
		assertEquals("14141414", outputFourteen.getAddresses().get(0).getPostalCode());
		assertEquals("Complement14", outputFourteen.getAddresses().get(0).getComplement());
		assertEquals(outputFourteen, outputFourteen.getAddresses().get(0).getClient());
	}

}
