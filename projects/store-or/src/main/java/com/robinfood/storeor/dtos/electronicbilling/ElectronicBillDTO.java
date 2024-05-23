package com.robinfood.storeor.dtos.electronicbilling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ElectronicBillDTO {

    private String createdAt;

    private String orderId;

    private String responsePayload;

    private String requestPayload;

    private String statusCode;

    private String storeId;

    private String storeName;
}
