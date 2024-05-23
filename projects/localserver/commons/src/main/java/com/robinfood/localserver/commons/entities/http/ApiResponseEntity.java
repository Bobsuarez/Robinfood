package com.robinfood.localserver.commons.entities.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponseEntity<T> {

    private Integer code;

    private T data;

    private String locale;

    private String message;

    private String status;
}
