'use strict';

const { disconnectService } = require('../../application/services/DisconnectService')
const { RESPONSE_OK_EMPTY } = require('../../../constants/LambdaConstants')

const disconnectionHandler = async (event) => {

    console.info('Execute disconnectionHandler() => ' + JSON.stringify(event));

    await disconnectService(event);

    return RESPONSE_OK_EMPTY;
};

module.exports = {
    disconnectionHandler,
};