# RentACarMicroservices
Car Rental Project with Microservice Architecture

# Config Server

<li>It is the intended approach for microservices to migrate to development and production environments</li>
<li>For installation, we need to install the <b>spring cloud config server package</b> in the <b>pom.xml</b> file</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/configserverpom.png" width="auto">

<li><b>application.yml</b> file configuration</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/configserverapplication.png" width="auto">

<ul>
<li>For the config server to work, you need to write the <b>@EnableConfigServer</b> annotation in the configserver application startup class.</li>
<li>If you want to run it on Docker, you can install it with the <b>docker pull muhammedinan/config-server-image</b> command from <a href="https://hub.docker.com/r/muhammedinan/config-server-image">https://hub.docker.com/r/muhammedinan/config-server-image</a></li>
<li>You can run it as a container on docker with the command <b>docker run -d -p 8888:8888  muhammedinan/config-server-image</b> </li>
</ul>


# Discovery Server

<li>By registering our microservice applications to the eureka server, we can communicate with each other from a single point. Thanks to this registration process, the eureka discovery server also undertakes the load balancer task. After the projects are up, our microservices, defined as eureka clients, are connected to the eureka server, and in this way, we monitor and manage features such as network communication and load balancer of our applications from the center.</li>

<li>For installation, we need to install the <b>spring-cloud-starter-netflix-eureka-server</b> package in the <b>pom.xml</b></li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/discoveryserverpom.png" width="auto">

<li>In order to run discovery server in development and production environments, we need to configure the config server.</li>

<li>We need to add the <b>spring cloud config client</b> package to the <b>pom.xml</b> file</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/discoveryserverpom1.png" width="auto">

<li><b>application.yml</b> file configuration</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/discoveryapplication.png" width="auto">

<li>For the eureka server to work, you need to write the <b>@EnableEurekaServer</b> annotation in the discoveryserver application startup class.</li>
<li>Discovery Server Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/eureka-server-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/eureka-server-dev.yml</a></li>
<li>Discovery Server Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/eureka-server-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/eureka-server-prod.yml</a></li>

<li>If you want to run it on Docker, you can install it with the <b>docker pull muhammedinan/discovery-server-image</b> command from <a href="https://hub.docker.com/r/muhammedinan/discovery-server-image">https://hub.docker.com/r/muhammedinan/discovery-server-image </a></li>
<li>You can run it as a container on docker with the command <b>docker run -d -p 9001:9001 muhammedinan/discovery-server-image</b> </li>

# Api Gateway

<li>The main function of API Gateway is to receive the request from the client and forward it to the appropriate service.</li>

<li>We need to install <b>spring-cloud-starter-gateway package</b> in api gateway <b>pom.xml</b></li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apigatewaypom1.png" width="auto">

<li>We need to add the <b>spring cloud config client</b> package to the pom.xml file</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apigatewaypom2.png" width="auto">

<li>For eureka server connection we need to install <b>spring-cloud-starter-netflix-eureka-client</b> package</li>
  
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apigatewaypom3.png" width="auto">

<li>We need to install the <b>spring-sleuth-zipkin</b> package to develop the logs on a multi-service system.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apizipkin.png" width="auto">

<li>To monitor our applications, we need to install the <b>micrometer-registry-prometheus</b> package.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apiprometheus.png" width="auto">

<li>To the apigateway application class for eureka server connection We need to add <b>@EnableDiscoveryClient</b> annotation</li>

<li>We need to add the <b>spring cloud config client</b> package to the <b>pom.xml</b> file</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apiconfig.png" width="auto">

<li>Api Gateway Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/api-gateway-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/api-gateway-dev.yml</a></li>
<li>Api Gateway Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/api-gateway-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/api-gateway-prod.yml</a></li>

<li>If you want to run it on Docker, you can install it with the <b>docker pull muhammedinan/api-gateway-image</b> command from <a href="https://hub.docker.com/r/muhammedinan/api-gateway-image">https://hub.docker.com/r/muhammedinan/api-gateway-image </a></li>
<li>You can run it as a container on docker with the command <b>docker run -d -p 9011:9011 muhammedinan/api-gateway-image</b> </li>


# Invertory Service

