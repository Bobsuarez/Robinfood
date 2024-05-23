package com.robinfood.changestatusor.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponseEntity<T> {

    private final Integer code;

    private final T data;

    private final boolean error;

    private final String locale;

    private final String message;
}
