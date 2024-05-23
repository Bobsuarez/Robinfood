'use strict';

const { CODE_HTTP_OK, RESPONSE_OK_EMPTY, FIRST_INDEX } = require('../../../constants/LambdaConstants');
const {
    updateOrderPayloadStatusByStoreId,
    scanOrderPaylodsByConectionId,
} = require('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');

const disconnectService = async (event) => {

    try {
        console.info('Execute disconnectService() => ' + JSON.stringify(event));

        let result = await scanOrderPaylodsByConectionId(event.requestContext.connectionId);

        console.info('Result value conectionPayload =>' + JSON.stringify(result));

        let conectionPayload =  result.Items[FIRST_INDEX];

        console.info('Value conectionPayload => ' + JSON.stringify(conectionPayload));

        let response = await updateOrderPayloadStatusByStoreId(conectionPayload.storeId);

        console.info('Result updateOrderPayloadStatusByStoreId() => ' + JSON.stringify(response));

        return {
            statusCode: CODE_HTTP_OK,
            body: JSON.stringify(response),
        };

    } catch (error) {
        console.error('Error catch disconnectService => ' + error);
        return RESPONSE_OK_EMPTY;
    }
};

module.exports = {
    disconnectService,
};