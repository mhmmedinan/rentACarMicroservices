FROM maven:3.8.3-openjdk-17
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests
EXPOSE 9001
CMD ["mvn","spring-boot:run"]