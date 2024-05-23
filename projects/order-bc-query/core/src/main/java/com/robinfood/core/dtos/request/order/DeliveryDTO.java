package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@AllArgsConstructor
@Data
public class DeliveryDTO {

    @NotNull
    private String addressCity;

    @NotNull
    private String addressDescription;

    @NotNull
    private LocalDate arrivalDate;

    @NotNull
    private LocalTime arrivalTime;

    @NotNull
    private String code;

    @NotNull
    private String integrationId;

    private String userName;

    private String notes;

    @NotNull
    @Min(value = DEFAULT_INTEGER_VALUE)
    private Integer orderType;

    @NotNull
    private String paymentMethod;

    @NotNull
    @Min(value = DEFAULT_INTEGER_VALUE)
    private Double totalDelivery;

    @NotNull
    @Min(value = DEFAULT_INTEGER_VALUE)
    private Double totalDiscount;

    @NotNull
    @Min(value = DEFAULT_INTEGER_VALUE)
    private Double totalTip;

    private String type;
}
