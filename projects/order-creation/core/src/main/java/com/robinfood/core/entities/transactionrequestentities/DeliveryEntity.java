package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DeliveryEntity {

    private String addressCity;

    private String addressDescription;

    private String arrivalDate;

    private String arrivalTime;

    private String code;

    private String integrationId;

    private String notes;

    private Integer orderType;

    private String paymentMethod;

    private Double totalDelivery;

    private Double totalDiscount;

    private Double totalTip;

    private String type;

    private String userName;
}
