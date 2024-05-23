FROM maven:3.6.3-jdk-11-slim AS compile

ARG HOME_DIR="/paymentmethodsbc"

COPY . $HOME_DIR
RUN cd $HOME_DIR && mvn install -DskipTests && mvn package -DskipTests
RUN cp $HOME_DIR/target/paymentmethodsbc-*.jar $HOME_DIR/paymentmethodsbc.jar

FROM openjdk:11-jre-slim AS jar

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring
RUN mkdir -p /tmp/logs && chown spring:spring /tmp/logs

ARG HOME_DIR="/paymentmethodsbc"
COPY --chown=spring --from=compile $HOME_DIR/paymentmethodsbc.jar .
ENV TZ America/Bogota
VOLUME /tmp/logs

ENTRYPOINT ["java","-jar","/paymentmethodsbc.jar"]