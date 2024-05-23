<p align="center">
  <a href="https://www.robinfood.com/">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1JI5jo7tLEAFys1Zy5qAbFnByjY11v46zUqn3IO6xRh90dA675u1jz1Vg4QKaP8vzENs&usqp=CAU" width="192px" height="192px"/>
  </a>
</p>

<h1 align="center">Order-bc-query</h1>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/RobinFood-%5E-blueviolet" alt="RobinFood"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Squad-CAI Kaizen-blue" alt="Squad CAI Kaizen"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Component-Business Capacity-orange" alt="Business Capacity"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Framework-Spring Boot-red" alt="Framework spring boot"/></a>
</p>

# Table of Contents

* [About the project](#about-the-project)
    * [Built with](#built-with)
* [Empezando](#starting)
    * [Run deploy dev](#run-deploy-dev)

# About the project
Capacidad de negocio de lectura para componente en orden unificada.

## Built with

* [Java](https://docs.oracle.com/en/java/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)


# Run Deploy Dev

To get a working dev copy, follow these simple example steps.

## Usage

# Properties

| Id  | Propiedad                                                                        | Valor Default                             | Descripción                                                          |
|:----|----------------------------------------------------------------------------------|-------------------------------------------|----------------------------------------------------------------------|
| 1   | ``` /config/{app-channel}/server.port ```                                        | --secreto_nacional--                      | Puerto de acceso del componente (Grupo de Parámetro DevOps)          |
| 2   | ``` /config/{app-channel}/spring.application.name ```                            | orderBcQueries                            | Nombre del componente                                                |
| 3   | ``` /config/{app-channel}/jwt-token-prefix ```                                   | --secreto_nacional--                      | Prefijo del token  (Grupo de Parámetro DevOps)                       |
| 4   | ``` /config/{app-channel}/jwt.token.secret ```                                   | --secreto_nacional--                      | Secret para el encriptado del token (Grupo de Parámetro DevOps)      |
| 5   | ``` /config/{app-channel}/jwt-token-aud ```                                      | service                                   | tipo de token                                                        |
| 6   | ``` /config/{app-channel}/jwt-token-mod ```                                      | order_bc                                  | nombre y tipo de componente                                          |
| 7   | ``` /config/{app-channel}/springdoc.api-docs.path ```                            | /api-docs                                 | path de api docs                                                     |
| 8   | ``` /config/{app-channel}/springdoc.swagger-ui.path ```                          | /swagger-ui.html                          | path de swagger ui                                                   |
| 9   | ``` /config/{app-channel}/springdoc.swagger-ui.operationsSorter ```              | method                                    | tipo de ordenamiento de swagger                                      |
| 10  | ``` /config/{app-channel}/springdoc.packagesToScan ```                           | com.robinfood.app.controllers             | paquete visualizado en swagger                                       |
| 11  | ``` /config/{app-channel}/spring.datasource.initialization-mode ```              | never                                     | modo de inicialización de la base de datos                           |
| 12  | ``` /config/{app-channel}/spring.datasource.url ```                              | --secreto_nacional--                      | url de la base de datos (Grupo de Parámetro DevOps)                  |
| 13  | ``` /config/{app-channel}/zeroDateTimeBehavior ```                               | convertToNull&enabledTLSProtocols=TLSv1.2 | protocolo de tiempo con la base de datos                             |
| 14  | ``` /config/{app-channel}/spring.datasource.username ```                         | --secreto_nacional--                      | credencial usuario de la base de datos (Grupo de Parámetro DevOps)   |
| 15  | ``` /config/{app-channel}/spring.datasource.password ```                         | --secreto_nacional--                      | credencial contraseña de l base de datos (Grupo de Parámetro DevOps) |
| 16  | ``` /config/{app-channel}/spring.jpa.show-sql ```                                | true                                      | Flag activar hibernate congifuration (Grupo de Parámetro DevOps)     |
| 17  | ``` /config/{app-channel}/logging.level.org.hibernate.SQL ```                    | DEBUG                                     | Logging                                                              |
| 18  | ``` /config/{app-channel}/spring.jpa.database-platform ```                       | org.hibernate.dialect.MySQL5InnoDBDialect | Plataforma o tipo de base de datos usada                             |
| 19  | ``` /config/{app-channel}/spring.jpa.hibernate.use-new-id-generator-mappings ``` | false                                     | Flag para activar el mapping de los id                               |
| 20  | ``` /config/{app-channel}/spring.jpa.hibernate.ddl-auto ```                      | none                                      | Propiedad que especifica si un valor pasa a Hibernate                |
| 21  | ``` /config/{app-channel}/spring.jpa.hibernate.ddl ```                           | false                                     | Flag que nos permite especificar si un valor pasa a hibernate        |
| 22  | ``` /config/{app-channel}/product-images-url ```                                 | --secreto_nacional--                      | url del bucket para las imagenes (Grupo de Parámetro DevOps)         |
| 23  | ``` /config/{app-channel}/user.active-orders.diff-status ```                     | 6,9                                       | Id de los estado de la orden no activas para consultas               |
| 24  | ``` /config/{app-channel}/user.active-orders.origin ```                          | 2                                         | Id del origen (canal) de la orden                                    |
| 25  | ``` /config/{app-channel}/user.active-orders.hours ```                           | 4                                         | Hora minima de diferencia para consulta de una orden entre fechas    |
| 26  | ``` /config/{app-channel}/user.history-orders.diff-status ```                    | 9                                         | Id de los estado de la orden no activas para consultas               |
| 27  | ``` /config/{app-channel}/pos.orders.status-cancelled ```                        | 6,9                                       | Id de estado de orden canceladas                                     |
| 28  | ``` /config/{app-channel}/spring.cloud.compatibility-verifier.enabled ```        | false                                     | Flag que activa la verificación por compatibilidad                   |
| 29  | ``` /config/{app-channel}/spring.mvc.pathmatch.matching-strategy ```             | ANT_PATH_MATCHER                          | Estrategia de agrupación                                             |
| 30  | ``` /config/{app-channel}/elastic.apm.enabled ```                                | false                                     | Flag para activar elastic APM                                        |
| 31  | ``` /config/{app-channel}/elastic.apm.server-url```                              | --secreto_nacional--                      | Url del servidor de elastic (Grupo de Parámetro DevOps)              |
| 32  | ``` /config/{app-channel}/elastic.apm.service-name```                            | order-bc-query                            | Nombre del componente                                                |
| 33  | ``` /config/{app-channel}/elastic.apm.secret-token```                            | --secreto_nacional--                      | Secret Token de elastic (Grupo de Parámetro DevOps)                  |
| 34  | ``` /config/{app-channel}/elastic.apm.environment```                             | dev                                       | ambiente al que evalúa elastic                                       |
| 35  | ``` /config/{app-channel}/elastic.apm.application-packages```                    | com.robinfood.order-bc-query              | Paquete al que evalúa elastic                                        |
| 36  | ``` /config/{app-channel}/elastic.apm.log-level```                               | INFO                                      | Nivel del log en elastic                                             |
| 37  | ``` /config/{app-channel}/amazon.aws.accesskey```                                | --secreto_nacional--                      | Accesskey para sesión de AWS (Grupo de Parámetro DevOps)             |
| 38  | ``` /config/{app-channel}/amazon.aws.secretkey```                                | --secreto_nacional--                      | Secretkey para sesión de AWS (Grupo de Parámetro DevOps)             |
| 39  | ``` /config/{app-channel}/amazonAWSSessionToken```                               | --secreto_nacional--                      | Token de sesión de AWS (Grupo de Parámetro DevOps)                   |


# Dependencies

| Tipo    | Nombre       | Descripción   | Url                                                                        |
|:--------|--------------|---------------|----------------------------------------------------------------------------|
| BD      | BD-Orders    | Base de datos |                                                                            |
| Broker  | Elastic APM  |               | https://c2d4e1292b6c4676afaa34a2fa9d0354.apm.us-east-1.aws.cloud.es.io:443 |
|         |              |               |                                                                            |




---
[RobinFood](https://bitbucket.org/muytech/) with ❤️






