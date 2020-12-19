package br.com.desafio.infra;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("desafio")
public class DesafioProperties {
	
	public String externalUrl;
}
