const { 
    scanOrderPaylodsByStoreId, 
    updateOrderPayloadConnectionIdByStoreId} 
    = require('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');

    const { RESPONSE_OK_EMPTY, RESPONSE_BAD_REQUEST_EMPTY } 
    = require('../../../constants/LambdaConstants');

const connectService = require('../../application/services/ConnectService');
const{mockEventSimple,mockResultDyanamo,mockEventConnect, mockResultWithoutItemstDyanamo}= require('../mock/dataevent');

jest.mock('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
jest.mock('../../infrastructure/websocket/WebSocketClient');

describe('Test with connection Service', () => {
   
    test('where exist items', () => {
        scanOrderPaylodsByStoreId.mockReturnValue(Promise.resolve(mockResultDyanamo));  
        updateOrderPayloadConnectionIdByStoreId.mockReturnValue(Promise.resolve(mockResultDyanamo));
        connectService.connectService(mockEventConnect).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });

    });

    test('where not exist items', () => {

        scanOrderPaylodsByStoreId.mockReturnValue(Promise.resolve(mockResultWithoutItemstDyanamo));  
        connectService.connectService(mockEventConnect).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });

    });

    test('where exception is generated', () => {
 
        scanOrderPaylodsByStoreId.mockReturnValue(Promise.resolve(mockResultDyanamo)); 
        connectService.connectService(mockEventSimple).then(reason => {
            expect(reason).toEqual(RESPONSE_BAD_REQUEST_EMPTY);
        });

    });

    test('where exception is generated', () => {
 
        mockEventConnect.requestContext.eventType= "NOCONNECT";
        connectService.connectService(mockEventConnect).then(reason => {
            expect(reason).toEqual(RESPONSE_BAD_REQUEST_EMPTY);
        });

    });

});