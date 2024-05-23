'use strict';

const _ = require('lodash')
const { scanOrderPaylodsByStoreId, updateOrderPayloadOrdersByStoreId } = require('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
const { ACTIVE, INACTIVE, FIRST_INDEX, RESPONSE_OK_EMPTY, RESPONSE_ERROR_EMPTY } = require('../../../constants/LambdaConstants');
const webSockerClient = require('../../infrastructure/websocket/WebSocketClient');

const orderService = async (event) => {

    try {
        console.info('Execute orderService() => ' + JSON.stringify(event.Records));

        for (const record of event.Records) {

            const order = JSON.parse(record.body);

            console.info('Detail orden => ' + JSON.stringify(order));

            const results = await scanOrderPaylodsByStoreId(order.storeId);
                    
            console.info(`Shop item obtained by scanOrderPaylodsByStoreId() => ${JSON.stringify(results)}`);

            if(_.isEmpty(results.Items)) {
                return RESPONSE_ERROR_EMPTY;
            }

            let store = results.Items[FIRST_INDEX];
            
            if(store.status == ACTIVE) {
                console.info('Send Order to online store with connectionId => ', store.connectionId);

                const params = {
                    ConnectionId: store.connectionId,
                    Data: JSON.stringify(order)
                };

                await webSockerClient.sendMessage(params);
            }

            if(store.status == INACTIVE) {
                console.info('Save Order to offline store storeId => ', store.storeId);

                let orders = store.orders;
                orders.push(order);

                console.info('UpdateOrderPayloadOrdersByStoreId => ',store.storeId, orders);

                await updateOrderPayloadOrdersByStoreId(
                    store.storeId,
                    orders
                );
            }
        }

        return RESPONSE_OK_EMPTY;
        
    } catch (error) {
        console.error('Error catch orderService => ' + error);
        return RESPONSE_ERROR_EMPTY;
    }
};

module.exports = {
    orderService,
};