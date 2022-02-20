# Lloyds Banking Group ATMs

This application returns a list of ATMs in the Lloyds Banking group.
## Prerequisites
1. Gradle
2. Docker
## Installation

1. Clone the repository into your local directory
2. Navigate to the directory where the repository was cloned
3. Build the application: 
```bash
gradle clean build
```
4. Create a Docker image:
```bash
docker build --build-arg JAR_FILE=build/libs/*.jar -t krinal-solution/lloyds-atms-app . 
```
5. Create a Docker container:
```bash
docker run -p 8080:8080 krinal-solution/lloyds-atms-app .
```
## Usage
1. Once the application has started, navigate to http://localhost:8080/swagger-ui/index.html
2. Expand the endpoint definition GET /v1.0.0/atms and press Try it out.
3. Press execute and see the list of ATMs in the response body