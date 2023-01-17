# RentACarMicroservices
Car Rental Project with Microservice Architecture

# Config Server

<li>It is the intended approach for microservices to migrate to development and production environments</li>
<li>For installation, we need to install the <b>spring cloud config server package</b> in the <b>pom.xml</b> file</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/configserverpom.png" width="auto">

<li><b>application.yml</b> file configuration</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/configserverapplication.png" width="auto">

# Discovery Server

<li>By registering our microservice applications to the eureka server, we can communicate with each other from a single point. Thanks to this registration process, the eureka discovery server also undertakes the load balancer task. After the projects are up, our microservices, defined as eureka clients, are connected to the eureka server, and in this way, we monitor and manage features such as network communication and load balancer of our applications from the center.</li>

<li>For installation, we need to install the <b>spring-cloud-starter-netflix-eureka-server</b> package in the <b>pom.xml</b></li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/discoveryserverpom.png" width="auto">

<li>In order to run discovery server in development and production environments, we need to configure the config server.</li>

<li>We need to add the <b>spring cloud config client</b> package to the <b>pom.xml</b> file</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/discoveryserverpom1.png" width="auto">

<li><b>application.yml</b> file configuration</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/discoveryapplication.png" width="auto">

<li>For the eureka server to work, you need to write the <b>@EnableEurekaServer</b> annotation in the discoveryserver application startup class.</li>
<li>Discovery Server Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/eureka-server-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/eureka-server-dev.yml</a></li>
<li>Discovery Server Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/eureka-server-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/eureka-server-prod.yml</a></li>


# Api Gateway

<li>The main function of API Gateway is to receive the request from the client and forward it to the appropriate service.</li>

<li>We need to install <b>spring-cloud-starter-gateway package</b> in api gateway <b>pom.xml</b></li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apigatewaypom1.png" width="auto">

<li>We need to add the <b>spring cloud config client</b> package to the pom.xml file</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apigatewaypom2.png" width="auto">

<li>For eureka server connection we need to install <b>spring-cloud-starter-netflix-eureka-client</b> package</li>
  
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apigatewaypom3.png" width="auto">

<li>We need to install the <b>spring-sleuth-zipkin</b> package to develop the logs on a multi-service system.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apizipkin.png" width="auto">

<li>To monitor our applications, we need to install the <b>micrometer-registry-prometheus</b> package.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apiprometheus.png" width="auto">

<li>To the apigateway application class for eureka server connection We need to add <b>@EnableDiscoveryClient</b> annotation</li>

<li>We need to add the <b>spring cloud config client</b> package to the <b>pom.xml</b> file</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apiconfig.png" width="auto">

<li>Api Gateway Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/api-gateway-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/api-gateway-dev.yml</a></li>
<li>Api Gateway Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/api-gateway-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/api-gateway-prod.yml</a></li>

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
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertorykafkaconf.png" width="auto">
<br/>
<li>We need to add the <b>spring cloud config client</b> package to the pom.xml file</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryconfig.png" width="auto">
<br/>
<li>To communicate with eureka server, it is necessary to install the <b>spring-cloud-starter-netflix-eureka-client</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryeureka.png" width="auto">
<br/>
<li>To use jparepository we need to install <b>spring-boot-starter-data-jpa</b> package</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryjpa.png" width="auto">
<br/>
<li>For the message queue system, we need to install the <b>spring-kafka</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertorykafka.png" width="auto">
<br/>
<li>we need to install <b>lombok and postgresql</b> packages</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertorylombokpostgre.png" width="auto">
<li>To monitor our applications, we need to install the <b>micrometer-registry-prometheus</b> package.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apiprometheus.png" width="auto">

<li>we install the <b>spring-boot-starter-validaton</b> package to perform validation operations.</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryvalidation.png" width="auto">

<li>we install spring-boot-starter-web package to develop web application</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryweb.png" width="auto">

<li>Invertory Service Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/invertory-service-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/invertory-service-dev.yml</a></li>
<li>Invertory Service Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/invertory-service-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/invertory-service-prod.yml</a></li>


