'use strict';

const AWS = require('aws-sdk');
const { API_VERSION, APIGATEWAY_ENDPOINT } = require('../../../constants/LambdaConstants');

const apigwManagementApi = new AWS.ApiGatewayManagementApi({
    apiVersion: API_VERSION,
    endpoint: APIGATEWAY_ENDPOINT
});

const sendMessage = async (params) => {

    console.info('sendMessage() params => ' + JSON.stringify(params));

    return await apigwManagementApi.postToConnection(params).promise();
};

module.exports = {
    sendMessage
};