<li>Brand, Model, Car we add service.</li>
<li>There is a <b>@ManyToOne</b> relationship between Model and Brand. There is a <b>@OneToMany</b> relationship between Brand and Model.</li>
<li>There is <b>@ManyToOne</b> relationship between Car and Model. There is a <b>@OneToMany</b> relationship between Model and Car.</li>
<li>we are using <b>JpaRepository</b> for data access.</li>
<li>I applied the <b>request response design pattern</b> in this service.</li>
<li>I wrote the global error exception method in this service</li>
<li>I used modelmapper infrastructure in this service</li>
<li>I established asynchronous communication with rentacar service in this service. I used kafka for this.</li>
<li>I have established asynchronous communication between this service and the filterservice that I will be filtering.</li>

<li>To the invertoryservice application class for eureka server connection We need to add <b>@EnableDiscoveryClient</b> annotation</li>
<li>To use global exceptino methods, we add <b>@RestControllerAdvice</b> annotation to the invertoryservice startup class</li>

<li>We create configuration class for kafka usage</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertorykafkaconf.png" width="auto">
<br/>
<li>We need to add the <b>spring cloud config client</b> package to the pom.xml file</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertoryconfig.png" width="auto">
<br/>
<li>To communicate with eureka server, it is necessary to install the <b>spring-cloud-starter-netflix-eureka-client</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertoryeureka.png" width="auto">
<br/>
<li>To use jparepository we need to install <b>spring-boot-starter-data-jpa</b> package</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertoryjpa.png" width="auto">
<br/>
<li>For the message queue system, we need to install the <b>spring-kafka</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertorykafka.png" width="auto">
<br/>
<li>we need to install <b>lombok and postgresql</b> packages</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertorylombokpostgre.png" width="auto">
<li>To monitor our applications, we need to install the <b>micrometer-registry-prometheus</b> package.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apiprometheus.png" width="auto">

<li>we install the <b>spring-boot-starter-validaton</b> package to perform validation operations.</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertoryvalidation.png" width="auto">

<li>we install spring-boot-starter-web package to develop web application</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertoryweb.png" width="auto">

<li>Invertory Service Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/invertory-service-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/invertory-service-dev.yml</a></li>
<li>Invertory Service Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/invertory-service-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/invertory-service-prod.yml</a></li>

<li>If you want to run it on Docker, you can install it with the <b>docker pull muhammedinan/invertory-service-api</b> command from <a href="https://hub.docker.com/r/muhammedinan/invertory-service-api">https://hub.docker.com/r/muhammedinan/invertory-service-api</a></li>
<li>You can run it as a container on docker with the command <b>docker run -d -p muhammedinan/invertory-service-api</b> </li>

# Filter Service
<li>It is the service used to filter information about the car faster.</li>
<li>we use mongorepository for data access</li>
<li>I used modelmapper infrastructure in this service</li>
<li>I used kafka, a message queue system, to consume requests from the inventoryservice service.</li>
<li>To the filterservice application class for eureka server connection We need to add <b>@EnableDiscoveryClient</b> annotation</li>


<li>We need to add the <b>spring cloud config client</b> package to the pom.xml file</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertoryconfig.png" width="auto">
<br/>
<li>To communicate with eureka server, it is necessary to install the <b>spring-cloud-starter-netflix-eureka-client</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertoryeureka.png" width="auto">
<li>For the message queue system, we need to install the <b>spring-kafka</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertorykafka.png" width="auto">
<li>To monitor our applications, we need to install the <b>micrometer-registry-prometheus</b> package.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/apiprometheus.png" width="auto">
<li>I used mongodb as database. for this we need to install <b>spring-boot-starter-data-mongodb</b> package</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/filtermongo.png" width="auto">

<li>Filter Service Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/filter-service-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/filter-service-dev.yml</a></li>
<li>Filter Service Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/filter-service-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/filter-service-prod.yml</a></li>

<li>If you want to run it on Docker, you can install it with the <b>docker pull muhammedinan/filter-service-api</b> command from <a href="https://hub.docker.com/r/muhammedinan/filter-service-api">https://hub.docker.com/r/muhammedinan/filter-service-api</a></li>
<li>You can run it as a container on docker with the command <b>docker run -d -p muhammedinan/filter-service-api</b> </li>
