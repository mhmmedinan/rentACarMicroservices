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
<li>If you want to run it on Docker, you can install it with the <b>docker pull muhammedinan/config-server-image</b> command from <a href="https://hub.docker.com/r/muhammedinan/config-server-image">https://hub.docker.com/r/muhammedinan/config-server-image</a></li>
<li>You can run it as a container on docker with the command <b>docker run -d -p 8888:8888  muhammedinan/config-server-image</b> </li>
</ul>


# Discovery Server

By registering our microservice applications to the eureka server, we can communicate with each other from a single point. Thanks to this registration process, the eureka discovery server also undertakes the load balancer task. After the projects are up, our microservices, defined as eureka clients, are connected to the eureka server, and in this way, we monitor and manage features such as network communication and load balancer of our applications from the center.

For installation, we need to install the <b>spring-cloud-starter-netflix-eureka-server</b> package in the <b>pom.xml</b>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/discoveryserverpom.png" width="auto">

In order to run discovery server in development and production environments, we need to configure the config server.

We need to add the <b>spring cloud config client</b> package to the <b>pom.xml</b> file

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/discoveryserverpom1.png" width="auto">

<b>application.yml</b> file configuration

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/discoveryapplication.png" width="auto">

<li>For the eureka server to work, you need to write the <b>@EnableEurekaServer</b> annotation in the discoveryserver application startup class.</li>
<li>Discovery Server Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/eureka-server-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/eureka-server-dev.yml</a></li>
<li>Discovery Server Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/eureka-server-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/eureka-server-prod.yml</a></li>

<li>If you want to run it on Docker, you can install it with the <b>docker pull muhammedinan/discovery-server-image</b> command from <a href="https://hub.docker.com/r/muhammedinan/discovery-server-image">https://hub.docker.com/r/muhammedinan/discovery-server-image </a></li>
<li>You can run it as a container on docker with the command <b>docker run -d -p 9001:9001 muhammedinan/discovery-server-image</b> </li>

# Api Gateway

The main function of API Gateway is to receive the request from the client and forward it to the appropriate service.

We need to install <b>spring-cloud-starter-gateway package</b> in api gateway <b>pom.xml</b>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apigatewaypom1.png" width="auto">

We need to add the <b>spring cloud config client</b> package to the pom.xml file

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apigatewaypom2.png" width="auto">

For eureka server connection we need to install <b>spring-cloud-starter-netflix-eureka-client</b> package
  
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apigatewaypom3.png" width="auto">


