package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderIntegrationDTO {

    private String addressCity;

    private String addressDescription;

    private Date arrivalDate;

    private Time arrivalTime;

    private Long brandId;

    private String brandName;

    private String code;

    private String integrationId;

    private String notes;

    private Long orderId;

    private Long originId;

    private String originName;

    private Integer orderType;

    private String paymentMethod;

    private Long storeId;

    private String storeName;

    private Double totalDelivery;

    private Double totalDiscount;

    private Double totalOrder;

    private Double totalTip;

    private String userName;

    private String userPhone;
}
