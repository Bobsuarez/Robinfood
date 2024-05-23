package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.core.dtos.DeductionDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderToCreateDTO extends MessageDTO implements Serializable {

    private static final long serialVersionUID = -8133003200203333168L;

    @JsonProperty("company")
    private OrderToCreateCompanyDTO company;

    @JsonProperty("delivery")
    private OrderToCreateDeliveryDTO delivery;

    @JsonProperty("device")
    private OrderToCreateDeviceDTO device;

    @JsonProperty("deductions")
    private List<DeductionDTO> deductions;

    @JsonProperty("flowId")
    private Long flowId;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("orders")
    private List<OrderToCreateOrderDTO> orders;

    @JsonProperty("origin")
    private OrderToCreateOriginDTO origin;

    @JsonProperty("paid")
    private Boolean paid;

    @JsonProperty("paymentMethods")
    private List<OrderToCreatePaymentMethodDTO> paymentMethods;

    private Boolean pickupTimeConsumption;

    @JsonProperty("total")
    private BigDecimal total;

    @JsonProperty("totalPaidPayments")
    private BigDecimal totalPaidPayments;

    @JsonProperty("user")
    private OrderToCreateUserDTO user;

    @JsonProperty("uuid")
    private UUID uuid;
}
