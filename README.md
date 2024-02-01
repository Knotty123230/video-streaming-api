<!DOCTYPE html>
<html lang="en">



<body>

  <h1>Video Streaming Application</h1>

  <p>Welcome to our video streaming application! This application leverages various technologies such as Kafka, Elasticsearch, Kibana, MinIO, Spring Cloud, Spring Data, Spring Boot, PostgreSQL, Eureka Server, and API Gateway to provide a robust and scalable video streaming solution.</p>

  <h2>Prerequisites</h2>

  <ul>
    <li>Docker installed on your machine.</li>
    <li>Basic understanding of Docker Compose.</li>
  </ul>

  <h2>Getting Started</h2>

  <p>To run the project, follow these steps:</p>

  <ol>
    <li>Clone this repository to your local machine.</li>
    <li>Navigate to the project directory.</li>
    <li>Ensure Docker is running.</li>
    <li>Run the start.sh script:</li>
  </ol>

  <pre><code>./start.sh</code></pre>

  <p>This script will build Docker images for the microservices and start the Docker Compose setup.</p>

  <h2>Services Overview</h2>

  <ul>
    <li>Kafka: Used for real-time data streaming and messaging.</li>
    <li>Elasticsearch: Powers search functionality for videos.</li>
    <li>Kibana: Provides visualization and management for Elasticsearch.</li>
    <li>MinIO: Object storage service used to store video files.</li>
    <li>Spring Cloud: Provides tools for building microservices.</li>
    <li>Spring Data: Simplifies data access for the Spring applications.</li>
    <li>Spring Boot: Facilitates the development of Spring applications.</li>
    <li>PostgreSQL: Database management system for storing application data.</li>
    <li>Eureka Server: Service registry for enabling service discovery.</li>
    <li>API Gateway: Acts as a single entry point for all microservices.</li>
  </ul>

  <h2>Usage</h2>

  <p>Once the Docker Compose setup is running, you can interact with the application through its APIs. Documentation for each microservice's API endpoints can be found in their respective codebases.</p>

</body>

</html>
