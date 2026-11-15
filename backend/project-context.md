# Project context — Back end

> Synthetic code base of the replication package. It is a reduced, fictitious version of the
> back end of the case and does not reproduce the original system.

## Overview

REST API of the diesel oil subsidy module. It exposes basic CRUD operations for class entities,
vessels, and fuel suppliers. The front-end repository consumes this API.

## Technologies

- Java 17, Spring Boot 3.3 (Web, Data JPA)
- Flyway for database migrations
- H2 in-memory database
- Lombok
- JUnit 5 and Mockito

## Architecture

Layered architecture: **Controller → Service → Repository → Model**. DTOs (Java records) are
used at the API boundary; entities are never exposed. The database schema is managed only by
Flyway, and Hibernate validates it at startup (`ddl-auto=validate`).

## Project structure

```
src/main/java/com/example/api/
├── controller/     REST endpoints
├── dto/            request and response records
├── exception/      NotFoundException (HTTP 404)
├── model/          JPA entities
├── repository/     Spring Data repositories
└── service/        business logic
src/main/resources/
├── application.properties
└── db/migration/   Flyway scripts (V<n>__<description>.sql)
src/test/java/com/example/api/
├── ApiApplicationTests.java   loads the application and applies the migrations
└── service/                   unit tests of the services
```

## Resources

| Resource | Path | Table | Attributes |
| --- | --- | --- | --- |
| Class entity | `/class-entities` | `class_entity` | name, tax_id |
| Vessel | `/vessels` | `vessel` | name, registration_number, state, class_entity_id |
| Supplier | `/suppliers` | `supplier` | name, tax_id, state |

Each resource offers: `GET /` (list), `GET /{id}`, `POST /` (201), `PUT /{id}`, and
`DELETE /{id}` (204). An unknown id returns 404.

`state` is the two-letter code of the Brazilian state (UF), for example `RS`.

## Integrations

None.

## Build and test

```
mvn test
```
