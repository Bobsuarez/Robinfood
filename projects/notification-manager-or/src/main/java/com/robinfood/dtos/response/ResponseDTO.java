package com.robinfood.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResponseDTO implements Serializable {

    private Integer code;

    private Object data;

    @Builder.Default
    private Boolean error = false;

    private String message;
}
