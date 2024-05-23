package com.robinfood.core.enums;

import lombok.Getter;

@Getter
public enum StatusCustomFilterEnum {

    ORDER_CANCELED("ORDER_CANCELED",2L),
    ORDER_PAID("ORDER_PAID", 1L);

    private final String code;

    private final Long id;

    StatusCustomFilterEnum (String code, Long id) {
        this.code = code;
        this.id = id;
    }
}
