# registro-ponto

API responsável pelo registro de ponto de entradas, saídas,
inicio de intervalo e retorno de intervalo.
Além disso, o microserviço é responsável por gerar relatórios de funcionários
referente ao mês anterior à solicitação, geração de PDF
e posterior envio ao sistema de envio de e-mails.


## Prerequisites:
- Apache Maven 3.+
- JDK 21
- Docker 20+
- Docker Compose 1.22.+

## Start application in Docker container in Docker:
```
    docker-compose up -d
```

## Stop all applications in Docker containers:
```
    docker-compose stop
```

## Start application in Docker container in Docker and rebuild service image:
```
    docker-compose up --build --force-recreate --no-deps -d 
```

## Swagger documentation:
```
    http://localhost:8080/swagger-ui.html
```

 - Endpoint para obter pontos do mês anterior 

![swagger-registar-ponto-get.PNG](images%2Fswagger-registar-ponto-get.PNG)

 - Endpoint para registrar um ponto

![swagger-registar-ponto-post.PNG](images%2Fswagger-registar-ponto-post.PNG)

 - Endpoint para geração de pdf e solicitação de envio por e-mail

![swagger-registar-ponto-pdf-post.PNG](images%2Fswagger-registar-ponto-pdf-post.PNG)


## Arquitetura 

![arqtuiteura-registro-ponto.jfif](images%2Farqtuiteura-registro-ponto.jfif)