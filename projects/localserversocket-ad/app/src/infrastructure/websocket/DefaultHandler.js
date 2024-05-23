'use strict';

const { getOfflineOrders } = require('../../application/services/RetryOrderService');
const { GET_OFFLINE_ORDERS } = require('../../../constants/LambdaConstants');
const webSockerClient = require('./WebSocketClient');

const defaultHandler = async (event) => {

    console.info('Execute defaultHandler() => ' + JSON.stringify(event));

    let body = event.body;

    if (GET_OFFLINE_ORDERS === body) {
        body = '{"storeId":"0","message":"getOfflineOrders"}';
    }

    body = JSON.parse(body);

    let connectionId = event.requestContext.connectionId

    if (GET_OFFLINE_ORDERS === body.message) {

        console.info('Execute retryOrderService.getOfflineOrders() => ', body.storeId);

        return getOfflineOrders(body.storeId, connectionId);
    }

    console.warn('Execute warn defaultHandler => Unspecified command ', body);

    const params = {
        ConnectionId: connectionId,
        Data: JSON.stringify({
            'error': true,
            'message': 'Unspecified command'
        })
    };

    return webSockerClient.sendMessage(params);
};

module.exports = {
    defaultHandler,
};