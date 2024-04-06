# ChatApp
This is a backend app that has minimal features of a  basic chat application 


This document provides an overview of the chat application, its setup instructions, and key functionalities.

Features:

Stores chat messages using MongoDB.
Provides Restful API endpoints for managing chat messages.

Technologies:

Spring Boot
Spring Data MongoDB



Setup Instructions:

Prerequisites:

Java 8+
Maven
MongoDB (local installation or hosted service)
Configuration:

Update application.properties file with your MongoDB connection details (host, port, database name).
Build and Run:

Open a terminal in the project directory.
Run mvn clean install to build the application.
Run mvn spring-boot:run to start the application.


Deployment:

The application is configured to run on port 9090 with the context path /chatapp.

For deployment on a server, package the application as a JAR file and execute it using java -jar <application-name>.jar.
Configure your server to expose port 9090 for incoming requests.


API Endpoints:

Replace <base-url> with http://localhost:9090/chatapp for local testing:

Get all messages: <base-url>/message/all (GET) - Retrieves all chat messages.
Get messages by username: <base-url>/messages/user/{username} (GET) - Retrieves messages sent by a specific user (provide username in the path variable).
Delete message by ID: <base-url>/message/{id} (DELETE) - Deletes a message by its ID (provide ID in the path variable).
Delete last N messages: <base-url>/message/last/{number} (DELETE) - Deletes the specified number of last messages (provide the number in the path variable).

Postman Collection:
Postman collection for the api's is added to the code repo for testing.