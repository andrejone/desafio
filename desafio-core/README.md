# GERANDO O CONTAINER DOCKER

- Na raiz do projeto, digite o comando abaixo para gerar a imagem docker

	docker build -f src/main/docker/Dockerfile.native -t quarkus-desafio/desafio-core .
	
- Após, execute a imagem por meio do seguinte comando:

	docker run -i --rm -p 8080:8080 quarkus-desafio/desafio-core
	
