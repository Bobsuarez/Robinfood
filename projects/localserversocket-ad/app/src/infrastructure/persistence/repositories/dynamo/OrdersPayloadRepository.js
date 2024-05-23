'use strict';

const AWS = require('aws-sdk');
const { ACTIVE, TABLE_NAME, INACTIVE } = require('../../../../../constants/LambdaConstants');
const dynamodb = new AWS.DynamoDB.DocumentClient();

const scanOrderPaylodsByStoreId = async (storeId) => {
    
    console.info('Execute scanOrderPaylodsByStoreId(): => ', storeId);

    let params = {
        TableName: TABLE_NAME,
        KeyConditionExpression: 'storeId = :storeId',
        ExpressionAttributeValues: {
            ':storeId': storeId.toString(),
        }
    };

    console.info('params scanOrderPaylodsByStoreId => ', JSON.stringify(params));
    
    return dynamodb.query(params).promise();
};

const scanOrderPaylodsByConectionId = async (connectionId) => {

    console.info('Execute scanOrderPaylodsByConectionId() => ' + JSON.stringify(connectionId));

    let params = {
        TableName: TABLE_NAME,
        IndexName: 'storeId',
        KeyConditionExpression: 'connectionId = :connectionId',
        ExpressionAttributeValues: {
            ':connectionId': connectionId,
        }
    };

    console.info('scanOrderPaylodsByConectionId() params => ' + JSON.stringify(params));

    return dynamodb.query(params, function(err, data ) {
        if (err) {
            console.error('Unable to read item. Error JSON:', JSON.stringify(err, null, 2));
        } else {
            console.info('GetItem succeeded:', JSON.stringify(data, null, 2));
        }
    } ).promise();

}

const putOrderPayload = async (ordersPayload) => {

    console.info('Execute putOrderPayload(): => ' + JSON.stringify(ordersPayload));

    const params = {
        TableName: TABLE_NAME,
        Item: {
            connectionId: ordersPayload.connectionId,
            storeId: ordersPayload.storeId,
            orders: ordersPayload.orders,
            status: ordersPayload.status,
        }
    };

    console.info('putOrderPayload() params => ' + JSON.stringify(params));

    return dynamodb.put(params).promise();
};

const updateOrderPayloadStatusByStoreId = async (storeId) => {

    console.info('Execute updateOrderPayloadStatusByStoreId(): => ',storeId);

    const params = {
        TableName: TABLE_NAME,
        Key: {
            storeId: storeId
        },
        UpdateExpression: 'set #status= :status',
        ExpressionAttributeValues: {
            ':status': INACTIVE
        },
        ExpressionAttributeNames: {
            '#status': 'status'
        }
    };

    console.info('updateOrderPayloadStatusByStoreId() params => ' + JSON.stringify(params));

    return dynamodb.update(params).promise();
};

const updateOrderPayloadOrdersByStoreId = async (storeId, orders) => {

    console.info('Execute updateOrderPayloadOrdersByStoreId(): => ',storeId, orders);

    const params = {
        TableName: TABLE_NAME,
        Key: {
            storeId: storeId.toString()
        },
        UpdateExpression: 'set #orders = :orders',
        ExpressionAttributeValues:{
            ':orders': orders,
        },
        ExpressionAttributeNames:{
            '#orders': 'orders',
        }
    };

    console.info('updateOrderPayloadOrdersByStoreId() params => ' + JSON.stringify(params));

    return dynamodb.update(params).promise();
};

const updateOrderPayloadConnectionIdByStoreId = async ( storeId, connectionId) => {

    console.info('Execute updateOrderPayloadConnectionIdByStoreId(): => ',storeId, connectionId);

    const params = {
        TableName: TABLE_NAME,
        Key: {
            storeId: storeId.toString()
        },
        UpdateExpression: 'set #connectionId = :connectionId, #status = :status',
        ExpressionAttributeValues:{
            ':connectionId': connectionId,
            ':status': ACTIVE
        },
        ExpressionAttributeNames:{
            '#connectionId': 'connectionId',
            '#status': 'status'
        }
    };

    console.info('updateOrderPayloadConnectionIdByStoreId() params => ' + JSON.stringify(params));

    return dynamodb.update(params).promise();
};

module.exports = {
    scanOrderPaylodsByStoreId,
    scanOrderPaylodsByConectionId,
    putOrderPayload,
    updateOrderPayloadStatusByStoreId,
    updateOrderPayloadConnectionIdByStoreId,
    updateOrderPayloadOrdersByStoreId,
};