package com.robinfood.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum CustomFiltersEnum {

    CLIENT_MOBILE(3L, "client_mobile"),
    CLIENT_NAME(2L, "client_name"),
    NUMBER_BILL(1L, "number_bill"),
    ORDER_INTEGRATION(6L, "integration_number"),
    ORDER_NUMBER(4L, "order_number"),
    ORDER_UUID(5L, "internal_id");

    private final Long code;
    private final String filters;

    public static CustomFiltersEnum parse(Long code) {

       return Arrays.stream(values())
                .filter(data -> data.getCode().equals(code))
                .findFirst()
                .orElse(CLIENT_NAME);
    }
}
