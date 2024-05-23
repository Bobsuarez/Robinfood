package com.robinfood.core.dtos.request.transaction;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class RequestOriginDTO {

    @NotNull
    @Positive
    private final Long id;

    @NotBlank
    private final String name;
}
