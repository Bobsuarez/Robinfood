

const { RESPONSE_OK_EMPTY } 
    = require('../../../../constants/LambdaConstants');

const{ mockEventSimple}= require('../../mock/dataevent');
const orderHandler = require('../../../infrastructure/queue/OrderHandler');

jest.mock('../../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
jest.mock('../../../infrastructure/websocket/WebSocketClient');

describe('Test with Order Handler', () => {
   
    test('where Order Handler', () => {
        orderHandler.orderHandler(mockEventSimple).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });
    });

});