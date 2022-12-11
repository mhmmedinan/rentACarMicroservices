# RentACarMicroservices
Car Rental Project with Microservice Architecture

# Config Server

It is the intended approach for microservices to migrate to development and production environments

For installation, we need to install the spring cloud config server package in the pom.xml file

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/configserverpom.png" width="auto">

application.yml file configuration

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/configserverapplication.png" width="auto">

For the config server to work, you need to write the <b>@EnableConfigServer</b> annotation in the configserver application startup class.


