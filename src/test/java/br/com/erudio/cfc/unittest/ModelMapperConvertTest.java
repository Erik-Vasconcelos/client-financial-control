package br.com.erudio.cfc.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.erudio.cfc.dto.ClientDTO;
import br.com.erudio.cfc.mapper.Mapper;
import br.com.erudio.cfc.model.Client;
import br.com.erudio.cfc.model.enums.ClientStatus;
import br.com.erudio.cfc.model.enums.Gender;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-01
 */

class ModelMapperConvertTest {

	MockClient input;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockClient();
	}

	@Test
	void parseEntityToDTOTest() {
		ClientDTO dto = Mapper.parseObject(input.mockEntity(1), ClientDTO.class);

		assertNotNull(dto);
		assertEquals(1L, dto.getId());
		assertEquals("First Name Test1", dto.getFirstName());
		assertEquals("Last Name Test1", dto.getLastName());
		assertEquals(LocalDate.parse("1971-01-01"), dto.getBirthDate());
		assertEquals(Gender.MALE, dto.getGender());
		assertEquals(ClientStatus.REGULAR, dto.getStatus());
		assertEquals("11111111111", dto.getTelephone());
	}

	@Test
	void parseEntityListToDTOListTest() {
		List<ClientDTO> outputList = Mapper.parseListObject(input.mockEntityList(), ClientDTO.class);
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

		var outputFour = outputList.get(4);

		assertNotNull(outputFour);
		assertEquals(4L, outputFour.getId());
		assertEquals("First Name Test4", outputFour.getFirstName());
		assertEquals("Last Name Test4", outputFour.getLastName());
		assertEquals(LocalDate.parse("1974-01-01"), outputFour.getBirthDate());
		assertEquals(Gender.FEMALE, outputFour.getGender());
		assertEquals(ClientStatus.PREMIUM, outputFour.getStatus());
		assertEquals("44444444444", outputFour.getTelephone());

		var outputFourteen = outputList.get(14);

		assertNotNull(outputFourteen);
		assertEquals(14L, outputFourteen.getId());
		assertEquals("First Name Test14", outputFourteen.getFirstName());
		assertEquals("Last Name Test14", outputFourteen.getLastName());
		assertEquals(LocalDate.parse("1984-01-01"), outputFourteen.getBirthDate());
		assertEquals(Gender.FEMALE, outputFourteen.getGender());
		assertEquals(ClientStatus.PREMIUM, outputFourteen.getStatus());
		assertEquals("14141414141", outputFourteen.getTelephone());
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

		var outputFour = outputList.get(4);

		assertNotNull(outputFour);
		assertEquals(4L, outputFour.getId());
		assertEquals("First Name Test4", outputFour.getFirstName());
		assertEquals("Last Name Test4", outputFour.getLastName());
		assertEquals(LocalDate.parse("1974-01-01"), outputFour.getBirthDate());
		assertEquals(Gender.FEMALE, outputFour.getGender());
		assertEquals(ClientStatus.PREMIUM, outputFour.getStatus());
		assertEquals("44444444444", outputFour.getTelephone());

		var outputFourteen = outputList.get(14);

		assertNotNull(outputFourteen);
		assertEquals(14L, outputFourteen.getId());
		assertEquals("First Name Test14", outputFourteen.getFirstName());
		assertEquals("Last Name Test14", outputFourteen.getLastName());
		assertEquals(LocalDate.parse("1984-01-01"), outputFourteen.getBirthDate());
		assertEquals(Gender.FEMALE, outputFourteen.getGender());
		assertEquals(ClientStatus.PREMIUM, outputFourteen.getStatus());
		assertEquals("14141414141", outputFourteen.getTelephone());
	}

}
