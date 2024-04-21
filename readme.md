# Estoque GLP

Aplicação para Controle de estoque para revendas de GLP.

## 🚀 Como executar

### 📋 Pré-requisitos

* JDK 21 ou superior
* Banco MySQL ou docker compose

### 🔧 Instalação

Para baixar as dependências, compilar e executar os testes execute:

```
./mvnw clean install
```

A execução é possível de ser executada em dois modos:

#### Com MySQL

Necessário ter o MySQL já configurado e rodando no seu computador, para mais informações acesse: [MySQL](https://www.mysql.com/).
Após iniciar o banco configurar as seguintes variáveis de ambiente:

```
GLP_DATABASE_USERNAME={seu usuário no banco}
GLP_DATABASE_PASSWORD={sua senha}
GLP_DATABASE_URL={url jdbc do banco}
```

Após isso execute o comando:

```
./mvnw spring-boot:run
```

#### Com Docker Compose

A aplicação pode iniciar uma imagem docker do MySQL e se conectar automaticamente a ela.
Para essa opção é necessário ter docker compose instalado, para mais informações consulte [Documentação docker compose](https://docs.docker.com/compose/).

Para essa opção configure as seguintes variáveis de ambiente:
```
GLP_DATABASE_NAME={nome do banco que será criado}
GLP_DATABASE_USERNAME={Usuário do banco que será criado}
GLP_DATABASE_PASSWORD={Senha do banco que será criado}
GLP_DATABASE_ROOT_PASSWORD={Senha do usuário root do banco que será criado}
```

Após isso execute o comando:

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=compose
```

## ⌨ Uso

O projeto será executado em http://localhost:8080/ e será possível enviar requisições a API.

## 🛠️ Construído com

Criado utilizando as tecnologias:

* [Java 21](https://jdk.java.net/21/) - Linguagem
* [Maven](https://maven.apache.org/) - Gerenciamento de Dependências
* [Spring-boot 3.2.5](https://spring.io/projects/spring-boot/) - Framework web

## ✒️ Autor

* [Caio Lima](https://github.com/Caio042)
* 