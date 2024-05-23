package com.robinfood.ordereports_bc_muyapp.enums;

import com.robinfood.app.library.constanst.GlobalConstants;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderDetailLogEnum {

    RECEIVING_REQUEST("0001", "Receiving request to get order detail by transactionUuid: {}"),
    GETTING_TRANSACTION_ID_REQUEST("0002", "Getting order detail with transactionUuid: {}"),
    GETTING_ORDER_DETAIL("0003", "Getting order detail with transactionUuid: {}"),
    GETTING_COUPON_DETAIL("0003", "Getting coupon detail with transactionUuid: {}"),
    GETTING_FLOW_ID("0004", "Getting flow with transactionUuid: {}"),
    FOLLOWING_IS_FOUND_FLOW_ID("0005", "found flow id: {}"),
    GETTING_PLATFORM_ID("0006", "Getting platform with transactionUuid: {}"),
    GETTING_LIST_ORDER_HISTORY("0007", "Getting list order history with transactionUuid: {}"),
    GETTING_ORDER_HISTORY("0008", "Getting order history with transactionUuid: {}"),
    GETTING_ORDER_DETAIL_INFO("0009", "Getting order detail additional info with id: {},by transactionUuid: {}"),
    GETTING_ORDER_SERVICES("00010", "Getting order services with transactionUuid: {}"),
    GETTING_ORDER_DISCOUNTS("00011", "Getting order discounts with transactionUuid: {}"),
    GETTING_ORDER_PAYMENTS("00012", "Getting order payment with transactionUuid: {}"),
    GETTING_ORDER_ADDRESS("00013", "Getting order address with transactionUuid: {}"),
    GETTING_ORDER_USER_DATA("00014", "Getting order user data with transactionUuid: {}"),;

    private final String code;

    private final String message;

    public String getMessage() {
        return code + GlobalConstants.DEFAULT_STRING_SPACE + message;
    }
}
