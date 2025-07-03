# Literalura

O **Literalura** é uma aplicação Java desenvolvida com **Spring Boot**, que permite buscar, armazenar e consultar livros e autores da API pública do [Projeto Gutenberg](https://gutendex.com/). A aplicação roda em ambiente de terminal e oferece funcionalidades interativas para manipulação dos dados.

## 📚 Funcionalidades

* **Buscar livros por título**
  Permite consultar livros diretamente pela API do Projeto Gutenberg, armazenando o primeiro resultado encontrado no banco de dados.

* **Listar livros registrados**
  Exibe todos os livros que foram previamente salvos no banco.

* **Listar autores registrados**
  Exibe todos os autores já registrados localmente.

* **Listar autores vivos em um determinado ano**
  Permite consultar quais autores estavam vivos em um ano específico com base nos dados de nascimento e morte.

* **Listar livros por idioma**
  Filtra e exibe os livros cadastrados conforme o idioma escolhido (ex: `en`, `pt`, `es`).

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.5.0**
* **Spring Data JPA**
* **MySQL**
* **Maven**

## ▶️ Como Usar

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/literalura.git
```

### 2. Crie o banco de dados

Crie manualmente o banco com o nome:

```sql
CREATE DATABASE literalura;
```

### 3. Configure o `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Execute o projeto

```bash
./mvnw spring-boot:run
```

### 5. Navegue pelo menu no terminal

Ao iniciar a aplicação, o terminal exibirá:

```
Escolha o número de sua opção
1 - Buscar livros pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros em um determinado idioma
0 - Sair
```

Basta digitar a opção desejada e seguir as instruções no console.

---

## 🌐 Fonte dos Dados

Todos os dados de livros e autores são obtidos da [API Gutendex](https://gutendex.com/), uma interface pública para o Projeto Gutenberg.

---

## 💡 Exemplo de Uso

**Buscar livro pelo título:**

* Digite: `1`
* Depois informe o nome do livro (ex: `Sherlock Holmes`)

**Filtrar livros por idioma:**

* Digite: `5`
* Informe o idioma desejado (ex: `en` para inglês, `pt` para português)
