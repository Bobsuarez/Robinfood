const { RESPONSE_OK_EMPTY }
    = require('../../../../constants/LambdaConstants');

const { mockEventConnect } = require('../../mock/dataevent');

const connectionHandler = require('../../../infrastructure/websocket/ConnectionHandler');

jest.mock('../../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
jest.mock('../../../infrastructure/websocket/WebSocketClient');

describe('Test with Connect Handler', () => {

    test('where Conncetion Handler', () => {
        connectionHandler.connectionHandler(mockEventConnect)
        .then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });
    });

});