package com.robinfood.customersbc.thirdparties.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    ACTIVE(1),
    INACTIVE(2);

    private final int status;

    StatusEnum(int status) {
        this.status = status;
    }
}
