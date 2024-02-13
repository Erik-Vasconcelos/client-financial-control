package br.com.erudio.cfc.integrationtest.cors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.cfc.config.TestConfigs;
import br.com.erudio.cfc.integrationtest.swagger.AbstractIntegrationTest;
import br.com.erudio.cfc.model.enums.ClientStatus;
import br.com.erudio.cfc.model.enums.Gender;
import br.com.erudio.cfc.model.enums.State;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
class CorsInetgrationTest extends AbstractIntegrationTest{

	private static RequestSpecification specification;
	private static ObjectMapper mapper;
	private static ClientDTOV2 client;
	
	@BeforeAll
	public static void setup() {
		client = new ClientDTOV2();
		
		mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	@Test
	@Order(1)
	void testCreate() throws JsonMappingException, JsonProcessingException {
		mockClient();
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
				.setBasePath("/api/clients/v2")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = RestAssured.given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(client)
				.when().post()
				.then().statusCode(200)
				.extract().body().asString();
		
		ClientDTOV2 result = mapper.readValue(content, ClientDTOV2.class);
		client = result;
		
		assertNotNull(result);
		assertNotNull(result.getFirstName());
		assertNotNull(result.getLastName());
		assertNotNull(result.getBirthDate());
		assertNotNull(result.getGender());
		assertNotNull(result.getStatus());
		assertNotNull(result.getTelephone());
		assertNotNull(result.getAddresses());
		
		assertTrue(result.getId() > 0);
		
		assertEquals("First Name", result.getFirstName());
		
		assertEquals("Last Name", result.getLastName());
		assertEquals(LocalDate.parse("2000-02-15"), result.getBirthDate());
		assertEquals(Gender.MALE, result.getGender());
		assertEquals(ClientStatus.NEW, result.getStatus());
		assertEquals("999999999", result.getTelephone());
		
		assertEquals("Street", result.getAddresses().get(0).getStreet());
		assertEquals(07, result.getAddresses().get(0).getNumber());
		assertEquals("Neighborhood", result.getAddresses().get(0).getNeighborhood());
		assertEquals("City", result.getAddresses().get(0).getCity());
		assertEquals(State.AL, result.getAddresses().get(0).getState());
		assertEquals("87888888", result.getAddresses().get(0).getPostalCode());
		assertEquals("Complement", result.getAddresses().get(0).getComplement());
		
		assertTrue(result.getAddresses().get(0).getId() > 0);
		
	}
	
	@Test
	@Order(2)
	void testCreateWrongOrigin() throws JsonMappingException, JsonProcessingException {
		mockClient();
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
				.setBasePath("/api/clients/v2")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = RestAssured.given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(client)
				.when().post()
				.then().statusCode(403)
				.extract().body().asString();
		
		assertNotNull(content);
		assertTrue(content.contains("Invalid CORS request"));
	}
	
	@Test
	@Order(3)
	void testFindClient() throws JsonMappingException, JsonProcessingException {
		mockClient();
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
				.setBasePath("/api/clients/v2")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = RestAssured.given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.pathParam("id", client.getId())
				.when().get("{id}")
				.then().statusCode(200)
				.extract().body().asString();
		
		ClientDTOV2 result = mapper.readValue(content, ClientDTOV2.class);
		client = result;
		
		assertNotNull(result);
		assertNotNull(result.getFirstName());
		assertNotNull(result.getLastName());
		assertNotNull(result.getBirthDate());
		assertNotNull(result.getGender());
		assertNotNull(result.getStatus());
		assertNotNull(result.getTelephone());
		assertNotNull(result.getAddresses());
		
		assertTrue(result.getId() > 0);
		
		assertEquals("First Name", result.getFirstName());
		
		assertEquals("Last Name", result.getLastName());
		assertEquals(LocalDate.parse("2000-02-15"), result.getBirthDate());
		assertEquals(Gender.MALE, result.getGender());
		assertEquals(ClientStatus.NEW, result.getStatus());
		assertEquals("999999999", result.getTelephone());
		
		assertEquals("Street", result.getAddresses().get(0).getStreet());
		assertEquals(07, result.getAddresses().get(0).getNumber());
		assertEquals("Neighborhood", result.getAddresses().get(0).getNeighborhood());
		assertEquals("City", result.getAddresses().get(0).getCity());
		assertEquals(State.AL, result.getAddresses().get(0).getState());
		assertEquals("87888888", result.getAddresses().get(0).getPostalCode());
		assertEquals("Complement", result.getAddresses().get(0).getComplement());
		
		assertTrue(result.getAddresses().get(0).getId() > 0);
		
	}
	
	@Test
	@Order(4)
	void testFindClientWrongOrigin() throws JsonMappingException, JsonProcessingException {
		mockClient();
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
				.setBasePath("/api/clients/v2")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = RestAssured.given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.pathParam("id", client.getId())
				.when().get("{id}")
				.then().statusCode(403)
				.extract().body().asString();
		
		assertNotNull(content);
		assertTrue(content.contains("Invalid CORS request"));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void mockClient() {
		client.setFirstName("First Name");
		client.setLastName("Last Name");
		client.setBirthDate(LocalDate.parse("2000-02-15"));
		client.setGender(Gender.MALE);
		client.setStatus(ClientStatus.NEW);
		client.setTelephone("999999999");
		
		List<AddressDTO> addresses = new ArrayList();
		addresses.add(new AddressDTO(null, "Street", 07, "Neighborhood", "City", State.AL, "87888888", "Complement", client));
		
		client.setAddresses(addresses);
	}

}
