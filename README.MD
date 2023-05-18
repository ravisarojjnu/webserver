# Basic HTTP Web Server

This is a basic HTTP web server written in Java using Maven. The server listens on the default port 8080 and provides a simple response to incoming requests.

## Prerequisites
- Java Development Kit (JDK) installed
- Maven installed

## Getting Started
1. Clone the repository:
git clone https://github.com/your-username/basic-http-web-server.git

2. Navigate to the project directory:
cd webserver

3. Build the project using Maven:
mvn clean package

4. Run the server:
java -jar target/basic-http-web-server.jar

5. The server is now running and listening on port 8080. You can access it by opening a web browser and entering `http://localhost:8080` in the address bar.

## Server Functionality
- When a client connects to the server, it reads the request line.
- The server generates a simple HTTP response.
- The response is sent back to the client.

## Customization
- You can modify the server code located in the `src/main/java` directory to add more functionality or customize the response logic.
- After making changes, rebuild the project using Maven (`mvn clean package`) and run the server again.

## Contributing
Contributions are welcome! If you find any issues or would like to add new features, feel free to open an issue or submit a pull request.

## License
This project is licensed under the [MIT License](LICENSE).