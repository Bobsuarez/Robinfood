'use strict';

const _ = require('lodash')
const {
    scanOrderPaylodsByConectionId,
    scanOrderPaylodsByStoreId,
    updateOrderPayloadOrdersByStoreId
} = require('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
const { RESPONSE_OK_EMPTY, FIRST_INDEX, CODE_HTTP_BAD_REQUEST } = require('../../../constants/LambdaConstants');
const webSockerClient = require('../../infrastructure/websocket/WebSocketClient');

const getOfflineOrders = async (storeId, connectionId) => {

    console.info('Execute getOfflineOrders() => ', storeId, ' ', connectionId);

    let results;

    if (storeId === '0') {
        results = await scanOrderPaylodsByConectionId(connectionId);
    } else {
        results = await scanOrderPaylodsByStoreId(storeId);
    }

    let store = results.Items[FIRST_INDEX];
    let ordesToSend;

    if (!_.isEmpty(store.orders)) {

        console.log('Quantity of pending orders for store storeId %s is: ', store.storeId, store.orders.length);

        console.debug('The store has pending orders' + store.orders);

        ordesToSend = await store.orders.map(function (order) {
            const params = {
                ConnectionId: connectionId,
                Data: JSON.stringify(order)
            };

            return webSockerClient.sendMessage(params);
        })

        Promise.all(ordesToSend).catch(error => {
            console.error('Error sending pending orders ' + error)
            return {
                message: error,
                status: CODE_HTTP_BAD_REQUEST,
            }
        });

        await updateOrderPayloadOrdersByStoreId(
            store.storeId, []
        );
    }

    return RESPONSE_OK_EMPTY;
};

module.exports = {
    getOfflineOrders,
};