package com.robinfood.core.dtos.request.transaction;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestCompanyDTO {

    @NotBlank
    private final String currency;

    @NotNull
    private final Long id;
}
