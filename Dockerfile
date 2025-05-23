FROM openjdk:17-jdk-slim 
COPY target/BackendClientes-0.0.1-SNAPSHOT.jar BackendClientes-0.0.1-SNAPSHOT.jar
EXPOSE 8080 3306
ENTRYPOINT [ "java" , "-jar" , "BackendClientes-0.0.1-SNAPSHOT.jar" ]