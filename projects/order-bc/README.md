<p align="center">
  <a href="https://www.robinfood.com/">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1JI5jo7tLEAFys1Zy5qAbFnByjY11v46zUqn3IO6xRh90dA675u1jz1Vg4QKaP8vzENs&usqp=CAU" width="192px" height="192px"/>
  </a>
</p>

<h1 align="center">Order-BC</h1>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/RobinFood-%5E-blueviolet" alt="RobinFood"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Squad-CAI Kaizen-blue" alt="Squad CAI Kaizen"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Component-Business Capacity-orange" alt="Business Capacity"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Framework-Spring Boot-red" alt="Framework spring boot"/></a>
</p>

# Table of Contents

* [About the project](#about-the-project)
    * [Built with](#built-with)
* [starting](#starting)
    * [Run deploy dev](#run-deploy-dev)

# About the project
Read business capability for component in unified order.

## Built with

* [Java](https://docs.oracle.com/en/java/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

# Run Deploy Dev

To get a working dev copy, follow these simple example steps.

## Usage

# Properties

| Id | Propitiated                                                                    | Value Default                                | Description                                                           |
|:---|--------------------------------------------------------------------------------|----------------------------------------------|-----------------------------------------------------------------------|
| 1  | ```/config/{app-channel}/server.port```                                        | 8080                                         | Component access port (DevOps parameter group)                        |
| 2  | ```/config/{app-channel}/spring.application.name ```                           | orderBc                                      | Component name                                                        |
| 3  | ```/config/{app-channel}/jwt-token-prefix```                                   | --Secret_National--                          | Token prefix (Devops parameter group)                                 |
| 4  | ```/config/{app-channel}/jwt-token-secret```                                   | --Secret_National--                          | Secret for the token encrypted (Devops parameter group)               |
| 5  | ```/config/{app-channel}/jwt-token-aud```                                      | service                                      | Token type                                                            |
| 6  | ```/config/{app-channel}/jwt-token-mod```                                      | order_bc                                     | Name and type of component                                            |
| 7  | ```/config/{app-channel}/springdoc.api-docs.path```                            | /api-docs                                    | Api docs path                                                         |
| 8  | ```/config/{app-channel}/springdoc.swagger-ui.path```                          | /swagger-ui.html                             | Swagger UI path                                                       |
| 9  | ```/config/{app-channel}/springdoc.swagger-ui.operationsSorter```              | method                                       | Swagger ordering type                                                 |
| 10 | ```/config/{app-channel}/springdoc.packagesToScan```                           | com.robinfood.app.controllers                | Displayed package on Swagger                                          |
| 11 | ```/config/{app-channel}/spring.datasource.initialization-mode```              | never                                        | Database initialization mode                                          |
| 12 | ```/config/{app-channel}/spring.datasource.url```                              | --Secret_National--                          | URL of the database (Devops parameter group)                          |
| 13 | ```/config/{app-channel}/spring.datasource.username```                         | --Secret_National--                          | Credential User of the Database (DevOps parameter group)              |
| 14 | ```/config/{app-channel}/spring.datasource.password```                         | --Secret_National--                          | Credential Database password (DevOps parameter group)                 |
| 15 | ```/config/{app-channel}/spring.jpa.show-sql```                                | true                                         | Flag activate hibernate configuration (DevOps parameter group)        |
| 16 | ```/config/{app-channel}/logging.level.org.hibernate.SQL```                    | DEBUG                                        | Logging                                                               |
| 17 | ```/config/{app-channel}/spring.jpa.database-platform```                       | --Secret_National--                          | Used Database Platform or Type                                        |
| 18 | ```/config/{app-channel}/spring.jpa.hibernate.use-new-id-generator-mappings``` | false                                        | Flag to activate the ID mapping                                       |
| 19 | ```/config/{app-channel}/spring.jpa.hibernate.ddl-auto```                      | none                                         | Property that specifies whether a value passes to Hibernate           |
| 20 | ```/config/{app-channel}/spring.jpa.generate-ddl```                            | false                                        | Flag that allows us to specify if a value passes to Hibernate         |
| 21 | ```/config/{app-channel}/product-images-url```                                 | --Secret_National--                          | Bucket URL for images (Devops parameter group)                        |
| 23 | ```/config/{app-channel}/user.active-orders.diff-status```                     | 6,9                                          | ID of the order of the order not active for consultations             |
| 24 | ```/config/{app-channel}/user.active-orders.origin```                          | 2                                            | Origin ID (Channel) of the Order                                      |
| 25 | ```/config/{app-channel}/user.active-orders.hours```                           | 4                                            | Minimum time of difference for consultation of an order between dates |
| 26 | ```/config/{app-channel}/user.history-orders.diff-status```                    | 9                                            | ID of the order of the order not active for consultations             |
| 27 | ```/config/{app-channel}/spring.activemq.broker-url```                         | --Secret_National--                          | URL of the ActiveMQ corridor for Spring.                              |
| 28 | ```/config/{app-channel}/spring.activemq.in-memory```                          | false                                        | ActiveMQ in spring memory.                                            |
| 29 | ```/config/{app-channel}/spring.activemq.user```                               | --Secret_National--                          | ActiveMQ user for Spring.                                             |
| 30 | ```/config/{app-channel}/spring.activemq.password```                           | --Secret_National--                          | ActiveMQ password for Spring.                                         |
| 31 | ```/config/{app-channel}/activemq.orders-created-delay```                      | 200                                          | Delay in the creation of orders in ActiveMQ.                          |
| 32 | ```/config/{app-channel}/activemq.paid-orders.client_id```                     | order-bc                                     | Customer ID for orders paid in ActiveMQ.                              |
| 33 | ```/config/{app-channel}/activemq.orders-created.topic```                      | Rf.All.Order.OrderCreated.VirtualTopic       | ActiveMQ theme for created orders                                     |
| 34 | ```/config/{app-channel}/activemq.orders-status-changed.topic```               | Rf.All.Order.OrderStatusChanged.VirtualTopic | ActiveMQ issue for orders status changes.                             |
| 35 | ```/config/{app-channel}/activemq.orders-change-status.queue```                | Rf.All.Order.OrdersChangeStatus.Queue        | ActiveMQ tail for orders status changes.                              |
| 36 | ```/config/{app-channel}/orders.payment-method-ids```                          | 4,5,6,10,11,23,24,25                         | IDs of payment methods for orders                                     |
| 37 | ```/config/{app-channel}/spring.cloud.compatibility-verifier.enabled```        | false                                        | Compatibility between the versions of The Spring Cloud Dependencies   |
| 38 | ```/config/{app-channel}/spring.mvc.pathmatch.matching-strategy```             | ANT_PATH_MATCHER                             | Configuration related to the route coincidence strategy               |
| 39 | ```/config/{app-channel}/elastic.apm.enabled```                                | false                                        | Elastic apm authorization indicator                                   |
| 40 | ```/config/{app-channel}/elastic.apm.server-url```                             | --Secret_National--                          | Elastic Server URL (Devops Parameter Group)                           |
| 41 | ```/config/{app-channel}/elastic.apm.service-name```                           | order-bc                                     | Name of the elastic APM Service.                                      |
| 42 | ```/config/{app-channel}/elastic.apm.secret-token```                           | --Secret_National--                          | Elastic Secret Token (Devops Parameter Group)                         |
| 43 | ```/config/{app-channel}/elastic.apm.environment```                            | dev                                          | Elastic Apm Environment.                                              |
| 44 | ```/config/{app-channel}/elastic.apm.application-packages```                   | com.robinfood.order-bc                       | Application packs for elastic APM.                                    |
| 45 | ```/config/{app-channel}/elastic.apm.log-level```                              | INFO                                         | Elastic APM registration level.                                       |



# Dependencies

| Kind              | Name            | Description                | Url                                                                        |
|:------------------|-----------------|----------------------------|----------------------------------------------------------------------------|
| BD                | BD-Orders       | Data Base                  |                                                                            |
| Messenger service | ActiveMQ-lasted | Memory data storage system |                                                                            |
| Broker            | Elastic APM     |                            | https://c2d4e1292b6c4676afaa34a2fa9d0354.apm.us-east-1.aws.cloud.es.io:443 |

---
[RobinFood](https://bitbucket.org/muytech/) with ❤️