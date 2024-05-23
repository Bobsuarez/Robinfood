package com.robinfood.core.dtos.report.ordercancellation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
public class GetOrderCancellationResponseDTO {

    private Long brandId;

    private Double discounts;

    private LocalDate localDate;

    private LocalTime localTime;

    @JsonProperty("id")
    private Long orderId;

    private String orderIdIntegration;

    private String orderInvoiceNumber;

    @JsonProperty("uId")
    private String orderUID;

    @JsonProperty("uuId")
    private String orderUUID;

    private Long originId;

    private Boolean paid;

    private String posResolutionsPrefix;

    @JsonProperty("status")
    private statusOrderCancellationDTO statusOrderCancellation;

    private Long storeId;

    private Double subtotal;

    private Double taxes;

    private Double total;

    private BigDecimal totalCo2;

    @JsonProperty("orderUserData")
    private OrderUserDataCancellationDTO userData;

}
