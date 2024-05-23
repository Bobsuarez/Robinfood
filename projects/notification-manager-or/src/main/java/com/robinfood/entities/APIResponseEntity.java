package com.robinfood.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class APIResponseEntity<T> {

    private Long code;
    private T data;
    private Boolean error;
    private String message;
}
