<p align="center">
  <a href="https://www.robinfood.com/">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1JI5jo7tLEAFys1Zy5qAbFnByjY11v46zUqn3IO6xRh90dA675u1jz1Vg4QKaP8vzENs&usqp=CAU" width="192px" height="192px"/>
  </a>
</p>

<h1 align="center">Lambda Notification Manager OR</h1>

<p align="center">  
  <a href="#"><img src="https://img.shields.io/badge/RobinFood-%5E-blueviolet" alt="RobinFood"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Squad-CAI Kaizen-blue" alt="Squad CAI Kaizen"/></a>
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

Component in charge of listening to the state change events coming from different brokers.
This way, ensures that all channels are updated with the latest information.

## Built with

* [Node Js](https://nodejs.org/en/docs/)
* [Serverless](https://www.serverless.com/framework/docs)

# Run local

To get a working copy on your personal Aws account, follow these simple step.

```console
serverless deploy
```

# Run Deploy Dev

To get a working dev copy, follow these simple example steps.

## Usage

### subscriber-change-status

The `subscriber-change-status` function performs the following task:

- Listen for state changes made and replicate to subscribers.

---
[RobinFood](https://bitbucket.org/muytech/) with ❤️