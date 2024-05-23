package com.robinfood.core.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponseEntity<T> {

    private final Integer code;

    private final T data;

    private final Object error;

    private final String locale;

    private final String message;

    private final Object properties;
}
