FROM maven:3.6.3-jdk-11-slim AS compile

ARG HOME_DIR="/configurations_bc"

WORKDIR $HOME_DIR

# Copy the Project Object Model file
COPY ./pom.xml ./pom.xml

# Fetch all dependencies
RUN mvn dependency:go-offline -B

# Copy the source files
COPY ./src ./src

# Build for release
RUN mvn package -Dmaven.test.skip=true && cp target/*.jar configurations-bc.jar

FROM openjdk:11-jre-slim AS jar

RUN addgroup --system spring && adduser --system spring --ingroup spring

USER spring:spring

ARG HOME_DIR="/configurations_bc"

COPY --chown=spring --from=compile $HOME_DIR ./

ENTRYPOINT ["java","-jar","/configurations-bc.jar"]
