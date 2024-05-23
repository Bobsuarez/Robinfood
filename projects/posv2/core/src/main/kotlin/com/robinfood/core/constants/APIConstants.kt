package com.robinfood.core.constants

object APIConstants {

    // API Headers
    const val AUTHORIZATION_HEADER_BEARER = "Bearer "

    // API Response Codes
    const val BAD_REQUEST = 400
    const val CREATED_CODE = 201
    const val NOT_FOUND_CODE = 404
    const val PRECONDITION_FAILED_CODE = 412
    const val SUCCESS_CODE = 200
    const val UNAUTHORIZED_CODE = 401
    const val UNAVAILABLE_CODE = 503


    // API Response Values
    const val UNAUTHORIZED_MESSAGE = "Unauthorized"

    // Controller Endpoints
    const val API_V1 = "/api/v1"
    const val COUPONS = "/coupons"
    const val DELIVERY_INTEGRATIONS = "/delivery-integrations"
    const val GIFT_CARD_VALIDATION = "/gift-card-validation"
    const val MENU = "/menu"
    const val MENU_PRODUCT_DETAIL = "/menu/product"
    const val ORDER_HISTORY = "/history"
    const val ORDERS = "/orders"
    const val ORDER_DETAIL = "/detail"
    const val PAYMENT_METHODS = "/payment-methods"
    const val STORE = "/store"
    const val TRANSACTION = "/transaction"
    const val ORDERS_DAILY = "/orders-daily"
    const val USER = "/user"
    const val REPORT = "/report"
    const val POS_DAILY_VOUCHER = "/{storeId}/voucher-daily"
    const val WITNESS_TAPE = "/{storeId}/witness-tape"
    const val CUSTOMER_INVOICE = "/order/{orderId}/invoice"
    const val CROSS_SELLING_MENU = "/cross-selling-menu"
}