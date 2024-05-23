package com.robinfood.core.dtos.request.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@AllArgsConstructor
@Data
public class RequestPaymentMethodDTO {

    private RequestPaymentDetailDTO detail;

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Long originId;

    @NotNull
    @Min(value = DEFAULT_INTEGER_VALUE)
    private Double value;

}
