package com.robinfood.app.enums;

import javax.validation.constraints.NotNull;

public enum OrderStatusEnum {

    ORDER_STATUS_CREATED("CREATED"),
    ORDER_STATUS_REJECTED("REJECTED");

    private final String orderStatus;

    OrderStatusEnum(@NotNull final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

}
