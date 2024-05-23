package com.robinfood.localserver.commons.dtos.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElectronicBillingResultResponseDTO {

    private String createAt;

    private String orderId;

    private String requestPayload;

    private String responsePayload;

    private String statusCode;

    private String storeId;

    private String storeName;

}
