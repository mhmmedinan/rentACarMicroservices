# RentACarMicroservices
Car Rental Project with Microservice Architecture

# Config Server

It is the intended approach for microservices to migrate to development and production environments

For installation, we need to install the spring cloud config server package in the pom.xml file

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/configserverpom.png" width="auto">

application.yml file configuration

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/configserverapplication.png" width="auto">

For the config server to work, you need to write the <b>@EnableConfigServer</b> annotation in the configserver application startup class.

If you want to run it on Docker, you can install it with the docker <b style="color:black">docker pull muhammedinan/config-server-image</b> command from <a href="https://hub.docker.com/repository/docker/muhammedinan/config-server-image">https://hub.docker.com/repository/docker/muhammedinan/config-server-image </a>

You can run it as a container on docker with the command <b>docker run -d -p 8888:8888  muhammedinan/config-server-image</b> 




