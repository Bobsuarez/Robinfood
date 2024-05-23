package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIServicesResponseEntity<T> {

    private Integer code;

    private String message;

    private List<String> error;

    private T data;

}
