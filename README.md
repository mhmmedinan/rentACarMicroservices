# RentACarMicroservices
Car Rental Project with Microservice Architecture

# Config Server

It is the intended approach for microservices to migrate to development and production environments

For installation, we need to install the <b>spring cloud config server package</b> in the <b>pom.xml</b> file

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/configserverpom.png" width="auto">

<b>application.yml</b> file configuration

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/configserverapplication.png" width="auto">

<ul>

<li>For the config server to work, you need to write the <b>@EnableConfigServer</b> annotation in the configserver application startup class.</li>
<li>If you want to run it on Docker, you can install it with the <b>docker pull muhammedinan/config-server-image</b> command from <a href="https://hub.docker.com/repository/docker/muhammedinan/config-server-image">https://hub.docker.com/repository/docker/muhammedinan/config-server-image </a></li>
<li>You can run it as a container on docker with the command <b>docker run -d -p 8888:8888  muhammedinan/config-server-image</b> </li>
</ul>


# Discovery Server

By registering our microservice applications to the eureka server, we can communicate with each other from a single point. Thanks to this registration process, the eureka discovery server also undertakes the load balancer task. After the projects are up, our microservices, defined as eureka clients, are connected to the eureka server, and in this way, we monitor and manage features such as network communication and load balancer of our applications from the center.

For installation, we need to install the <b>spring-cloud-starter-netflix-eureka-server</b> package in the <b>pom.xml</b> file
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/discoveryserverpom.png" width="auto">

In order to run discovery server in development and production environments, we need to configure the config server.

We need to add the <b>spring cloud config server package</b> to the <b>pom.xml</b> file
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/discoveryserverpom1.png" width="auto">
