package com.robinfood.storeor.dtos.electronicbilling;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class CreateElectronicBillingRequestDTO {

    private String operationDate;

    @NotNull
    @Positive
    private Long orderId;

    private String requestPayload;

    private String responsePayload;

    @NotNull
    @Positive
    private String statusCode;

    @Positive
    @NotNull
    private Long storeId;

    private  String storeName;
}

