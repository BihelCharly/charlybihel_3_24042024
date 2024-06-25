# Chatop API
#### Project 3 : Développez le back-end en utilisant Java et Spring https://openclassrooms.com/fr/paths/533/projects/1303/assignment

## Project structure
* `main/java/com.chatop.api.config/` : contains Swagger files
* `main/java/com.chatop.api.controller/` : all controllers
* `main/java/com.chatop.api.converter/` : all converters
* `main/java/com.chatop.api.mapper/` : all mappers
* `main/java/com.chatop.api.models/` : Entities, enums and DTOs
* `main/java/com.chatop.api.repository/` : all repositories
* `main/java/com.ochatop.api.security/` : contains security logic = filter + jwt
* `main/java/com.ochatop.api.service/` : all services
* `main/ressources/` : contains Spring resources
* `test/` : contains tests

## Technologies and tools
* MySql 8.0.36
* OpenJDK 21.0.3
* Java 17
* Spring Boot 3.2.5 with Maven
* Lombok

## How to start

### Install MySQL
Documentation at https://dev.mysql.com/doc/
1. [Download MySQL](https://dev.mysql.com/downloads/mysql/)
2. Install MySQL
3. Start MySQL : `sudo systemctl enable mysqld` then `sudo systemctl start mysqld`
4. Create a new root password : `mysqladmin -u root password 'YourRootPassword'`

### Create the database with MySQL
1. Connect to MySQL as root : `sudo mysql -u root -p`
2. Create the new database `CREATE DATABASE chatop;`

### Install Java Development Kit 21
1. [Download JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
2. [How to install JDK 21](https://docs.oracle.com/en/java/javase/21/install/overview-jdk-installation.html)
