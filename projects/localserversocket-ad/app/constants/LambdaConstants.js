'use strict';

//Routes
const APIGATEWAY_ENDPOINT = process.env.APIGATEWAY_ENDPOINT;
const GET_OFFLINE_ORDERS = 'getOfflineOrders';

// Http Status
const CODE_HTTP_OK = 200;
const CODE_HTTP_BAD_REQUEST = 400;
const CODE_INTERNAL_SERVER_ERROR = 500;

// DynamoDB
const TABLE_NAME = 'orders-payload';

// Event Types
const EVENT_TYPE_CONNECT = 'CONNECT'

// Orders Payload Status
const ACTIVE = '1';
const INACTIVE = '0';

// Responses
const RESPONSE_OK_EMPTY = {
    statusCode: CODE_HTTP_OK,
    body: JSON.stringify({})
};

const RESPONSE_ERROR_EMPTY = {
    statusCode: CODE_INTERNAL_SERVER_ERROR,
    body: JSON.stringify({})
};

const RESPONSE_BAD_REQUEST_EMPTY = {
    statusCode: CODE_HTTP_BAD_REQUEST,
    body: JSON.stringify({})
};

// Position
const FIRST_INDEX = 0;

// Api Version
const API_VERSION = '2018-11-29';

module.exports = {
    CODE_HTTP_OK,
    CODE_HTTP_BAD_REQUEST,
    RESPONSE_BAD_REQUEST_EMPTY,
    TABLE_NAME,
    ACTIVE,
    INACTIVE,
    RESPONSE_OK_EMPTY,
    RESPONSE_ERROR_EMPTY,
    FIRST_INDEX,
    API_VERSION,
    GET_OFFLINE_ORDERS,
    APIGATEWAY_ENDPOINT,
    EVENT_TYPE_CONNECT
};