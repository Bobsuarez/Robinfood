'use strict';

const { connectService } = require('../../application/services/ConnectService')
const { RESPONSE_OK_EMPTY } = require('../../../constants/LambdaConstants')

const connectionHandler = async (event) => {

    console.info('Execute connectionHandler() => ' + JSON.stringify(event));

    await connectService(event);

    return RESPONSE_OK_EMPTY;
};

module.exports = {
    connectionHandler,
};