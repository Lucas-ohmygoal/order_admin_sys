# Order Admin System

An order management system built with Spring Boot, MyBatis, and Thymeleaf.

## Features

- User authentication (login/register)
- Customer management
- Product management
- Order management

## Local Development

1. Set up PostgreSQL database:
   ```sql
   CREATE DATABASE order_admin_db;
   ```

2. Run the PostgreSQL SQL script:
   ```bash
   psql -d order_admin_db -f order_admin_db_postgres.sql
   ```

3. Update `src/main/resources/application.properties` with your database credentials.

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access at http://localhost:8080

## Deployment on Render with Supabase

1. **Create Supabase Account**: Sign up at https://supabase.com

2. **Create Database**:
   - Go to your Supabase dashboard
   - Navigate to SQL Editor
   - Copy and paste the contents of `order_admin_db_postgres.sql`
   - Run the SQL to create tables and insert sample data

3. **Get Connection Details**:
   - In Supabase dashboard, go to Settings > Database
   - Copy the connection string (it will look like: `postgresql://postgres:[password]@[host]:5432/postgres`)

4. **Create Render Account**: Sign up at https://render.com

5. **Connect Repository**: Link your GitHub/GitLab repository

6. **Create Web Service**:
   - Choose "Web Service"
   - Select your repository
   - Runtime: Docker
   - Dockerfile Path: Dockerfile (optional, Render auto-detects)

7. **Environment Variables**:
   - `PORT`: Automatically set by Render
   - `DATABASE_URL`: Your Supabase PostgreSQL connection string
   - `DB_USERNAME`: From Supabase connection details
   - `DB_PASSWORD`: From Supabase connection details

## Tech Stack

- Java 17
- Spring Boot 2.6.15
- MyBatis
- PostgreSQL
- Thymeleaf
- Maven
