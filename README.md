
```markdown
# Project Name

printManagement.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java JDK (11.0.23)
- Maven (3.9.6)
- Docker (26.0.1)
- Docker Compose (v2.23.3)

## Getting Started

To get a local copy up and running, follow these steps.

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/Omar-ABDELKEFI/jje-printManagement
   ```
2. Navigate into the project directory:
   ```sh
   cd jje-printManagement
   ```

### Usage

#### Maven

To compile, build, and run the project using Maven, execute the following command:
```sh
mvn clean compile install jetty:run
```

This will clean the project, compile the source code, package it into a .war file, and deploy it on a Jetty server.

#### Docker Compose

To run the project using Docker Compose, execute the following command:
```sh
docker-compose up
```

This will build the Docker images and start the containers defined in the `docker-compose.yml` file.

