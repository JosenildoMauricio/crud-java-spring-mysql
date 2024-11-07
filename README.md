# processoseletivo

Como configurar as imagens no docker para rodar o projeto local


* docker image pull mysql:8.0.39

* docker network create --driver bridge crud-network

* docker container run --name crud-mysql -d --rm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=crud -e MYSQL_DATABASE=cruddb -e MYSQL_USER=crud -e MYSQL_PASSWORD=crud --network crud-network mysql:8.0.39

* mvn clean package

* docker image build -t crud-spring:0.0.1 .

* docker container run --name crud-api -d --rm -p 8080:8080 -e DB_HOST=crud-mysql --network crud-network crud-spring:0.0.1

No diretorio test/resources/postman tem uma collection do postman.