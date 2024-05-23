package com.robinfood.dtos.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResponseDTO {

    private Integer code;

    private Object data;

    @Builder.Default
    private Boolean error = false;

    private String message;
}
