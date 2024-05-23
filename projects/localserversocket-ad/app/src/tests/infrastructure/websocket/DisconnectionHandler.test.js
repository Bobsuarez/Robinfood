const { RESPONSE_OK_EMPTY }
    = require('../../../../constants/LambdaConstants');

const { mockEventConnect } = require('../../mock/dataevent');

const disconnectionHandler = require('../../../infrastructure/websocket/DisconnectionHandler');

jest.mock('../../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
jest.mock('../../../infrastructure/websocket/WebSocketClient');

describe('Test with DiscConnect Handler', () => {

    test('where DisconnetConncetion Handler', () => {
        disconnectionHandler.disconnectionHandler(mockEventConnect)
        .then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });
    });

});