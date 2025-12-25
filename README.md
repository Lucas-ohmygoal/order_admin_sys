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

4. Access at http://localhost:8080

## Deployment on Render

1. **Create Render Account**: Sign up at https://render.com

2. **Connect Repository**: Link your GitHub/GitLab repository

3. **Create Web Service**:
   - Choose "Web Service"
   - Select your repository
   - Build Command: `./mvnw clean package` (or `mvn clean package` if Maven is installed)
   - Start Command: `java -jar target/*.jar`

4. **Environment Variables**:
   - `PORT`: Automatically set by Render
   - `DATABASE_URL`: Your database connection string
   - `DB_USERNAME`: Database username
   - `DB_PASSWORD`: Database password

5. **Database Setup**:
   - Create a MySQL database on Render or use an external MySQL provider
   - Run the SQL script `order_admin_db.sql` to initialize tables

## Tech Stack

- Java 17
- Spring Boot 2.6.15
- MyBatis
- MySQL
- Thymeleaf
- Maven
