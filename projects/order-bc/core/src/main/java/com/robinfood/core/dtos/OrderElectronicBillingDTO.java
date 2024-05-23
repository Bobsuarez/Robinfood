package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderElectronicBillingDTO {

    private Date createdAt;

    private Long id;

    private Long orderId;

    private String responsePayload;

    private String requestPayload;

    private String statusCode;

    private Long storeId;

    private String storeName;

    private Date updatedAt;
}
