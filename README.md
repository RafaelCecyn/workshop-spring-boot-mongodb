# Projeto MongoDB com Spring Boot
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/RafaelCecyn/workshop-spring-boot-mongodb/blob/main/LICENSE)

# Sobre o projeto
Este projeto é uma aplicação backend desenvolvida durante o curso (Java COMPLETO Programação Orientada a Objetos + Projetos) que consiste num web services com operações de CRUD para um sistema de postagem com comentários. Os dados podem ser inseridos automaticamente no banco de dados MongoDB ou podem ser inseridos via requisições. Eu implementei o Swagger como forma de enriquecer o projeto.

## Modelo de domínio
![Modelo Conceitual](https://github.com/RafaelCecyn/workshop-spring-boot-mongodb/blob/main/assets/Modelo_de_Dominio.png)

## Camada lógica
![Modelo Conceitual](https://github.com/RafaelCecyn/workshop-spring-boot-mongodb/blob/main/assets/Modelo_Logico.png)


# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- Swagger

## Banco de dados
- MongoDB

# Como executar o projeto

## Back end
Pré-requisitos: Java 17, MongoDB

```bash
# clonar repositório
git clone https://github.com/RafaelCecyn/workshop-spring-boot-mongodb

# inicializar o MongoDB
mongod ou mongosh (Ubuntu)

# entrar na pasta do projeto back end
cd src/com.rafaelcecyn.workshopmongo

# executar o projeto
run  no arquivo WorkshopmongoApplication.java
```
