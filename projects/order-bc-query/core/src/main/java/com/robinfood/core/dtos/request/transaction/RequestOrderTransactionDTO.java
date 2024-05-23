package com.robinfood.core.dtos.request.transaction;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.OrderCouponDTO;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.dtos.request.order.DeliveryDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RequestOrderTransactionDTO {

    private AddressDTO address;

    @Builder.Default
    private Boolean alreadyValidated = false;

    @NotNull
    @Valid
    private RequestCompanyDTO company;

    private List<DeductionDTO> deductions;

    private List<OrderCouponDTO> couponResponseEntities;

    @Valid
    private DeliveryDTO delivery;

    @NotNull
    @Valid
    private RequestDeviceDTO device;

    private Long flowId;

    @Valid
    private List<OrderDTO> orders;

    @JsonProperty("buyer")
    private OrderFiscalIdentifierDTO orderFiscalIdentifier;

    @NotNull
    private Boolean paid;

    @NotNull
    @Size
    @Valid
    private List<RequestPaymentMethodDTO> paymentMethods;

    @Size
    @Valid
    @Builder.Default
    private List<RequestPaymentMethodDTO> paymentsPaid = new ArrayList<>();

    private BigDecimal co2Total;

    @NotNull
    @Min(value = DEFAULT_INTEGER_VALUE)
    private Double total;

    @NotNull
    @Valid
    private RequestUserDTO user;

    @NotNull
    private UUID uuid;

    @Nullable
    private Long id;

    public RequestOrderTransactionDTO(
            Boolean alreadyValidated,
            RequestCompanyDTO company,
            List<OrderCouponDTO> couponsApplied,
            DeliveryDTO delivery,
            RequestDeviceDTO device,
            Long flowId,
            List<OrderDTO> orders,
            OrderFiscalIdentifierDTO orderFiscalIdentifier,
            Boolean paid,
            List<RequestPaymentMethodDTO> paymentMethods,
            List<RequestPaymentMethodDTO> paymentsPaid,
            BigDecimal co2Total,
            Double total,
            RequestUserDTO user,
            @Nullable Long id,
            UUID uuid) {
        this.alreadyValidated = alreadyValidated;
        this.company = company;
        this.couponResponseEntities = couponsApplied;
        this.delivery = delivery;
        this.device = device;
        this.flowId = flowId;
        this.orders = orders;
        this.orderFiscalIdentifier = orderFiscalIdentifier;
        this.paid = paid;
        this.paymentMethods = paymentMethods;
        this.paymentsPaid = paymentsPaid;
        this.co2Total = co2Total;
        this.total = total;
        this.user = user;
        this.id = id;
        this.uuid = uuid;
    }
}
