# Train Management App

A simple Java application for managing train seat bookings.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Building the Project

```bash
mvn clean compile
```

## Running the Application

```bash
mvn exec:java -Dexec.mainClass="com.example.trainmanagement.Main"
```

## Running Tests

```bash
mvn test
```

## Project Structure

- `src/main/java/` - Main source code
- `src/test/java/` - Test code
- `pom.xml` - Maven configuration

## Features

- Create trains with specified capacity
- Book seats on trains
- Check available seats
- Basic validation for seat bookings