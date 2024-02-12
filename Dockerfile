FROM openjdk:21

WORKDIR /app

COPY target/clientes-1.0.jar .

CMD ["java", "-jar", "clientes-1.0.jar"]

