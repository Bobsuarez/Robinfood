package com.robinfood.storeor.dtos.apiresponsebuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class DataResponseDTO<T> {

    @Schema(example = "'data': {}", description = "The content of the HTTP response data")
    private T data;

}
