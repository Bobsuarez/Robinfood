<p align="center">
  <a href="https://www.robinfood.com/">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1JI5jo7tLEAFys1Zy5qAbFnByjY11v46zUqn3IO6xRh90dA675u1jz1Vg4QKaP8vzENs&usqp=CAU" width="192px" height="192px"/>
  </a>
</p>

<h1 align="center">Local server cloud adapter</h1>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/RobinFood-%5E-blueviolet" alt="RobinFood"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Squad-CAI-blue" alt="Squad Cai"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Lambda-AWS-orange" alt="Lambda AWS"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Framework-serverless-red" alt="Framework serverless"/></a>
</p>

# Table of Contents

* [About the project](#about-the-project)
  * [Built with](#built-with)
* [Empezando](#starting)
  * [Run local](#run-local)
  * [Run deploy dev](#run-deploy-dev)
  * [Run Communicate websocket](#run-Communicate-websocket)

# About the project
Adaptador: Administrará todas las comunicaciones a servicios externos desde el canal del servidor local.

## Built with

* [Node Js](https://nodejs.org/en/docs/)
* [Serverless](https://www.serverless.com/framework/docs)
* [Dynamo](https://docs.aws.amazon.com/dynamodb/index.html)

# Run local

To get a working copy on your personal Aws account, follow these simple step.

```console
serverless deploy
```

# Run deploy dev

To get a working dev copy, follow these simple example steps.

### Installation

### Run Communicate websocket
 Instalar [wscat](https://github.com/websockets/wscat) Communicate over websocket

```console
npm install -g wscat
```

Verificar que se instalo correctamente
```console
wscat --version
```

Conunciacion con el websocket
```console
wscat -c "URL_API_WEBSOCKET?posId=2&storeId=2"
```

---
[RobinFood](https://bitbucket.org/muytech/) with ❤️