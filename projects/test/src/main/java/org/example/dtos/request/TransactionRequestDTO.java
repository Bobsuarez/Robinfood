package org.example.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionRequestDTO {

    private Object address;

    private BigDecimal co2Total = BigDecimal.ZERO;

    private Object channel;

    private Object company;

    private List<Object> coupons = new ArrayList<>();

    private Object delivery;

    private Object device;

    private List<Object> deductions;

    private Long flowId;

    private Long id;

    private String orderCreatedAt;

    private List<OrderDTO> orders;

    private Object origin;

    @JsonProperty("buyer")
    private Object orderFiscalIdentifier;

    private Boolean paid;

    private List<Object> paymentMethods;

    private List<Object> paymentsPaid = new ArrayList<>();

    private Boolean pickupTimeConsumption = false;

    private BigDecimal total;

    private BigDecimal totalPaidPayments;

    private Boolean updateOrder = false;

    private Object user;

    private List<Object> performCouponResponseDTOS = new ArrayList<>();

    private BigDecimal subtotalOnlyWithDiscount;

    private Object storeInfo;

    private UUID uuid;

}
