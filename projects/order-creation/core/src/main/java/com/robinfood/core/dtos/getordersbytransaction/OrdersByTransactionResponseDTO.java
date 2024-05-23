package com.robinfood.core.dtos.getordersbytransaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersByTransactionResponseDTO implements Serializable {

    private Long billingResolutionId;

    private Long brandId;

    private String brandName;

    private Long companyId;

    private String createdAt;

    private String currency;

    private Long deliveryTypeId;

    private Double discounts;

    private Long id;

    private String localDate;

    private String localTime;

    private Integer numberFinalProducts;

    private String operationDate;

    private String orderInvoiceNumber;

    private String orderNumber;

    private Long originId;

    private String originName;

    private Boolean paid;

    private Long paymentModelId;

    private String pickupTime;

    private Long posId;

    private Boolean printed;

    private Long statusId;

    private Long storeId;

    private String storeName;

    private Double subtotal;

    private Double taxes;

    private Long transactionId;

    private Double total;

    private String uid;

    private Long userId;

    private Long workshiftId;
}
