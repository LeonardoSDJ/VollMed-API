# API VollMed

## Descrição

A API VollMed é uma API RESTful construída com Spring Boot para gerenciar uma clínica médica. Ela fornece funcionalidades para gerenciamento de pacientes, médicos, consultas e autenticação de usuários.

## Tecnologias

- Java 17
- Spring Boot 3.3.2
- Spring Data JPA
- Spring Security
- Flyway para migrações de banco de dados
- Banco de dados H2 (para desenvolvimento)
- Lombok
- JWT para autenticação
- SpringDoc OpenAPI para documentação da API

## Funcionalidades

1. Gerenciamento de Pacientes
2. Gerenciamento de Médicos
3. Agendamento de Consultas
4. Autenticação de Usuários
5. Documentação da API com Swagger UI

## Endpoints

### Controlador de Pacientes

- `GET /pacientes`: Recuperar uma lista paginada de pacientes
- `PUT /pacientes`: Atualizar informações do paciente
- `POST /pacientes`: Criar um novo paciente
- `GET /pacientes/{id}`: Recuperar um paciente específico por ID
- `DELETE /pacientes/{id}`: Excluir um paciente por ID

### Controlador de Médicos

- `GET /medicos`: Recuperar uma lista paginada de médicos
- `PUT /medicos`: Atualizar informações do médico
- `POST /medicos`: Criar um novo médico
- `GET /medicos/{id}`: Recuperar um médico específico por ID
- `DELETE /medicos/{id}`: Excluir um médico por ID

### Controlador de Autenticação

- `POST /login`: Autenticar um usuário e gerar um token JWT

### Controlador de Consultas

- `POST /consultas`: Agendar uma nova consulta

### Controlador de Cadastro de Usuários

- `POST /cadastro-usuario`: Cadastrar um novo usuário

## Configuração e Execução

1. Clone o repositório
2. Configure as variáveis de ambiente necessárias (se houver)
3. Execute `mvn clean install` para construir o projeto
4. Execute `mvn spring-boot:run` para iniciar a aplicação

A API estará disponível em `http://localhost:8080`

## Documentação da API

Após iniciar a aplicação, a documentação da API estará disponível em:

`http://localhost:8080/swagger-ui.html`

## Desenvolvimento

Este projeto usa o Spring Boot DevTools para desenvolvimento rápido. As alterações no código-fonte serão refletidas automaticamente sem necessidade de reiniciar a aplicação.

## Testes

Execute `mvn test` para rodar os testes unitários.

## Collections do Postman

Para facilitar o teste e a interação com a API, fornecemos uma collection do Postman que inclui todos os endpoints disponíveis. Esta collection está organizada nas seguintes categorias:

1. Pacientes
2. Médicos
3. Autenticação
4. Consultas
5. Cadastro de Usuário

### Como usar a collection

1. Baixe e instale o [Postman](https://www.postman.com/downloads/)
2. Importe a collection do Postman fornecida no arquivo `VollMed_API_Collection.json`
3. Configure a variável de ambiente `baseUrl` no Postman para apontar para a URL base da sua API (por padrão, `http://localhost:8080`)
4. Use as requisições pré-configuradas para interagir com a API

### Autenticação

Algumas requisições podem exigir autenticação. Após fazer login usando o endpoint de autenticação, você receberá um token JWT. Configure este token no header `Authorization` das requisições subsequentes como `Bearer {seu_token_aqui}`.

### Exemplos de uso

A collection inclui exemplos de corpo de requisição para criar e atualizar recursos. Você pode usar esses exemplos como base e modificá-los conforme necessário para seus testes.
