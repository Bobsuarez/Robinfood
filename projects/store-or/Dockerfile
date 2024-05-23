FROM gradle:6.8-jdk11 AS compile

ARG HOME_DIR="/store-or"
COPY . $HOME_DIR

RUN cd $HOME_DIR && chmod +x $HOME_DIR/gradlew && $HOME_DIR/gradlew bootJar

RUN cp $HOME_DIR/build/libs/storeOr*.jar $HOME_DIR/storeOr.jar

FROM adoptopenjdk/openjdk11-openj9 AS jar
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring
RUN mkdir -p /tmp/logs && chown spring:spring /tmp/logs

ARG HOME_DIR="/store-or"
COPY --chown=spring --from=compile $HOME_DIR/storeOr.jar .
VOLUME /tmp/logs

ENTRYPOINT ["java", "-jar", "/storeOr.jar"]