# Filter Service
<li>It is the service used to filter information about the car faster.</li>
<li>we use mongorepository for data access</li>
<li>I used modelmapper infrastructure in this service</li>
<li>I used kafka, a message queue system, to consume requests from the inventoryservice service.</li>
<li>To the filterservice application class for eureka server connection We need to add <b>@EnableDiscoveryClient</b> annotation</li>


<li>We need to add the <b>spring cloud config client</b> package to the pom.xml file</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryconfig.png" width="auto">
<br/>
<li>To communicate with eureka server, it is necessary to install the <b>spring-cloud-starter-netflix-eureka-client</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryeureka.png" width="auto">
<li>For the message queue system, we need to install the <b>spring-kafka</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertorykafka.png" width="auto">
<li>To monitor our applications, we need to install the <b>micrometer-registry-prometheus</b> package.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apiprometheus.png" width="auto">
<li>I used mongodb as database. for this we need to install <b>spring-boot-starter-data-mongodb</b> package</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/filtermongo.png" width="auto">

<li>Filter Service Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/filter-service-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/filter-service-dev.yml</a></li>
<li>Filter Service Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/filter-service-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/filter-service-prod.yml</a></li>

# Payment Service
<li>It is the service where payment transactions are made.</li>
<li>adapter design pattern used</li>
<li>we are using <b>JpaRepository</b> for data access.</li>
<li>I applied the <b>request response design pattern</b> in this service.</li>
<li>I wrote the global error exception method in this service</li>
<li>I used modelmapper infrastructure in this service</li>

<li>To the paymentservice application class for eureka server connection We need to add <b>@EnableDiscoveryClient</b> annotation</li>
<li>To use global exceptino methods, we add <b>@RestControllerAdvice</b> annotation to the paymentservice startup class</li>

<li>We need to add the <b>spring cloud config client</b> package to the pom.xml file</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryconfig.png" width="auto">
<br/>
<li>To communicate with eureka server, it is necessary to install the <b>spring-cloud-starter-netflix-eureka-client</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryeureka.png" width="auto">
<br/>
<li>To use jparepository we need to install <b>spring-boot-starter-data-jpa</b> package</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryjpa.png" width="auto">
<li>we need to install <b>lombok and postgresql</b> packages</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertorylombokpostgre.png" width="auto">
<li>To monitor our applications, we need to install the <b>micrometer-registry-prometheus</b> package.</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apiprometheus.png" width="auto">
<li>we install the <b>spring-boot-starter-validaton</b> package to perform validation operations.</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryvalidation.png" width="auto">
<li>we install spring-boot-starter-web package to develop web application</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryweb.png" width="auto">

<li>Payment Service Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/payment-service-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/payment-service-dev.yml</a></li>
<li>Payment Service Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/payment-service-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/payment-service-prod.yml</a></li>

# Rental Service 
<li>It is the service where rental transactions are made.</li>
<li>We communicate synchronously with the invertoryservice. We use the openfeign client infrastructure of this</li>
<li>We communicate synchronously with the paymentservice. We use the openfeign client infrastructure of this</li>
<li>We use kafka, the message queue system, for asynchronous communication with the inventoryservice.</li>
<li>We use kafka, the message queue system, for asynchronous communication with the invoiceservice.</li>
<li>I applied the <b>request response design pattern</b> in this service.</li>
<li>I wrote the global error exception method in this service</li>
<li>I used modelmapper infrastructure in this service</li>
<li>To the rentalservice application class for eureka server connection We need to add <b>@EnableDiscoveryClient</b> annotation</li>
<li>To use global exceptino methods, we add <b>@RestControllerAdvice</b> annotation to the rentalservice startup class</li>

