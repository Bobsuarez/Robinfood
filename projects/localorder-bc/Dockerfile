FROM openjdk:11-jdk-slim
COPY /build/libs/*.jar localorderbc.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/localorderbc.jar"]