
<p align="center">
  <a href="https://www.robinfood.com/">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1JI5jo7tLEAFys1Zy5qAbFnByjY11v46zUqn3IO6xRh90dA675u1jz1Vg4QKaP8vzENs&usqp=CAU" width="192px" height="192px"/>
  </a>
</p>

<h1 align="center">Order-Creation</h1>

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

| Id | Properties                                                                   | value Default                                                                    | Description                                                                        |
|:---|------------------------------------------------------------------------------|----------------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| 1  | ```/config/{app-channel}/server.port```                                      | --Secret_National--                                                              | Component access port (DevOps parameter group)                                     |
| 2  | ```/config/{app-channel}/spring.application.name```                          | order-creation                                                                   | Component name                                                                     |
| 3  | ```/config/{app-channel}/spring.cloud.compatibility-verifier.enabled```      | false                                                                            | Compatibility between the versions of the Spring Cloud dependencies                |
| 4  | ```/config/{app-channel}/spring.mvc.pathmatch.matching-strategy```           | ANT_PATH_MATCHER                                                                 | Configuration related to the route coincidence strategy                            |
| 5  | ```/config/{app-channel}/jwt-token-prefix```                                 | --Secret_National--                                                              | Token prefix (Devops parameter group)                                              |
| 6  | ```/config/{app-channel}/jwt-token-secret```                                 | --Secret_National--                                                              | Secret for the token encrypted (Devops parameter group)                            |
| 7  | ```/config/{app-channel}/jwt-token-aud```                                    | internal                                                                         | Token type                                                                         |
| 8  | ```/config/{app-channel}/jwt-token-mod```                                    | orch_order                                                                       | Name and type of component                                                         |
| 9  | ```/config/{app-channel}/springdoc.api-docs.path```                          | /api-docs                                                                        | Api docs path                                                                      |
| 10 | ```/config/{app-channel}/springdoc.swagger-ui.path```                        | /swagger-ui.html                                                                 | Swagger UI path                                                                    |
| 11 | ```/config/{app-channel}/springdoc.swagger-ui.operationsSorter```            | method                                                                           | Swagger ordering type                                                              |
| 12 | ```/config/{app-channel}/springdoc.packagesToScan```                         | com.robinfood.app.controllers                                                    | Displayed package on Swagger                                                       |
| 13 | ```/config/{app-channel}/url.configurations-bc```                            | https://dev.configurations-bc.muydev.com/api/                                    | URL of the main configuration component                                            |
| 14 | ```/config/{app-channel}/url.order-bc```                                     | https://dev.order-bc.muydev.com/api/                                             | URL of the business component                                                      |
| 15 | ```/config/{app-channel}/url.loyalty```                                      | https://dev.loyalty.muydev.com/api/                                              | URL of the loyalty component                                                       |
| 16 | ```/config/{app-channel}/url.services-bc```                                  | https://dev.services-bc.muydev.com/api/                                          | URL of the Services-BC component                                                   |
| 17 | ```/config/{app-channel}/url.users-bc```                                     | https://dev.users-bc.muydev.com/api/                                             | User component URL                                                                 |
| 18 | ```/config/{app-channel}/url.payment-methods-bc```                           | https://dev.paymentmethods-bc.muydev.com/api/                                    | Payment component URL                                                              |
| 19 | ```/config/{app-channel}/url.coupon-system```                                | https://dev.makeitrain.muydev.com/api/                                           | URL of the coupons component                                                       |
| 20 | ```/config/{app-channel}/url.menu-base-admin-bc```                           | https://steamstable-menu-bc-baseadmin.rf-dev.com/api/                            | URL of the Base Menu Component                                                     |
| 21 | ```/config/{app-channel}/url.menu-base```                                    | https://steamstable-menu-bc-base.rf-dev.com/api/                                 | BASE MENU COMPONENT URL                                                            |
| 22 | ```/config/{app-channel}/url.menu-bc```                                      | https://steamstable-menu-bc.rf-dev.com/api/                                      | BC Menu component URL                                                              |
| 23 | ```/config/{app-channel}/url.store-configurations```                         | https://dev.store-configurations.muydev.com/api/                                 | URL of the store configuration component                                           |
| 24 | ```/config/{app-channel}/url.taxes-bc```                                     | https://dev.taxes-bc.muydev.com/api/                                             | Tax calculation component URL                                                      |
| 25 | ```/config/{app-channel}/url.co2-bc```                                       | https://dev.co2-bc.muydev.com/api/                                               | URL of the CO2 component                                                           |
| 26 | ```/config/{app-channel}/url.cubano-v2```                                    | https://dev.cubano.muydev.com/api/                                               | URL of the Cuban component version 2.0                                             |
| 27 | ```/config/{app-channel}/url.cubano```                                       | https://dev.cubano.muydev.com/api/                                               | URL of the Cuban component version 1.0                                             |
| 28 | ```/config/{app-channel}/url.order-syncdata-bc```                            | https://order-bc-syncdata.muydev.com/api/                                        | URL of data synchronization component                                              |
| 29 | ```/config/{app-channel}/url.token-to-business-capability```                 | https://sso.muydev.com/                                                          | URL of the security token component                                                |
| 30 | ```/config/{app-channel}/spring.mvc.allowed-origin-bcc```                    | *                                                                                | CORS HEAD SETTINGS                                                                 |
| 31 | ```/config/{app-channel}/activemq.paid-orders.topic```                       | active-mq-order-bc                                                               | ActiveMQ orders payment channel.                                                   |
| 32 | ```/config/{app-channel}/spring.activemq.in-memory```                        | false                                                                            | Activemq in spring memory.                                                         |
| 33 | ```/config/{app-channel}/activemq-orders.broker-url```                       | --Secret_National--                                                              | URL of the ActiveMQ orders. (DevOps parameter group)                               |
| 34 | ```/config/{app-channel}/activemq-orders.user```                             | --Secret_National--                                                              | ActiveMQ user for the orders system. (DevOps parameter group)                      |
| 35 | ```/config/{app-channel}/activemq-orders.password```                         | --Secret_National--                                                              | Component access port (DevOps parameter group)                                     |
| 36 | ```/config/{app-channel}/activemq-payment-methods.broker-url```              | --Secret_National--                                                              | Corridor URL for ActiveMQ payment methods.                                         |
| 37 | ```/config/{app-channel}/activemq-payment-methods.user```                    | --Secret_National--                                                              | ActiveMQ user for payment methods.                                                 |
| 38 | ```/config/{app-channel}/activemq-payment-methods.password```                | --Secret_National--                                                              | ActiveMQ password for payment methods.                                             |
| 39 | ```/config/{app-channel}/activemq.orders-to-created.topic```                 | ORDERS_TO_CREATED                                                                | ActiveMQ topic for created orders.                                                 |
| 40 | ```/config/{app-channel}/activemq.orders-response.topic```                   | ORDERS_RESPONSE                                                                  | ActiveMQ topic for orders responses.                                               |
| 41 | ```/config/{app-channel}/activemq.orders-change-status.queue```              | Rf.All.Order.OrdersChangeStatus.Queue                                            | ActiveMQ tail for orders status changes.                                           |
| 42 | ```/config/{app-channel}/activemq.orders-paymentmethods-transaction.queue``` | Rf.Co.Orders.TransactionStatusChanges.Queue                                      | ActiveMQ tail for transactions of orders payment methods.                          |
| 43 | ```/config/{app-channel}/activemq.orders-paymentmethods-refund.queue```      | Rf.All.PaymentMethods.Refunds.Queue                                              | Activemq tail for reimbursements of orders payment methods.                        |
| 44 | ```/config/{app-channel}/payment-method.online-col```                        | 1                                                                                | ID online payment method for Colombia.                                             |
| 45 | ```/config/{app-channel}/payment-method.online-mx```                         | 2                                                                                | ID online payment method for Mexico.                                               |
| 46 | ```/config/{app-channel}/payment-method.online-bra```                        | 3                                                                                | ID online payment method for Brazil.                                               |
| 47 | ```/config/{app-channel}/payment.method-foodcoins-id```                      | 7                                                                                | FoodCoins payment method identifier.                                               |
| 48 | ```/config/{app-channel}/payment.method-validation-service```                | 4,5,6,10,11,24,25                                                                | Validation ids for payment methods.                                                |
| 49 | ```/config/{app-channel}/orders.payment-method-ids-to-service```             | {4: 3, 6: 1, 23: 1, 24: 3, 25: 3, 5: 3,10:8, 11:8}                               | IDS of payment methods for orders service.                                         |
| 50 | ```/config/{app-channel}/orders.payment-method-ids```                        | {1: {4: 8, 6: 1, 23: 1, 5: 3, 11:8}, 5: {4: 3, 6: 1, 23: 1, 24: 3, 25: 3, 5: 3}} | IDS of payment methods for orders.                                                 |
| 51 | ```/config/{app-channel}/orders.payment-method-ids-to-service.col```         | {4: 8, 6: 1, 23: 1, 5: 3,10:8, 11:8}                                             | IDS of payment methods for orders service in Colombia.                             |
| 52 | ```/config/{app-channel}/orders.payment-method-ids-to-service.bra```        | {4: 3, 6: 1, 23: 1, 24: 3, 25: 3, 5: 3}                                          | IDS of payment methods for orders in Brazil.                                       |
| 53 | ```/config/{app-channel}/taxe-service-mex```                                 | 16                                                                               | ID for tax service in Mexico.                                                      |
| 54 | ```/config/{app-channel}/taxe-service-col```                                 | 8                                                                                | ID for tax service in Colombia.                                                    |
| 55 | ```/config/{app-channel}/payment-methods-ids-allowed-to-validate-detail```   | 4,5,6,9,10,11,12                                                                 | Detail of the IDS of payment methods allowed for validation.                       |
| 56 | ```/config/{app-channel}/co2.validation.is-enabled```                        | true                                                                             | CO2 validation qualification indicator.                                            |
| 57 | ```/config/{app-channel}/spring.redis.host```                                | --Secret_National--                                                              | Redis host for Spring. (DevOps parameter group)                                    |
| 58 | ```/config/{app-channel}/spring.redis.port```                                | 6379                                                                             | Redis port for Spring.                                                             |
| 59 | ```/config/{app-channel}/token-to-business-capability.redis-id```            | service-token                                                                    | Redis ID for the business capacity token.                                          |
| 60 | ```/config/{app-channel}/token-to-business-capability.issuer```              | backoffice.v1                                                                    | Business capacity token issuer.                                                    |
| 61 | ```/config/{app-channel}/token-to-business-capability.auth-key```            | --Secret_National--                                                              | Authentication key for business capacity token. (DevOps parameter group)           |
| 62 | ```/config/{app-channel}/token-to-business-capability.auth-secret```         | --Secret_National--                                                              | Secret of authentication for the business capacity token. (DevOps parameter group) |
| 63 | ```/config/{app-channel}/server.error.include-message```                     | always                                                                           | Message inclusion indicator in server errors.                                      |
| 64 | ```/config/{app-channel}/PROXY_REPOSITORY_AWS```                             | --Secret_National--                                                              | AWS proxy repository (DevOps parameter group)                                      |
| 65 | ```/config/{app-channel}/PROXY_REPOSITORY_AWS_USER```                        | --Secret_National--                                                              | AWS proxy repository user (Devops parameter group)                                 |
| 66 | ```/config/{app-channel}/PROXY_REPOSITORY_AWS_AUTH_TOKEN```                  | --Secret_National--                                                              | AUTHENTIC TOKEN FOR AWS Proxy repository (Devops parameter group)                  |
| 67 | ```/config/{app-channel}/thread.core-pool-size```                            | 5                                                                                | Size of the main thread group.                                                     |
| 68 | ```/config/{app-channel}/thread.max-pool-size```                             | 10                                                                               | Maximum size of the thread group.                                                  |
| 69 | ```/config/{app-channel}/thread.queue-capacity```                            | 1000                                                                             | Thread tail capacity.                                                              |
| 70 | ```/config/{app-channel}/elastic.apm.enabled```                              | true                                                                             | Elastic APM authorization indicator.                                               |
| 71 | ```/config/{app-channel}/elastic.apm.server-url```                           | --Secret_National--                                                              | Elastic server url (Devops parameter group)                                        |
| 72 | ```/config/{app-channel}/elastic.apm.service-name```                         | order-creation                                                                   | Name of the Elastic Apm service.                                                   |
| 73 | ```/config/{app-channel}/elastic.apm.secret-token```                         | --Secret_National--                                                              | Elastic Secret Token (Devops parameter group)                                      |
| 74 | ```/config/{app-channel}/elastic.apm.environment```                          | dev                                                                              | Elastic APM environment.                                                           |
| 75 | ```/config/{app-channel}/elastic.apm.application-packages```                 | com.robinfood.order-creation                                                     | Application packages for elastic APM.                                              |
| 76 | ```/config/{app-channel}/elastic.apm.log-level```                            | INFO                                                                             | Elastic APM registration level.                                                    |
| 77 | ```/config/{app-channel}/amazon.aws.accesskey```                             | --Secret_National--                                                              | AccessKey for AWS session (DevOps parameter group)                                 |
| 78 | ```/config/{app-channel}/amazon.aws.secretkey```                             | --Secret_National--                                                              | SecretKey for AWS session (DevOps parameter group)                                 |
| 79 | ```/config/{app-channel}/amazon.aws.region```                                | --Secret_National--                                                              | AWS session token (Devops parameter group)                                         |
| 80 | ```/config/{app-channel}/amazon.aws.dynamo.temporaryTransactionTableName```  | transaction-created-temporary                                                    | Temporary Table of Amazon Aws Dynamodb Transactions Table                          |
| 81 | ```/config/{app-channel}/orders.gift-card-payment-methods-ids```  | gift card payment method identifiers by country                                                    | Temporary Table of Amazon Aws Dynamodb Transactions Table                          |
| 82 | ```/config/{app-channel}/orders.gift-card-product-categories-ids```  | product categories identifiers gift card                                                    | Temporary Table of Amazon Aws Dynamodb Transactions Table                          |


# Dependencies

| Kind              | name        | Version | Description                              | URL                                                                        |
|:------------------|-------------|---------|------------------------------------------|----------------------------------------------------------------------------|
| Cache             | Redis       | V7.0.5  | Messaging system based on a JMS protocol |                                                                            |
| Messenger service | ActiveMQ    | lasted  | Memory data storage system               |                                                                            |
| Broker            | Elastic APM |         |                                          | https://c2d4e1292b6c4676afaa34a2fa9d0354.apm.us-east-1.aws.cloud.es.io:443 |


---
[RobinFood](https://bitbucket.org/muytech/) with ❤️