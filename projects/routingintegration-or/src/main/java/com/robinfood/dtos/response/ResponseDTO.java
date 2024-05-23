package com.robinfood.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO {

    private Integer code;

    private Object data;

    @Builder.Default
    private Boolean error = false;

    private String message;
}
