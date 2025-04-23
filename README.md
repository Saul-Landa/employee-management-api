# Employee Management API

## Introduction

Welcome to the Employee Management API project! This project contains a REST API for managing employee data using Spring Boot, Spring Data JPA and MySQL. 

## Getting Started

### Prerequisites

Before you start, ensure you have the following prerequisites installed on your system:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL Database](https://dev.mysql.com/downloads/installer/)
- [Git](https://git-scm.com/downloads) (optional, for version control)
- [Docker](https://docs.docker.com/get-started/get-docker/) (optional, only in case of running the project with Docker)

### Installation

1. Clone the repository:

```sh
git clone https://github.com/Saul-Landa/employee-management-api.git
```

Navigate to the project directory:

```sh
cd employee-management-api
```

### Execution
Two ways of implementing the project are proposed:
1. Manually with the help of an IDE.
2. Using Docker.

### 1. Execution with an IDE
Open the project with an IDE (IntelliJ IDEA is recommended).
1. In case of using IntelliJ, it is recommended to build the project, in the menu options choose Build > Build Project.
2. Execute the project with the start button.
3. Considerations: in the application.properties file, the local database url must be uncommented and the docker url must be commented.

### 2. Execution with Docker
1. Enter the root of the project from the console, generate the project image with the help of the Dockerfile file, execute the following command:
```sh
docker build -t employee-service:1.0.0 .
```
2. Execute the following command to have the API in a Docker container, as well as the database and subsequently be able to consume the end points from Postman.
```sh
docker compose up -d
```
3. Considerations: in the application.properties file, the docker database url must be uncommented and the local url must be commented.

### Notes
1. The Postman collection to test the end points is located inside the “postman” folder.
2. The API documentation is available at the following link: http://localhost:8080/api/docs. While the API is running.