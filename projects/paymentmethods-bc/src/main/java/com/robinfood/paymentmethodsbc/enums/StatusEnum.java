package com.robinfood.paymentmethodsbc.enums;

import java.util.Arrays;
import lombok.Getter;

/**
 * Enum whit status mapping codes
 */
@Getter
public enum StatusEnum {

    PAID("PAID", 1L),
    PENDING("PENDING", 2L),
    CANCELED("CANCELED", 8L),
    REJECTED("REJECTED", 3L),
    FAILED("FAILED", 0L),
    ERROR("ERROR", 9L),
    NO_STATUS("NO_STATUS", -1L);

    private String statusCode;
    private long statusId;

    StatusEnum(String statusCode, long statusId) {
        this.statusCode = statusCode;
        this.statusId = statusId;
    }

    public static StatusEnum getStatusCode(long transactionStatus) {

        return Arrays.stream(StatusEnum.values())
            .filter(x -> x.getStatusId() == transactionStatus)
            .findAny()
            .orElse(NO_STATUS);
    }
}