<li>We create configuration class for kafka usage</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertorykafkaconf.png" width="auto">
<br/>
<li>We need to add the <b>spring cloud config client</b> package to the pom.xml file</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryconfig.png" width="auto">
<br/>
<li>To communicate with eureka server, it is necessary to install the <b>spring-cloud-starter-netflix-eureka-client</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryeureka.png" width="auto">
<br/>
<li>To use jparepository we need to install <b>spring-boot-starter-data-jpa</b> package</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryjpa.png" width="auto">
<br/>
<li>For the message queue system, we need to install the <b>spring-kafka</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/master/microservicesimages/invertorykafka.png" width="auto">
<br/>
<li>we need to install <b>lombok and postgresql</b> packages</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertorylombokpostgre.png" width="auto">
<li>To monitor our applications, we need to install the <b>micrometer-registry-prometheus</b> package.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apiprometheus.png" width="auto">

<li>we install the <b>spring-boot-starter-validaton</b> package to perform validation operations.</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryvalidation.png" width="auto">

<li>we install spring-boot-starter-web package to develop web application</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryweb.png" width="auto">

<li>Rental Service Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/rental-service-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/rental-service-dev.yml</a></li>
<li>Rental Service Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/rental-service-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/rental-service-prod.yml</a></li>

# Invoice Service
<li>It is a service with invoice transactions.</li>
<li>We communicate synchronously with the invertoryservice. We use the openfeign client infrastructure of this</li>
<li>I applied the <b>request response design pattern</b> in this service.</li>
<li>I wrote the global error exception method in this service</li>
<li>I used modelmapper infrastructure in this service</li>
<li>To the invoiceservice application class for eureka server connection We need to add <b>@EnableDiscoveryClient</b> annotation</li>
<li>To use global exceptino methods, we add <b>@RestControllerAdvice</b> annotation to the invoiceservice startup class</li>
<li>kafka, which is a message queue system, was used for the consumption transactions from the rental service.</li>
<li>We need to add the <b>spring cloud config client</b> package to the pom.xml file</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryconfig.png" width="auto">
<br/>
<li>To communicate with eureka server, it is necessary to install the <b>spring-cloud-starter-netflix-eureka-client</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryeureka.png" width="auto">
<br/>
<li>To use jparepository we need to install <b>spring-boot-starter-data-jpa</b> package</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryjpa.png" width="auto">
<br/>
<li>For the message queue system, we need to install the <b>spring-kafka</b> package.</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertorykafka.png" width="auto">
<br/>
<li>we need to install <b>lombok and postgresql</b> packages</li>
<br/>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertorylombokpostgre.png" width="auto">
<li>To monitor our applications, we need to install the <b>micrometer-registry-prometheus</b> package.</li>

<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/apiprometheus.png" width="auto">
<li>we install the <b>spring-boot-starter-validaton</b> package to perform validation operations.</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryvalidation.png" width="auto">

<li>we install spring-boot-starter-web package to develop web application</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/invertoryweb.png" width="auto">

<li>Invoice Service Development application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/invoice-service-dev.yml">https://github.com/mhmmedinan/configServer/blob/master/invoice-service-dev.yml</a></li>
<li>Invoice Service Production application.yml <a href="https://github.com/mhmmedinan/configServer/blob/master/invoice-service-prod.yml">https://github.com/mhmmedinan/configServer/blob/master/invoice-service-prod.yml</a></li>

# Common Package
<li>It is a package in which common operations are used in Microservice architecture.
It contains operations such as caching, logging, exceptions, mapping and we use these operations within services.</li>
<li>I extracted this package from the microservice architecture package and uploaded it to the maven repository.
We can install and use the pom.xml file of the related services</li>
<li>You can install your pom.xml file according to the template you want from the address. <a href="https://mvnrepository.com/artifact/io.github.mhmmedinan/common/1.2.8">https://mvnrepository.com/artifact/io.github.mhmmedinan/common/1.2.8</a></li>
<li>You can install it by writing the package name in your pom.xml file like this</li>
<img src="https://github.com/mhmmedinan/rentACarMicroservices/blob/microservicesdev/microservicesimages/common.png" width="auto">

