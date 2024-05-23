package com.robinfood.storeor.entities.electronicbilling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectronicBillingEntity {

    private String createdAt;

    private String orderId;

    private String responsePayload;

    private String requestPayload;

    private String statusCode;

    private String storeId;

    private String storeName;

}
