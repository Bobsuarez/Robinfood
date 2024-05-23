package com.robinfood.entities.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class APIResponseEntity<T> {

    private final Long code;

    private final T data;

    private final Boolean error;

    private final String message;
}
