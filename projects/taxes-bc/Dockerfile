FROM adoptopenjdk/openjdk11:armv7l-ubuntu-jdk-11.0.12_7 AS jar

RUN addgroup --system spring && adduser --system spring --ingroup spring

USER spring:spring

COPY --chown=spring ./target/*.jar ./taxes-bc.jar

ENTRYPOINT ["java","-jar","/taxes-bc.jar"]