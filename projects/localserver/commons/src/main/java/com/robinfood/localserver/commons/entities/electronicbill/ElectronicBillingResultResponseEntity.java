package com.robinfood.localserver.commons.entities.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElectronicBillingResultResponseEntity {

    private String createAt;

    private String orderId;

    private String requestPayload;

    private String responsePayload;

    private String statusCode;

    private String storeId;

    private String storeName;

}
