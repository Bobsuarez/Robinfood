
const { 
    scanOrderPaylodsByStoreId, 
    updateOrderPayloadOrdersByStoreId } 
    = require('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
const {RESPONSE_OK_EMPTY, RESPONSE_ERROR_EMPTY } = require('../../../constants/LambdaConstants');
const webSockerClient = require('../../infrastructure/websocket/WebSocketClient');
const orderService= require('../../application/services/OrderService');
const{mockEventSimple,mockResultDyanamo,mockResultdyanamoInactivo}= require('../mock/dataevent');

jest.mock('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
jest.mock('../../infrastructure/websocket/WebSocketClient');

describe('Test with event', () => {
   
    test('where  store found and active', () => {

        let mockResponseWebsocket=RESPONSE_OK_EMPTY;
        scanOrderPaylodsByStoreId.mockReturnValue(Promise.resolve(mockResultDyanamo));
        webSockerClient.sendMessage.mockReturnValue(Promise.resolve(mockResponseWebsocket))
        return orderService.orderService(mockEventSimple).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });
    });

    test('where  store not found', () => {

        scanOrderPaylodsByStoreId.mockReturnValue(Promise.resolve([]));
        return orderService.orderService(mockEventSimple).then(reason => {
            expect(reason).toEqual(RESPONSE_ERROR_EMPTY);
        });
    });

    test('where  store found and not active', () => {
       
        scanOrderPaylodsByStoreId.mockReturnValue(Promise.resolve(mockResultdyanamoInactivo));
        updateOrderPayloadOrdersByStoreId.mockReturnValue(Promise.resolve([]));
        return orderService.orderService(mockEventSimple).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });
    });

    test('where event is badformation', () => {
      
        return orderService.orderService([]).then(reason => {
            expect(reason).toEqual(RESPONSE_ERROR_EMPTY);
        });
    });

});





