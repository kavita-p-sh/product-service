# product-service

Product Service is a Spring Boot based microservice responsible for handling product-related operations 
such as product creation, updating, deletion, stock management, and product retrieval for the ecommerce application.

_____________________________________________________________________________________________________________________

## Features

- Create Product
- Update Product
- Delete Product
- Fetch Products
- Fetch Product By ID
- Product Stock Management
- Product Filtering Support
- Role-Based Authorization
- Request Validation using DTOs
- Global Exception Handling
- Redis Caching Support
- Audit Information Tracking

_____________________________________________________________________________________________________________________

## Technologies Used

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Redis
- Maven
- Lombok
- JWT Authentication
- Swagger / OpenAPI

_____________________________________________________________________________________________________________________
## Architecture

The application follows layered architecture:

```text
Controller → Service → Repository → Database
```

---

## Design Patterns Used

- Dependency Injection
- Repository Pattern
- DTO Pattern
- Layered Architecture

_____________________________________________________________________________________________________________________

## Project Structure

```text
src/main/java
│
├── controller
├── service
├── repository
├── dto
├── entity
├── config
├── security
├── exception
├── util
└── audit
```

_____________________________________________________________________________________________________________________
## Configuration

Update the following properties inside:

```properties
src/main/resources/application.properties
```

```properties
# Application
spring.application.name=product-service
server.port=8097

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/ecom_db
spring.datasource.username=root
spring.datasource.password=your_password

# JPA
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

# JWT
jwt.secret=your_secret_key
jwt.expiration=3600000

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

_____________________________________________________________________________________________________________________
## Build Project

```bash
mvn clean install
```

---

## Run Project

```bash
mvn spring-boot:run
```

_____________________________________________________________________________________________________________________

## Swagger Documentation

After starting the application:

Swagger UI:
```text
http://localhost:8097/swagger-ui/index.html
```

OpenAPI Docs:
```text
http://localhost:8097/v3/api-docs
```

_____________________________________________________________________________________________________________________

## Main APIs

### Product APIs

| Method | API | Description |
|---|---|---|
| POST | `/api/products` | Create product |
| GET | `/api/products` | Fetch all products |
| GET | `/api/products/{productId}` | Fetch product by ID |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |

_____________________________________________________________________________________________________________________
## Security

- JWT-based authentication
- Spring Security integration
- Protected APIs using authorization filters
- Role-based access control

_____________________________________________________________________________________________________________________
## Caching

Redis is used for:

- Product caching
- Frequently accessed product data
- Performance optimization

_____________________________________________________________________________________________________________________
## Profiles

```text
application-dev.properties   → Development Environment
application-prod.properties  → Production Environment
```
