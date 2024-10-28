# Task Management Microservice Project

Welcome to the Task Management Microservices project. This application is designed to streamline task creation, management, and file handling, making it easier for users to organize and track their tasks efficiently in a microservices architecture.


## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)


## Overview

The Task Management Project is a comprehensive solution built on a microservices architecture with the Spring Boot framework. It streamlines task organization and enhances user management with modular, scalable services. Key components include:

- **Discovery Service**: For dynamic service tracking.
- **Gateway Service**: For efficient request routing and load balancing.
- **Dedicated Services**: For file handling, user authentication, and task notifications.

Users can securely register and log in through JWT authentication, manage tasks with functionalities like creation, updating, deletion, and seamlessly attach or download files. The Notification Service provides real-time email updates to keep users informed on task changes. This project lays a robust foundation for efficient task management, emphasizing security, scalability, and ease of integration, making it ideal for team collaboration and organizational productivity.


## Features

### User Service:
- **Authentication & Authorization**: Handles secure login and registration with JWT-token-based authentication.
- **Security Configuration**: Uses custom web security settings to filter and validate requests.
- **Database Integration**: Stores user registration data in PostgreSQL for login and access management.

### Task Service:
- **Core Task Management**: Manages essential task actions such as create, update, delete, and view, with support for file attachments via multipart upload.
- **Service Integration & Communication**: Seamlessly interacts with User, Notification, and File Services using Spring's RestTemplate and WebClient for efficient inter-service communication.
- **Resilience & Logging**: Employs circuit breaker patterns for fault tolerance and SLF4J for comprehensive logging.
- **Data Persistence**: Task data is securely stored in PostgreSQL, ensuring consistency and easy access.

### Notify Service:
- **Email Notifications**: Sends automated email updates to users upon task actions, keeping them informed in real time.
- **Simple Email Composition**: Leverages Spring’s built-in mail framework to efficiently compose and dispatch emails to user addresses.

### File Service:
- **Attachment Upload**: Manages file uploads by extracting multipart files and securely storing them as attachments within the database.
- **Attachment Retrieval**: Provides a streamlined download API for secure access and retrieval of stored attachments.

### Discovery Service:
- **Service Registration and Tracking**: Utilizes Netflix Eureka for registering and monitoring all services, enabling quick status checks for uptime and availability.
- **Enhanced Service Visibility**: Allows for centralized tracking, making it easier to manage and monitor each service in the microservice ecosystem.

### Gateway Service:
- **API Gateway Functionality**: Acts as a single entry point for client requests, routing them to the appropriate microservices.
- **Load Balancing**: Distributes incoming requests across multiple service instances to optimize resource usage and ensure high availability.


## Tech Stack
- **Programming Language**: Java
- **Framework**: Spring Boot
- **Microservices**: Netflix Eureka (Service Discovery), Spring Cloud Gateway
- **Database**: PostgreSQL
- **Authentication**: JWT (JSON Web Token)
- **Security**: Spring Security
- **Email Service**: Spring Mail (SimpleMailMessage and JavaMailSender)
- **Logging**: SLF4J
- **File Handling**: Multipart File Upload/Download
- **Communication**: Spring RestTemplate, WebClient
- **Resilience**: Circuit Breaker (Resilience4j)
- **Build Tool**: Maven
- **Version Control**: Git


## Getting Started

To get started with the project, make sure you have the required tools and dependencies installed.

### Installation:
1. Clone this repository: [https://github.com/NithishD718/TaskManagement_Project1.git](https://github.com/NithishD718/TaskManagement_Project1.git)
2. Navigate to the project directory: 
   ```bash
   cd TaskManagement_Project1
### Configuration:
Configure the properties of each microservice according to your requirements.

### Usage:
Run each microservice individually to start the Task Management system.

### Check Service Status:
You can check the status of all registered microservices here. It will show which services are up and running and their corresponding instances.  
**Eureka Dashboard:** [http://localhost:8761](http://localhost:8761)

## API Endpoints

### User Service routes
- **User login:**                     [http://localhost:8010/user-service/user/login](http://localhost:8010/user-service/user/login)
- **User registration:**              [http://localhost:8010/user-service/user/register](http://localhost:8010/user-service/user/register)
- **Authenticate and get token:**     [http://localhost:8010/user-service/user/authenticate](http://localhost:8010/user-service/user/authenticate)
- **Get user email:**                 [http://localhost:8010/user-service/user/get/{userId}](http://localhost:8010/user-service/user/get/{userId})

### Task Service routes
- **Create task:**                    [http://localhost:8010/task-service/task/create](http://localhost:8010/task-service/task/create)
- **Update task:**                    [http://localhost:8010/task-service/task/update](http://localhost:8010/task-service/task/update)
- **Delete task:**                    [http://localhost:8010/task-service/task/delete/{taskId}](http://localhost:8010/task-service/task/delete/{taskId})
- **View task:**                      [http://localhost:8010/task-service/task/view/{taskId}](http://localhost:8010/task-service/task/view/{taskId})
- **Get all tasks:**                  [http://localhost:8010/task-service/task/get](http://localhost:8010/task-service/task/get)
- **Download Attachment:**             [http://localhost:8010/task-service/task/downloadFile/{taskId}](http://localhost:8010/task-service/task/downloadFile/{taskId})

### Notify Service route
- **Send Email:**                     [http://localhost:8010/email-service/email/send](http://localhost:8010/email-service/email/send)

### File Service route
- **File upload:**                    [http://localhost:8010/file-service/file/upload](http://localhost:8010/file-service/file/upload)
- **File download:**                  [http://localhost:8010/file-service/file/download/{fileId}](http://localhost:8010/file-service/file/download/{fileId})
