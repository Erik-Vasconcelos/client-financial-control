package br.com.erudio.cfc;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.erudio.cfc.integrationtest.swagger.AbstractIntegrationTest;
import br.com.erudio.cfc.integrationtest.swagger.TestConfigs;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ClientFinancialControlApplicationTests extends AbstractIntegrationTest{

	@Test
	void showDisplayUiPage() {
		var content = RestAssured.given().basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when().get()
				.then().statusCode(200)
				.extract().body().asString();
		
		assertTrue(content.contains("Swagger UI"));
		
	}

}
