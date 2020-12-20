package br.com.desafio;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.desafio.service.DesafioExternalService;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MainResourceTest {
	
	private static String CODE_COUNTRY_TEST = "BRA";

	@Inject
	DesafioExternalService service;
	
    @Test
    public void testListCountriesEndpoint() {
        given()
                .when().get("/desafio/listCountries")
                .then()
                .statusCode(200);
    }

    @Test
    public void testServiceListCountries() {
    	Assertions.assertTrue(service.recuperaPaises() != null && service.recuperaPaises().size() > 0);
    }
    
    @Test
    public void testFindByCountryCodeEndpoint() {
    	given()
    	.when().get("/desafio/findByCountryCode/" + CODE_COUNTRY_TEST)
    	.then()
    	.statusCode(200);
    }
    
    @Test
    public void testServiceFindByCountryCode() {
    	Assertions.assertTrue(service.recuperaIndicadores(CODE_COUNTRY_TEST) != null 
    			&& service.recuperaIndicadores(CODE_COUNTRY_TEST) !=  null
    			&& service.recuperaIndicadores(CODE_COUNTRY_TEST).getIndicadores() != null
    			&& service.recuperaIndicadores(CODE_COUNTRY_TEST).getIndicadores().size() > 0);
    }

}
