const { RESPONSE_OK_EMPTY, FIRST_INDEX }
    = require('../../../../constants/LambdaConstants');

const AWS = require('aws-sdk');

const { mockEventSimple } = require('../../mock/dataevent');

const webSocketClient = require('../../../infrastructure/websocket/WebSocketClient');


const connectionId = "RTAYTdcciYcCGNg=";

describe('Test with WebSocket Handler', () => {

    test('where WebSocket Handler', () => {

        const params = {
            ConnectionId: connectionId,
            Data: JSON.stringify(mockEventSimple.Records[FIRST_INDEX].body)
        };

        webSocketClient.sendMessage(params).then(reason => {
            expect(reason).toEqual(RESPONSE_OK_EMPTY);
        });
    });

});