# HISTÓRIA DE USUÁRIO

HU: Como Usuário, eu quero consultar os indicadores econômicos de avaliação da quantidade de pessoas em situação de extrema pobreza
no mundo (vivendo com até $1,90 por dia) por país, para gerar relatórios analíticos.

# GERANDO O CONTAINER DOCKER

- Na raiz do projeto, digite o comando abaixo para gerar a imagem docker

	docker build -f src/main/docker/Dockerfile.native -t quarkus-desafio/desafio-core .
	
- Após, execute a imagem por meio do seguinte comando:

	docker run -i --rm -p 8080:8080 quarkus-desafio/desafio-core
	
