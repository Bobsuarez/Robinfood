package com.robinfood.localserver.commons.dtos.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestElectronicBillingDTO {

    private String operationDate;

    @NotNull
    @Positive
    private Long orderId;

    @Positive
    @NotNull
    private Long storeId;

    private String storeName;

    private String requestPayload;

    private String responsePayload;

    @NotNull
    @Positive
    private String statusCode;

}