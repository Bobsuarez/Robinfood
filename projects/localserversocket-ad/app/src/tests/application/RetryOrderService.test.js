'use strict';

const { scanOrderPaylodsByStoreId,
        scanOrderPaylodsByConectionId,
        updateOrderPayloadOrdersByStoreId 
      } = require('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
const { RESPONSE_OK_EMPTY, CODE_HTTP_BAD_REQUEST } = require('../../../constants/LambdaConstants');
const retryOrderServices = require('../../application/services/RetryOrderService');
const webSockerClient = require('../../infrastructure/websocket/WebSocketClient');
const mockConnectionId = "RTAYTdcciYcCGNg=";
const storeId = '16';
const{mockResultdyanamoInactivo,mockResultDyanamoInit}= require('../mock/dataevent');

jest.mock('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
jest.mock('../../infrastructure/websocket/WebSocketClient');

describe('General test', () => {

    test('where connection found orders by store ', () => {

        let mockResponseWebsocket=RESPONSE_OK_EMPTY;
        scanOrderPaylodsByStoreId.mockReturnValue(Promise.resolve(mockResultdyanamoInactivo));
        webSockerClient.sendMessage.mockReturnValue(Promise.resolve(mockResponseWebsocket));
        updateOrderPayloadOrdersByStoreId.mockReturnValue(Promise.resolve([]));
        return retryOrderServices.getOfflineOrders(storeId, mockConnectionId).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });

    });

    test('where connection found orders by connection ', () => {

        let mockResponseWebsocket=RESPONSE_OK_EMPTY;
        scanOrderPaylodsByConectionId.mockReturnValue(Promise.resolve(mockResultdyanamoInactivo));
        webSockerClient.sendMessage.mockReturnValue(Promise.resolve(mockResponseWebsocket));
        updateOrderPayloadOrdersByStoreId.mockReturnValue(Promise.resolve([]));
        return retryOrderServices.getOfflineOrders('0', mockConnectionId).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });

    });

    test('where connection found orders but error in send', () => {

        let mockResponseWebsocket=RESPONSE_OK_EMPTY;
        scanOrderPaylodsByStoreId.mockReturnValue(Promise.resolve(mockResultdyanamoInactivo));
        webSockerClient.sendMessage.mockReturnValue(Promise.reject());
        updateOrderPayloadOrdersByStoreId.mockReturnValue(Promise.resolve([]));
        return retryOrderServices.getOfflineOrders(storeId, mockConnectionId).catch(reason => {
        expect(reason.status).toEqual(CODE_HTTP_BAD_REQUEST);
        });

    });

    test('where connection not found orders', () => {

        let mockResponseWebsocket=RESPONSE_OK_EMPTY;
        scanOrderPaylodsByStoreId.mockReturnValue(Promise.resolve(mockResultDyanamoInit));
        return retryOrderServices.getOfflineOrders(storeId, mockConnectionId).catch(reason => {
        expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });

    });

});
