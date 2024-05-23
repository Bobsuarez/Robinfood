FROM adoptopenjdk/openjdk11:armv7l-ubuntu-jdk-11.0.12_7 AS jar

RUN addgroup --system spring && adduser --system spring --ingroup spring

USER spring:spring

RUN mkdir -p /tmp/logs && chown spring:spring /tmp/logs

COPY --chown=spring ./target/*.jar ./changestatus-or.jar

VOLUME /tmp/logs

ENTRYPOINT ["java","-jar","/changestatus-or.jar"]