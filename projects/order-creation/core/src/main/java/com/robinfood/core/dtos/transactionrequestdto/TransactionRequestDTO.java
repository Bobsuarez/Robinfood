package com.robinfood.core.dtos.transactionrequestdto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.dtos.redeemcoupon.PerformCouponResponseDTO;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequestDTO implements Serializable {

    private static final long serialVersionUID = -1347434589560844935L;

    private AddressDTO address;

    @Valid
    private ChannelDTO channel;

    @NotNull
    @Valid
    private CompanyDTO company;

    @NotNull
    @Builder.Default
    private List<CouponDTO> coupons = new ArrayList<>();

    @Valid
    private DeliveryDTO delivery;

    @NotNull
    @Valid
    private DeviceDTO device;

    private List<DeductionDTO> deductions;

    @NotNull
    @Positive
    private Long flowId;

    @Positive
    private Long id;

    private String orderCreatedAt;

    @NotNull
    @Size(min = 1)
    @Valid
    private List<OrderDTO> orders;

    @NotNull
    @Valid
    private OriginDTO origin;

    @JsonProperty("buyer")
    @Valid
    private OrderFiscalIdentifierDTO orderFiscalIdentifier;

    @NotNull
    private Boolean paid;

    @NotNull
    @Size
    @Valid
    private List<PaymentMethodDTO> paymentMethods;

    @Builder.Default
    @Size
    @Valid
    private List<PaymentMethodDTO> paymentsPaid = new ArrayList<>();

    @Builder.Default
    private Boolean pickupTimeConsumption = false;

    @NotNull
    @Min(0)
    private BigDecimal total;

    @NotNull
    @Min(0)
    private BigDecimal totalPaidPayments;

    @Builder.Default
    private Boolean updateOrder = false;

    @NotNull
    @Valid
    private NewUserDTO user;

    @Builder.Default
    private BigDecimal co2Total = BigDecimal.ZERO;

    /**
     * Fields filled in the use cases
     */
    @Builder.Default
    private List<PerformCouponResponseDTO> performCouponResponseDTOS = new ArrayList<>();

    private BigDecimal subtotalOnlyWithDiscount;

    private StoreInformation storeInfo;

    private UUID uuid;

    @JsonIgnore
    public BigDecimal getTotalMinusCoupons() {
        BigDecimal totalCoupons = coupons.stream()
                .map(CouponDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }


}
