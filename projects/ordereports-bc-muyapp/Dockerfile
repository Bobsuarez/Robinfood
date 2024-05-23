FROM 853324821382.dkr.ecr.us-east-1.amazonaws.com/arm64:21.0.2_13-jre-jammy AS jar

RUN apt-get update && apt-get install -y curl && apt-get clean && rm -rf /var/lib/apt/lists/* /var/cache/apt/archives/*

RUN addgroup --system spring && adduser --system spring --ingroup spring

USER spring:spring

COPY --chown=spring ./target/*.jar ./ordereports-bc-muyapp.jar

ENTRYPOINT ["java", "-jar", "/ordereports-bc-muyapp.jar"]