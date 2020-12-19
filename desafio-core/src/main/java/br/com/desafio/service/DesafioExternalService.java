package br.com.desafio.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.desafio.dto.Country;
import br.com.desafio.dto.Resultado;
import br.com.desafio.infra.DesafioProperties;

@ApplicationScoped
public class DesafioExternalService {
	
//	private static final String URL_DESAFIO_EXTERNAL = "http://localhost:8081/desafio-external/";
	
	@Inject
    DesafioProperties properties;

    public Resultado recuperaIndicadores(String countryCode) {
    	try {
    		StringBuffer url = new StringBuffer(properties.externalUrl).append("findByCountryCode/").append(countryCode);
			HttpURLConnection conn = getConnection(url.toString());
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED &&
					conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			String retorno = null;
			while ((output = br.readLine()) != null) {
				retorno = output;
			}
			conn.disconnect();
			return convertJsonToResultado(retorno);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public List<Country> recuperaPaises() {
    	try {
    		StringBuffer url = new StringBuffer(properties.externalUrl).append("listCountries");
    		HttpURLConnection conn = getConnection(url.toString());
    		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED &&
    				conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
    			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
    		}
    		
    		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    		
    		String output;
    		String retorno = null;
    		while ((output = br.readLine()) != null) {
    			retorno = output;
    		}
    		conn.disconnect();
    		return convertJsonToCountry(retorno);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    private Resultado convertJsonToResultado(String json) {
    	Gson gson = new Gson();
    	Resultado retorno = gson.fromJson(json, Resultado.class);
    	return retorno;
    }
    
    private List<Country> convertJsonToCountry(String json) {
    	Gson gson = new Gson();
    	List<Country> retorno = gson.fromJson(json, new TypeToken<List<Country>>(){}.getType());
    	return retorno;
    }
    
    private HttpURLConnection getConnection(String endereco) {
		try {
			URL url = new URL(endereco);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(RequestMethod.GET.name());
			conn.setRequestProperty("Content-Type", "application/json");

			return conn;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
