package com.robinfood.localserver.commons.entities.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiSsoResponseEntity<T> {
    private Integer code;

    private T result;

    private String locale;

    private String message;

    private String status;
}