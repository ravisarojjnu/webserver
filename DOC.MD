# Design Document: Web Server in Java

## Introduction
The purpose of this design document is to outline the architecture and implementation details of a web server in Java that can serve static content and route API calls to specific methods. The web server will be designed to handle concurrent requests efficiently and provide a scalable and reliable solution.

## Architecture Overview
The web server will follow a multi-threaded architecture, where each incoming request will be handled by a separate thread. This approach ensures concurrent processing of requests and prevents blocking of the main server thread. The server will consist of the following components:

## 2.1. Server Initialization

- A main class responsible for initializing and starting the web server.
- The server will listen on a specified port for incoming connections.
### 2.2. Request Handling

- A request handler class that accepts incoming client connections and processes each request.
- Upon receiving a request, the handler will parse the HTTP headers and determine the type of request (API call or static content).
- If it's an API call, the handler will extract the route from the request URL and forward it to the appropriate method for processing.
- If it's a request for static content, the handler will locate and serve the requested file.
### 2.3. API Routing

- A set of classes or annotations to define API routes and associated methods.
- Each API route will be mapped to a specific method that handles the corresponding request.
- The routing mechanism will match the incoming request URL to the registered routes and invoke the appropriate method.
### 2.4. Static Content Serving

- A file handler class responsible for locating and serving static content.
- The server will maintain a root directory where the static files are stored.
- The file handler will receive a request for static content, validate the file path, and return the file's contents if found.

## Implementation Details
### 3.1. Server Initialization

- Create a Java class for the server and implement the necessary methods to start and stop the server.
- Use the Java Socket API to listen for incoming connections on a specified port.
- Spawn a new thread for each incoming connection and pass it to the request handler.
### 3.2. Request Handling

- Implement a request handler class that extends the Thread class.
- In the run() method, handle the following steps:
    - Read the incoming request from the client socket.
    - Parse the HTTP headers to determine the request type (GET, POST, etc.).
- If it's an API call:
    - Extract the route from the request URL.
    - Match the route to the appropriate method using the routing mechanism.
    - Invoke the method and send the response back to the client.
- If it's a request for static content:
    - Extract the file path from the request URL.
    - Validate the file path and check if it exists.
    - If the file is found, read its contents and send them back to the client.
### 3.3. API Routing

- Create a routing class that registers API routes and their associated methods.
- Use annotations or a configuration file to map routes to methods.
- Implement a routing mechanism that matches the incoming request URL to the registered routes and invokes the corresponding method.
### 3.4. Static Content Serving

- Implement a file handler class that receives requests for static content.
- Define a root directory where the static files are stored.
- Validate the requested file path and check if it exists within the root directory.
- If the file is found, read its contents and send them back to the client.

## Conclusion
This design document provides an outline for implementing a web server in Java capable of serving static content and routing API calls to specific methods. The proposed architecture ensures concurrent processing of requests and scalability. By following this design, you can build a robust and