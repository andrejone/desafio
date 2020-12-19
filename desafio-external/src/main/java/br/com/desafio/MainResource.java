package br.com.desafio;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import br.com.desafio.dto.Country;
import br.com.desafio.dto.Resultado;
import br.com.desafio.service.WorldBankService;

@Path("/desafio-external")
public class MainResource {

    @Inject
    WorldBankService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findByCountryCode/{code}")
    @APIResponse(description = "Recuperar os indicadores de um país pelo código",
    	    responseCode = "200",
    	    name = "Recuperar indicadores",
    	    content = @Content(
    	        mediaType = "application/json",
    	        schema = @Schema(
    	            type = SchemaType.OBJECT,
    	            implementation = Resultado.class
    	        )
    	    )
    	)
    public Resultado findByCountryCode(@PathParam("code") String code) {
        return service.recuperaIndicadores(code);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listCountries")
    @APIResponse(description = "Listar todos os países disponíveis e seus códigos",
	    responseCode = "200",
	    name = "Listar países",
	    content = @Content(
	        mediaType = "application/json",
	        schema = @Schema(
	            type = SchemaType.ARRAY,
	            implementation = Country.class
	        )
	    )
	)
    public List<Country> listCountries() {
    	return service.recuperaPaises();
    }

}