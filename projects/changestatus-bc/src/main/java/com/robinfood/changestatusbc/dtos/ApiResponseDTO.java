package com.robinfood.changestatusbc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO<T> implements Serializable {

    private int code;

    private T data;

    private Boolean error;

    private String message;
}
