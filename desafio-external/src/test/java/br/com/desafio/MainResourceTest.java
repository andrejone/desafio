package br.com.desafio;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.desafio.service.WorldBankService;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MainResourceTest {

	@Inject
	WorldBankService service;
	
    @Test
    public void testListCountriesEndpoint() {
        given()
                .when().get("/desafio-external/listCountries")
                .then()
                .statusCode(200);
    }

    @Test
    public void testServiceListCountries() {
    	Assertions.assertTrue(service.recuperaPaises() != null && service.recuperaPaises().size() > 0);
    }

}
