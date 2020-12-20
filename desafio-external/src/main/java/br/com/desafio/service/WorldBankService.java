package br.com.desafio.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.desafio.dto.Control;
import br.com.desafio.dto.Country;
import br.com.desafio.dto.EconomicIndicator;
import br.com.desafio.dto.Resultado;

@ApplicationScoped
public class WorldBankService {
	
	private static final String URL_API_WORLD_BANK = "http://api.worldbank.org/v2/country";

    public Resultado recuperaIndicadores(String countryCode) {
    	try {
    		StringBuffer url = new StringBuffer(URL_API_WORLD_BANK)
    				.append("/")
    				.append(countryCode)
    				.append("/indicator/SI.POV.DDAY?format=json");
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
    		StringBuffer url = new StringBuffer(URL_API_WORLD_BANK)
    				.append("?format=json");
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
    	List<Object> retorno = gson.fromJson(json, new TypeToken<List<Object>>(){}.getType());
    	Resultado resultado = new Resultado();
    	if(retorno != null) {
    		for(Object o : retorno) {
    			if(o instanceof ArrayList) {
    				resultado.setIndicadores(gson.fromJson(gson.toJsonTree(o), new TypeToken<List<EconomicIndicator>>(){}.getType()));
    			} else {
    				resultado.setControl(gson.fromJson(gson.toJsonTree(o), Control.class));
    			}
    		}
    	}
    	return resultado;
    }
    
    private List<Country> convertJsonToCountry(String json) {
    	Gson gson = new Gson();
    	List<Object> retorno = gson.fromJson(json, new TypeToken<List<Object>>(){}.getType());
    	List<Country> paises = new ArrayList<>();
    	if(retorno != null) {
    		for(Object o : retorno) {
    			if(o instanceof ArrayList) {
    				paises = gson.fromJson(gson.toJsonTree(o), new TypeToken<List<Country>>(){}.getType());
    			}
    		}
    	}
    	return paises;
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
