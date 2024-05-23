package com.robinfood.core.entities;

import lombok.Data;

@Data
public class APIResponseEntity<T> {

    private final Integer code;

    private final T data;

    private final String locale;

    private final String message;

    private final String status;

}
