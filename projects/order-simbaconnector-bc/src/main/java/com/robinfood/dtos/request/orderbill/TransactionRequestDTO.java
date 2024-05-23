package com.robinfood.dtos.request.orderbill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.dtos.request.orderbill.storeinformation.StoreInformationDTO;
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
public class TransactionRequestDTO {

    private AddressDTO address;

    private ChannelDTO channel;

    private CompanyDTO company;

    private List<CouponDTO> coupons = new ArrayList<>();

    private Object delivery;

    private DeviceDTO device;

    private List<DeductionDTO> deductions;

    private Long flowId;

    private Long id;

    private String orderCreatedAt;

    private List<OrderDTO> orders;

    private OriginDTO origin;

    @JsonProperty("buyer")
    private Object orderFiscalIdentifier;

    private Boolean paid;

    private List<PaymentMethodDTO> paymentMethods;

    private List<PaymentMethodDTO> paymentsPaid = new ArrayList<>();

    private Boolean pickupTimeConsumption = false;

    private BigDecimal total;

    private BigDecimal totalPaidPayments;

    private Boolean updateOrder = false;

    private NewUserDTO user;

    private BigDecimal co2Total = BigDecimal.ZERO;

    private List<Object> performCouponResponseDTOS = new ArrayList<>();

    private BigDecimal subtotalOnlyWithDiscount;

    private StoreInformationDTO storeInfo;

    private UUID uuid;

}
