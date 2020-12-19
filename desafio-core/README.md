# CONFIGURANDO O APONTAMENTO PARA O MICROSSERVIÇO DE CONSULTA À API DO WORLD BANK

- Edite o arquivo application.properties
- Altere a URL definida na propriedade desafio.external-url apontando para o endereço/porta local ou de produção 

# GERANDO O CONTAINER DOCKER

- Na raiz do projeto, digite o comando abaixo para gerar a imagem docker

	docker build -f src/main/docker/Dockerfile.native -t quarkus-desafio/desafio-core .
	
- Após, execute a imagem por meio do seguinte comando:

	docker run -i --rm -p 8080:8080 quarkus-desafio/desafio-core
	
