package br.com.erudio.cfc.unittest.v2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.cfc.dto.v2.ClientDTOV2;
import br.com.erudio.cfc.exception.RequestWithNullObjectException;
import br.com.erudio.cfc.model.Client;
import br.com.erudio.cfc.model.enums.ClientStatus;
import br.com.erudio.cfc.model.enums.Gender;
import br.com.erudio.cfc.model.enums.State;
import br.com.erudio.cfc.repository.ClientRepository;
import br.com.erudio.cfc.service.ClientServiceV2;
import br.com.erudio.cfc.unittest.v2.MockClientV2;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ClientServiceV2Test {

	MockClientV2 input;

	@Mock
	ClientRepository repository;

	@InjectMocks
	ClientServiceV2 clientService;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockClientV2();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Client entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		ClientDTOV2 result = clientService.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());

		assertEquals("</clients/v2/1>;rel=\"self\",</clients/v2>;rel=\"clients\"", result.getLinks().toString());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals(LocalDate.parse("1971-01-01"), result.getBirthDate());
		assertEquals(Gender.MALE, result.getGender());
		assertEquals(ClientStatus.REGULAR, result.getStatus());
		assertEquals("11111111111", result.getTelephone());

		assertNotNull(result.getAddresses());
		assertEquals("Street1", result.getAddresses().get(0).getStreet());
		assertEquals(1, result.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood1", result.getAddresses().get(0).getNeighborhood());
		assertEquals("City1", result.getAddresses().get(0).getCity());
		assertEquals(State.DF, result.getAddresses().get(0).getState());
		assertEquals("11111111", result.getAddresses().get(0).getPostalCode());
		assertEquals("Complement1", result.getAddresses().get(0).getComplement());
		assertEquals(result, result.getAddresses().get(0).getClient());
	}

	@Test
	void testInsert() {
		Client entity = input.mockEntity(1);
		entity.setId(null);

		Client persisted = entity;
		persisted.setId(1L);

		when(repository.save(entity)).thenReturn(persisted);

		ClientDTOV2 dto = input.mockDTO(1);

		ClientDTOV2 result = clientService.insert(dto);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());

		assertEquals("</clients/v2/1>;rel=\"self\",</clients/v2>;rel=\"clients\"", result.getLinks().toString());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals(LocalDate.parse("1971-01-01"), result.getBirthDate());
		assertEquals(Gender.MALE, result.getGender());
		assertEquals(ClientStatus.REGULAR, result.getStatus());
		assertEquals("11111111111", result.getTelephone());

		assertNotNull(result.getAddresses());
		assertEquals("Street1", result.getAddresses().get(0).getStreet());
		assertEquals(1, result.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood1", result.getAddresses().get(0).getNeighborhood());
		assertEquals("City1", result.getAddresses().get(0).getCity());
		assertEquals(State.DF, result.getAddresses().get(0).getState());
		assertEquals("11111111", result.getAddresses().get(0).getPostalCode());
		assertEquals("Complement1", result.getAddresses().get(0).getComplement());
		assertEquals(result, result.getAddresses().get(0).getClient());
	}

	@Test
	void testInsertClientNull() {
		Exception ex = assertThrows(RequestWithNullObjectException.class, () -> clientService.insert(null));

		String messageExpected = "Not a object null allowed";
		String messageActual = ex.getMessage();

		assertEquals(messageExpected, messageActual);
	}

	@Test
	void testUpdate() {
		Client entity = input.mockEntity(1);
		entity.setId(1L);

		Client persisted = entity;
		persisted.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		ClientDTOV2 dto = input.mockDTO(1);

		ClientDTOV2 result = clientService.update(dto);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());

		assertEquals("</clients/v2/1>;rel=\"self\",</clients/v2>;rel=\"clients\"", result.getLinks().toString());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals(LocalDate.parse("1971-01-01"), result.getBirthDate());
		assertEquals(Gender.MALE, result.getGender());
		assertEquals(ClientStatus.REGULAR, result.getStatus());
		assertEquals("11111111111", result.getTelephone());

		assertNotNull(result.getAddresses());
		assertEquals("Street1", result.getAddresses().get(0).getStreet());
		assertEquals(1, result.getAddresses().get(0).getNumber());
		assertEquals("Neichborhood1", result.getAddresses().get(0).getNeighborhood());
		assertEquals("City1", result.getAddresses().get(0).getCity());
		assertEquals(State.DF, result.getAddresses().get(0).getState());
		assertEquals("11111111", result.getAddresses().get(0).getPostalCode());
		assertEquals("Complement1", result.getAddresses().get(0).getComplement());
		assertEquals(result, result.getAddresses().get(0).getClient());
	}

	@Test
	void testUpdateClientNull() {
		Exception ex = assertThrows(RequestWithNullObjectException.class, () -> clientService.update(null));

		String messageExpected = "Not a object null allowed";
		String messageActual = ex.getMessage();

		assertEquals(messageExpected, messageActual);
	}

	@Test
	void testFindAll() {
		List<Client> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var clients = clientService.findAll();

		var outputOne = clients.get(1);

		assertNotNull(outputOne);
		assertNotNull(outputOne.getId());
		assertNotNull(outputOne.getLinks());
		assertEquals("</clients/v2/1>;rel=\"self\",</clients/v2>;rel=\"clients\"", outputOne.getLinks().toString());

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

		var outputFour = clients.get(4);

		assertNotNull(outputFour);
		assertNotNull(outputFour.getId());
		assertNotNull(outputFour.getLinks());
		assertEquals("</clients/v2/4>;rel=\"self\",</clients/v2>;rel=\"clients\"", outputFour.getLinks().toString());

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

		var outputFourteen = clients.get(14);

		assertNotNull(outputFourteen);
		assertNotNull(outputFourteen.getId());
		assertNotNull(outputFourteen.getLinks());
		assertEquals("</clients/v2/14>;rel=\"self\",</clients/v2>;rel=\"clients\"",
				outputFourteen.getLinks().toString());

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
	void testDelete() {
		Client entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		clientService.delete(1L);
	}

}
