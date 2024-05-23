

const { RESPONSE_OK_EMPTY } 
    = require('../../../../constants/LambdaConstants');

const{ mockEventConnect, mockEventOff}= require('../../mock/dataevent');
const defaultHandler = require('../../../infrastructure/websocket/DefaultHandler');

jest.mock('../../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
jest.mock('../../../infrastructure/websocket/WebSocketClient');

describe('Test with Default Handler', () => {
   
    test('where Handler response is by store id', () => {
        defaultHandler.defaultHandler(mockEventOff).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });
    });

    test('where Handler response is by connection id', () => {
        mockEventOff.body='getOfflineOrders'
        defaultHandler.defaultHandler(mockEventOff).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });
    });

    test('where Handler response ', () => {
        defaultHandler.defaultHandler(mockEventConnect).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });
    });

});