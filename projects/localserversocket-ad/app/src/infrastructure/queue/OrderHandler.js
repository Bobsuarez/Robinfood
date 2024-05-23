'use strict';

const { orderService } = require('../../application/services/OrderService')

const orderHandler = async (event) => {

    console.info('Execute orderHandler event => ' + JSON.stringify(event));

    return await orderService(event);
};

module.exports = {
    orderHandler,
};