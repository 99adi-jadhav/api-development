# api-development
# Hospital Management System
```markdown
# Project Title:- Hospital Management System.

## Overview :- 
The Hospital Management System(HMS) is a Java-based application developed using Spring Boot for managing Patients data.
It allows the management of Patient data, including retrieving patient details, adding, updating, and deleting Patient records. 
The system uses Spring Boot for the backend and Hibernate for database interaction, with an in-memory H2 database for simplicity.

# Project Structure
- /backend: Contains Java backend (Spring Boot).
- /database: MYSQL scripts for database setup.
```

## Technologies :
- **Java 17+**
- **Spring Boot**
- **Maven**
- **MySQL**
- **RESTful API**


## Features :
- Add, update, delete, and view Patients.
- RESTful API endpoints for CRUD operations.
- MySQL database integration for persistent storage.

---
## Prerequisites :

### Development Tools:
- **IDE :** Spring Tool Suite (STS).
- **Postman :**  for API testing.
## ##Installation Requirements
1. To deploy this project for run

- **Java 17 :**
To download and install Java 17+ on Ubuntu, you can use the following steps:

```bash
  sudo apt update
  sudo apt install openjdk-17-jdk
  java -version
```

- **Spring Boot :**

Method 1 : Using .tar.gz Archive
1.Download the latest version of Spring Tool Suite from the official website:
Go to Spring Tool Suite Downloads and download the .tar.gz file for Linux.
2.Extract the downloaded .tar.gz file :
```bash
tar -xvzf spring-tool-suite-*.tar.gz
```
3.Move the extracted folder to a desired location (e.g., ~/apps) :
```bash
mv spring-tool-suite-* ~/apps/spring-tool-suite
```
4.Navigate to the directory and launch the IDE :
```bash
cd ~/apps/spring-tool-suite
./STS
```

- **Maven :**
   
```bash
sudo apt update
sudo apt install maven
```
Verify the installation:
```bash
mvn -version
```

- **MySQL :** 
 
```bash
sudo apt update
sudo apt install mysql-server
```
Secure your MySQL installation:
```bash
sudo mysql_secure_installation
```

- **Postman :**
   
Postman is used to testing the curd operation.
```bash
sudo snap install postman
```
#Setting Up the Database

1.**Create a database** for Hospital Management System:
  ```sql
  CREATE DATABASE Hospital;
  ```
  ```sql
  USE Hospital;
  ```
   
2.**Create a user and grant permissions :**
  ```sql
  CREATE USER 'root'@'localhost' IDENTIFIED BY 'adij007';
  GRANT ALL PRIVILEGES ON Hospital.* TO 'root'@'localhost';
  FLUSH PRIVILEGES;
  ```

3.**Exit MySQL :**
   ```sql
   EXIT;
   ```
   
#Running the Application
 Backend Setup
 Update the application.properties file in the project with your MySQL configuration:
 properties

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/Hospital
spring.datasource.username=root
spring.datasource.password=adij007
spring.datasource.dbcp2.driver-class-name=com.mysql.cj.jdbc.Driver

 spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
 spring.jpa.hibernate.ddl-auto=update
 ```
 ## Endpoints of the project
 Testing API Endpoints
 Use Postman or cURL to test API endpoints:
 - Post - Post is used to create a new entry.
 - Get - Get is used to retrive the data of new record.
 - Put - Update the entry records.
 - Delete – Delete the entry record from database.


### Clone the repository:
```bash
git clone https://github.com/VMVGITHUB/api-development.git

```


### Build the backend using Maven Navigate you pom.xml path and RUN:
```bash
mvn clean install
```
 After Build is Success:
 • Copy the path of jar file from build logs and run below command 
 ```bash
 java -jar /path/.jar 
 ```
