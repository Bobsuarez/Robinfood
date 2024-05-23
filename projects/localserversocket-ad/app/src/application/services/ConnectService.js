'use strict';

const _ = require('lodash'); 
const { ACTIVE, RESPONSE_OK_EMPTY, RESPONSE_BAD_REQUEST_EMPTY, FIRST_INDEX, EVENT_TYPE_CONNECT } = require('../../../constants/LambdaConstants');
const { putOrderPayload,
    scanOrderPaylodsByStoreId,
    updateOrderPayloadConnectionIdByStoreId,
} = require('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');

const connectService = async (event) => {

    try {

        console.info('Execute connectService() => ' + JSON.stringify(event));

        if (event.requestContext.eventType === EVENT_TYPE_CONNECT) {
            console.info('Access to the conditional connection');
            
            let conectionPayload = {
                id: event.queryStringParameters.storeId,
                connectionId: event.requestContext.connectionId,
                storeId: event.queryStringParameters.storeId,
                orders: [],
                status: ACTIVE,
            };

            console.info('Create the connection payload object => ' + JSON.stringify(conectionPayload));
            
            const results = await scanOrderPaylodsByStoreId( conectionPayload.storeId);

            let response = {};

            if (!_.isEmpty(results.Items)) {

                let store = results.Items[FIRST_INDEX];

                console.info('Shop item obtained by scanOrderPaylodsByStoreId() => ' + JSON.stringify(store));

                console.info('Execute Update connection: ' + event.requestContext.connectionId);
                console.info('Store information : store id :',store.storeId);

                response = await updateOrderPayloadConnectionIdByStoreId(
                    store.storeId,
                    conectionPayload.connectionId
                );

                console.log('Store not is empty updated response => ' + JSON.stringify(response));
            }

            if (_.isEmpty(results.Items)) {
                
                console.log('New connection => ' + conectionPayload.connectionId);

                response = await putOrderPayload(conectionPayload);

                console.log('Store is empty create response => ' + JSON.stringify(response));
            }
            
            return RESPONSE_OK_EMPTY;
        }
    } catch (error) {
        console.error('Error catch connectService() => ' + error);
        return RESPONSE_BAD_REQUEST_EMPTY;
    }
};

module.exports = {
    connectService,
};