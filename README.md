# GATEWAY

This is the gateway of the code challenge

This should be the only microservice that is exposed to users. The others ([Eureka server](https://github.com/alejandra21/discovery-server), [City server](https://github.com/alejandra21/city-service), [Itinerary server](https://github.com/alejandra21/itinerary-service) ) should be hidden in an internal network.

To access the **itinerary service**, we should authenticate using the gateway microservice, get the response JWT, and use it as a Barer token in the rest of the calls from the gateway endpoints server.

This service has a dummy endpoint (`/auth`) that gives the user a JWT token. That token is neccesary to acces other services routed by the gateway.

## Connections required

To work correctly this microservice required the following services:

* [Eureka server](https://github.com/alejandra21/discovery-server): Netflix Eureka server
* [City server](https://github.com/alejandra21/city-service): Microservice in charge of providing information about cities and their connections
* [Itinerary server](https://github.com/alejandra21/itinerary-service)

## Dependencies

* Java 8 or Docker
    
## Instructions

Here are the instructions to run the project (without docker):

1. Go to [Eureka server](https://github.com/alejandra21/discovery-server), [City server](https://github.com/alejandra21/city-service) and [Itinerary server](https://github.com/alejandra21/itinerary-service) and run both projects
2. Clone **this** repository, in your PC (git clone)
3. Execute the following command: 
2. Execute the following command to export env variable an run the project: 
```
cd gateway/config
export $(cat config.env | xargs)
cd ../
./mvnw spring-boot:run
```
3. You will be able to access its api in `http://<itinerary-service>:8081/swagger-ui.html`

It's important to mention that in this swagger documentation You will see also the documentation of the services that this gateway routes

