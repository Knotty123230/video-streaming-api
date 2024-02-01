Video Streaming Application

Welcome to our video streaming application! This application leverages various technologies such as Kafka, Elasticsearch, Kibana, MinIO, Spring Cloud, Spring Data, Spring Boot, PostgreSQL, Eureka Server, and API Gateway to provide a robust and scalable video streaming solution.

Prerequisites

Docker installed on your machine.
Basic understanding of Docker Compose.
Getting Started
To run the project, follow these steps:

Clone this repository to your local machine.
Navigate to the project directory.
Ensure Docker is running.
Run the start.sh script:
bash
Copy code
./start.sh
This script will build Docker images for the microservices and start the Docker Compose setup.
Services Overview

Kafka: Used for real-time data streaming and messaging.
Elasticsearch: Powers search functionality for videos.
Kibana: Provides visualization and management for Elasticsearch.
MinIO: Object storage service used to store video files.
Spring Cloud: Provides tools for building microservices.
Spring Data: Simplifies data access for the Spring applications.
Spring Boot: Facilitates the development of Spring applications.
PostgreSQL: Database management system for storing application data.
Eureka Server: Service registry for enabling service discovery.
API Gateway: Acts as a single entry point for all microservices.


Usage

Once the Docker Compose setup is running, you can interact with the application through its APIs. Documentation for each microservice's API endpoints can be found in their respective codebases.
