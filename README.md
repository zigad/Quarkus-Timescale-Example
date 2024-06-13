# Quarkus TimescaleDB Example

This project demonstrates a simple Quarkus application that interacts with TimescaleDB to manage sensor data. This is
more of a playground for my personal use but feel free to optimise, or use to code used here, The application showcases
how to use Quarkus for creating a RESTful API to insert and retrieve sensor data efficiently using TimescaleDB's
hypertable capabilities.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Clone the Repository](#clone-the-repository)
    - [Configure Database](#configure-database)
    - [Run the Application](#run-the-application)
- [Endpoints](#endpoints)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)

## Features

- RESTful API for managing sensor data
- TimescaleDB hypertable for efficient time-series data handling
- Automatic database schema creation and data initialization
- Demonstrates use of Quarkus with a PostgreSQL-based TimescaleDB

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- Docker (for running TimescaleDB)
- Quarkus CLI (optional, for easier project management)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/zigad/quarkus-timescaledb-example.git
cd quarkus-timescaledb-example
```

### Configure Database

1. **Start TimescaleDB**:

   You can use Docker to quickly start a TimescaleDB instance:

   ```bash
   docker run -d --name timescaledb -p 5432:5432 -e POSTGRES_PASSWORD=yourpassword timescale/timescaledb:latest-pg14
   ```

   Alternatively, you can use the provided `docker-compose.yaml` file to start a TimescaleDB instance provided in the
   root folder.
   To start the TimescaleDB instance using Docker Compose, run:

   ```bash
   docker-compose up -d
   ```

2. **Update Database Configuration**:

   Modify [application.properties](src%2Fmain%2Fresources%2Fapplication.properties) to match your TimescaleDB
   configuration.

### Run the Application

Use Maven to build and run the application:

```bash
./mvnw compile quarkus:dev
```

Make sure that TimescaleDB is running in docker.
Quarkus will start in development mode, enabling hot reload for faster development.

## Endpoints

The application exposes the following RESTful endpoints:

- **Get All Sensors**:
    - **GET** `/sensorEntities`
    - Response: `[{ "id": 1, "type": "Temperature", "location": "Living Room", "sensorDataList": [...] }]`


- **Add a Sensor**:
    - **POST** `/sensorEntities`
    - Request Body: `{ "type": "Temperature", "location": "Living Room" }`


- **Add Sensor Data**:
    - **POST** `/generateData/{count}`
    - Replace `{count}` with the number of random data points to generate.

## Project Structure

```
src
├── main
│   ├── docker
│   ├── java
│   │   └── si
│   │       └── deisinger
│   │           ├── dto
│   │           │   └── SensorDTO.java
│   │           ├── entity
│   │           │   ├── Sensor.java
│   │           │   └── SensorData.java
│   │           ├── repository
│   │           │   ├── SensorDataRepository.java
│   │           │   └── SensorRepository.java
│   │           └── service
│   │               ├── SensorDataService.java
│   │               ├── SensorService.java
│   │               └── TimescaleResource.java
│   └── resources
│       ├── application.properties
│       └── import.sql
└── target
```

- **src/main/java/si/deisinger/dto**: Data Transfer Objects
- **src/main/java/si/deisinger/entity**: JPA entities for Sensor and SensorData
- **src/main/java/si/deisinger/repository**: Repositories for accessing the database
- **src/main/java/si/deisinger/service**: Service layer for business logic
- **src/main/resources**: Configuration files
    - **application.properties**: Quarkus application configuration
    - **import.sql**: SQL script for initializing database schema and data

## Technologies Used

- **Quarkus**: Supersonic Subatomic Java framework
- **TimescaleDB**: Time-series database based on PostgreSQL
- **Hibernate ORM**: Object-relational mapping framework
- **RESTEasy Reactive**: Reactive extensions for JAX-RS




