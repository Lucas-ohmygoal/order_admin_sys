# Order Admin System

An order management system built with Spring Boot, MyBatis, and Thymeleaf.

## Features

- User authentication (login/register)
- Customer management
- Product management
- Order management

## Local Development

1. Set up MySQL database:
   ```sql
   CREATE DATABASE order_admin_db;
   ```

2. Update `src/main/resources/application.properties` with your database credentials.

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access at http://localhost:

## Tech Stack

- Java 17
- Spring Boot 2.6.15
- MyBatis
- MySQL
- Thymeleaf
- Maven
