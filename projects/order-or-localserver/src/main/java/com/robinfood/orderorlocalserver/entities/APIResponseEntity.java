package com.robinfood.orderorlocalserver.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponseEntity<T> {

    private final Integer code;

    private final T data;

    private final String locale;

    private final String message;

    private final String status;

}
