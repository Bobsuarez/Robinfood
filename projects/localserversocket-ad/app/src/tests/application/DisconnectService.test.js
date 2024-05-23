const {
    updateOrderPayloadStatusByStoreId,
    scanOrderPaylodsByConectionId,
} = require('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');

    const { RESPONSE_OK_EMPTY } 
    = require('../../../constants/LambdaConstants');

const disConnectService = require('../../application/services/DisconnectService');
const{mockEventSimple,mockResultDyanamo,mockEventConnect
    ,mockResultdyanamoInactivo}= require('../mock/dataevent');

jest.mock('../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
jest.mock('../../infrastructure/websocket/WebSocketClient');

describe('Test Disconnet Service', () => {
   
    test('where Disconnect', () => {
        scanOrderPaylodsByConectionId.mockReturnValue(Promise.resolve(mockResultdyanamoInactivo));
        updateOrderPayloadStatusByStoreId.mockReturnValue(Promise.resolve(mockResultDyanamo));
        disConnectService.disconnectService(mockEventConnect).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });

    });

    test('where exception is generated in disconnect', () => {
 
        disConnectService.disconnectService(mockEventSimple).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });

    });

});