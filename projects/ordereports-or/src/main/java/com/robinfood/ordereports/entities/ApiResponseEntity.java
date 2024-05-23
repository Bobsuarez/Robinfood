package com.robinfood.ordereports.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiResponseEntity<T> {

    private  Integer code;

    private  T data;

    private  Object error;

    private  String locale;

    private  String message;

    private  Object properties;
}
