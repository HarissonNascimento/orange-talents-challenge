##

![GitHub build workflow](https://img.shields.io/github/workflow/status/HarissonNascimento/orange-talents-challenge/orange-talents-CI)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/HarissonNascimento/orange-talents-challenge)
![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed/HarissonNascimento/orange-talents-challenge)

## :speech_balloon:O que há neste documento
- [Missão deste projeto](https://github.com/HarissonNascimento/orange-talents-challenge#hammermissão-deste-projeto)
- [Executando testes unitários](https://github.com/HarissonNascimento/orange-talents-challenge#executando-testes-unitários)
- [Executando testes de integração](https://github.com/HarissonNascimento/orange-talents-challenge#executando-testes-de-integração)
- [Executando todos os testes](https://github.com/HarissonNascimento/orange-talents-challenge#executando-todos-os-testes)
- [Executando o projeto com Docker](https://github.com/HarissonNascimento/orange-talents-challenge#whaleexecutando-o-projeto-com-docker)
- [Executando o projeto localmente](https://github.com/HarissonNascimento/orange-talents-challenge#computerexecutando-o-projeto-localmente)
- [Endpoints disponíveis](https://github.com/HarissonNascimento/orange-talents-challenge#mag_rightendpoints-disponíveis)

## :hammer:Missão deste projeto

_Você está fazendo uma API REST que precisa suportar o processo de abertura de nova conta no banco. O primeiro passo desse fluxo é cadastrar os dados pessoais de uma pessoa. Precisamos de apenas algumas informações obrigatórias:_
> - _**Nome**_
> - _**E-mail**_
> - _**CPF**_
> - _**Data de nascimento**_

_Caso os dados estejam corretos, é necessário gravar essas informações no banco de dados relacional e retornar o status adequado para a aplicação cliente, que pode ser uma página web ou um aplicativo mobile._

_Você deve fazer com que sua API devolva a resposta adequada para o caso de falha de validação._

## Executando testes unitários

No terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw test -Ponly-unit-tests
```
no Windows

```shell
mvnw.cmd test -Ponly-unit-tests
```

## Executando testes de integração

No terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw test -Ponly-integration-tests
```
no Windows

```shell
mvnw.cmd test -Ponly-integration-tests
```

## Executando todos os testes

No terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw test -Pall-tests
```
no Windows

```shell
mvnw.cmd test -Pall-tests
```

## :whale:Executando o projeto com Docker

No terminal, navegue até a pasta raiz do projeto e execute

```shell
docker-compose up
```

## :computer:Executando o projeto localmente

Para que não seja necessário instalar nada em sua máquina, vamos alterar o banco de dados da nossa aplicação para um banco em mémoria.

Para isso, no arquivo [pom.xml](https://github.com/HarissonNascimento/orange-talents-challenge/blob/main/pom.xml) remova a dependência

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```
E altere a o escopo da dependência h2 de _test_ para _runtime_ da seguinte forma

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

Agora, reescreva o arquivo [application.yml](https://github.com/HarissonNascimento/orange-talents-challenge/blob/main/src/main/resources/application.yml) da seguinte forma

```yml
spring:
  application:
    name: orange-talents
  datasource:
    url: jdbc:h2:mem:orange-talents
    username: orange
    password: harisson
    driverClassName: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: update
```

Feito isso, no terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw clean install 
```

no Windows

```shell
mvnw.cmd clean install 
```

Após a conclusão, execute

```shell
./mvnw spring-boot:run
```

no Windows

```shell
mvnw.cmd spring-boot:run
```

## :mag_right:Endpoints disponíveis

Antecedido por http://\<seu-host\>:8080 temos os endpoints

- /user/sign-up \(POST\) 
- /swagger-ui.html \(Documentação da API\)




