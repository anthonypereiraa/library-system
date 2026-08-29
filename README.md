# Library System

A library management system developed with **Java and Spring Boot**, focused on applying backend development concepts such as REST APIs, JPA, validation, DTOs, and JWT authentication and authorization.

## Technologies

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA / Hibernate
* Spring Validation
* Spring Security
* JWT
* PostgreSQL
* Maven

## Features

The system manages:

* Books
* Students
* Courses
* Loans

It implements business rules such as:

* ISBN validation
* Book availability and quantity management
* Student validation
* Loan management
* Checking active loans

## Security

Authentication is implemented with **Spring Security and JWT**.

Authorization uses **roles and authorities** to control access to different operations.

Example:

```text
student:create
student:read
student:update
student:delete
```

## Architecture

The project follows a layered architecture with:

* Controllers
* Services
* Repositories
* Entities
* DTOs
* Security

Request DTOs are used to define the API contracts and validate incoming data.

## Running

Requirements:

* Java 21
* Maven
* PostgreSQL

The application runs on:

```text
http://localhost:8080
```
