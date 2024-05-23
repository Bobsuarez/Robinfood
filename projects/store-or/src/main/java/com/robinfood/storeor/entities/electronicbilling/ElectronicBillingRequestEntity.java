package com.robinfood.storeor.entities.electronicbilling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectronicBillingRequestEntity {

    private String operationDate;

    private Long orderId;

    private String requestPayload;

    private String responsePayload;

    private String statusCode;

    private Long storeId;

    private String storeName;

}
