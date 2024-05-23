<p align="center">
  <a href="https://www.robinfood.com/">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1JI5jo7tLEAFys1Zy5qAbFnByjY11v46zUqn3IO6xRh90dA675u1jz1Vg4QKaP8vzENs&usqp=CAU" width="192px" height="192px"/>
  </a>
</p>

<h1 align="center">Order-Creation-Queries</h1>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/RobinFood-%5E-blueviolet" alt="RobinFood"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Squad-CAI Kaizen-blue" alt="Squad CAI Kaizen"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Component-Orchestrator-orange" alt="Orchestrator"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Framework-Spring Boot-red" alt="Framework spring boot"/></a>
</p>

# Table of Contents

* [About the project](#about-the-project)
    * [Built with](#built-with)
* [starting](#starting)
    * [Run deploy dev](#run-deploy-dev)

# About the project
Process orchestrator in directing and coordinating the interactions according to the component

## Built with

* [Java](https://docs.oracle.com/en/java/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

# Run Deploy Dev

To get a working dev copy, follow these simple example steps.

## Usage

# Properties


| Id | Properties                                                                                              | Value Default                                 | Description                                                                                 |
|:---|---------------------------------------------------------------------------------------------------------|-----------------------------------------------|---------------------------------------------------------------------------------------------|
| 1  | ```/config/{app-channel}/server.port```                                                                 | 8080                                          | Component access port (Devops parameter group)                                              |
| 2  | ```/config/{app-channel}/server.error.include-stacktrace```                                             | never                                         | Determines whether to include the stack trace in server error responses.                    |
| 3  | ```/config/{app-channel}/server.error.include-message```                                                | always                                        | Determines whether to include the error message in server error responses.                  |
| 4  | ```/config/{app-channel}/jwt-token-prefix```                                                            | --Secret_National--                           | Token prefix (Devops parameter group)                                                       |
| 5  | ```/config/{app-channel}/jwt-token-secret```                                                            | --Secret_National--                           | Secret for the token encrypted (Devops parameter group)                                     |
| 6  | ```/config/{app-channel}/jwt-token-aud```                                                               | internal                                      | Token type                                                                                  |
| 7  | ```/config/{app-channel}/jwt-token-mod```                                                               | order_creation_queries                        | Name and type of component                                                                  |
| 8  | ```/config/{app-channel}/springdoc.api-docs.path```                                                     | /api-docs                                     | Api docs path                                                                               |
| 9  | ```/config/{app-channel}/springdoc.swagger-ui.path```                                                   | /swagger-ui.html                              | Swagger UI path                                                                             |
| 10 | ```/config/{app-channel}/springdoc.swagger-ui.operationsSorter```                                       | method                                        | Swagger ordering type                                                                       |
| 11 | ```/config/{app-channel}/springdoc.packagesToScan```                                                    | com.robinfood.app.controllers                 | Displayed package on Swagger                                                                |
| 12 | ```/config/{app-channel}/url.orderBc```                                                                 | https://dev.order-bc.muydev.com/api/          | URL of the business component                                                               |
| 13 | ```/config/{app-channel}/url.MenuBc```                                                                  | https://steamstable-menu-bc.rf-dev.com/api/   | BC Menu component URL                                                                       |
| 14 | ```/config/{app-channel}/url.Loyalty```                                                                 | https://dev.loyalty.muydev.com/api/           | URL of the loyalty component                                                                |
| 15 | ```/config/{app-channel}/url.configurationBc```                                                         | https://dev.configurations-bc.muydev.com/api/ | URL of the main configuration component                                                     |
| 16 | ```/config/{app-channel}/url.billingBc```                                                               | https://dev.billing-bc.muydev.com/api/        | URL of the billing configuration component                                                  |
| 17 | ```/config/{app-channel}/url.serviceBc```                                                               | https://dev.services-bc.muydev.com/api/       | URL of the services-bc component                                                            |
| 18 | ```/config/{app-channel}/url.orderBcQueries```                                                          | https://dev.order-bc-query.muydev.com/api/    | URL of the order-bc-queries component                                                       |
| 19 | ```/config/{app-channel}/url.lambdaExchangesBc```                                                       | https://lambdaexchanges-bc.muydev.com/api/    | URL of the lambdaexchanges-bc component                                                     |
| 20 | ```/config/{app-channel}/url.userBc```                                                                  | https://dev.users-bc.muydev.com/api/          | URL of the user-bc component                                                                |
| 21 | ```/config/{app-channel}/spring.application.name ```                                                    | orderCreationQueries                          | Component name                                                                              |
| 23 | ```/config/{app-channel}/spring.redis.host```                                                           | --Secret_National--                           | Redis host for Spring. (DevOps parameter group)                                             |
| 24 | ```/config/{app-channel}/spring.redis.port```                                                           | --Secret_National--                           | Redis port for Spring.                                                                      |
| 25 | ```/config/{app-channel}/token-to-business-capability.auth-secret```                                    | --Secret_National--                           | Secret of authentication for the business capacity token. (DevOps parameter group)          |
| 26 | ```/config/{app-channel}/token-to-business-capability.redis-id```                                       | service-token                                 | Redis ID for the business capacity token.                                                   |
| 27 | ```/config/{app-channel}/token-to-business-capability.issuer```                                         | backoffice.v1                                 | Business capacity token issuer.                                                             |
| 28 | ```/config/{app-channel}/token-to-business-capability.auth-key```                                       | --Secret_National--                           | Authentication key for business capacity token. (DevOps parameter group)                    |
| 29 | ```/config/{app-channel}/logging.level.com.amazonaws.util.EC2MetadataUtils```                           | error                                         | Sets the logging level for the EC2MetadataUtils class in the AWS SDK.                       |
| 30 | ```/config/{app-channel}/logging.level.com.amazonaws.internal.InstanceMetadataServiceResourceFetcher``` | error                                         | Sets the logging level for the InstanceMetadataServiceResourceFetcher class in the AWS SDK. |
| 31 | ```/config/{app-channel}/spring.cloud.compatibility-verifier.enabled```                                 | false                                         | Compatibility between the versions of the Spring Cloud dependencies                         |
| 32 | ```/config/{app-channel}/spring.mvc.pathmatch.matching-strategy```                                      | ANT_PATH_MATCHER                              | Configuration related to the route coincidence strategy                                     |
| 33 | ```/config/{app-channel}/elastic.apm.enabled```                                                         | false                                         | Elastic APM authorization indicator.                                                        |
| 34 | ```/config/{app-channel}/elastic.apm.server-url```                                                      | --Secret_National--                           | Elastic server url (Devops parameter group)                                                 |
| 35 | ```/config/{app-channel}/elastic.apm.service-name```                                                    | order-creation-queries                        | Name of the Elastic Apm service.                                                            |
| 36 | ```/config/{app-channel}/elastic.apm.secret-token```                                                    | --Secret_National--                           | Elastic Secret Token (Devops parameter group)                                               |
| 37 | ```/config/{app-channel}/elastic.apm.environment```                                                     | dev                                           | Elastic APM environment.                                                                    |
| 38 | ```/config/{app-channel}/elastic.apm.application-packages```                                            | com.robinfood.order-creation-queries          | Application packages for elastic APM.                                                       |
| 39 | ```/config/{app-channel}/elastic.apm.log-level```                                                       | INFO                                          | Elastic APM registration level.                                                             |


# Dependencies

| Kind              | name        | Version | Description                              | URL                                                                        |
|:------------------|-------------|---------|------------------------------------------|----------------------------------------------------------------------------|
| Cache             | Redis       | V7.0.5  | Messaging system based on a JMS protocol |                                                                            |
| Broker            | Elastic APM |         |                                          | https://c2d4e1292b6c4676afaa34a2fa9d0354.apm.us-east-1.aws.cloud.es.io:443 |


---
[RobinFood](https://bitbucket.org/muytech/) with ❤️