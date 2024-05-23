package com.robinfood.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    ORDER_IN_PROGESS(2L),

    ORDER_READY_TO_DELIVERED(3L),

    ORDER_DELIVERED(5L);

    private final Long statusId;

    StatusEnum(Long statusId) {
        this.statusId = statusId;
    }

}
