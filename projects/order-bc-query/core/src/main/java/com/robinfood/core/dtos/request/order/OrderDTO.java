package com.robinfood.core.dtos.request.order;

import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.request.transaction.RequestOriginDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDTO {

    @Schema(example = "1", description = "The identifier of the order")
    @Positive
    private final Long billingResolutionId;

    @NotNull
    private final BrandDTO brand;

    private final Double consumptionValue;

    private final String carPlate;

    @NotNull
    @Positive
    private final Long deliveryTypeId;

    private List<DeductionDTO> deductions;

    @NotNull
    @Size
    @Valid
    private final List<DiscountDTO> discounts;

    @NotNull
    @Valid
    @Size(min = 1)
    private final List<FinalProductDTO> finalProducts;

    private final BigDecimal foodcoins;

    @Valid
    private final OrderFlagDTO flags;

    private final Boolean hasConsumption;

    private final Long id;

    private final Long menuId;

    private final String notes;

    private final Long orderInvoiceNumber;

    private final String orderNumber;

    @NotNull
    @Valid
    private final RequestOriginDTO origin;

    private final Boolean paid;

    @NotNull
    @Positive
    private final Long paymentModelId;

    @NotNull
    @Size
    @Valid
    private final List<ServiceDTO> services;

    private final StoreDTO store;

    private final BigDecimal co2Total;

    private final Double total;

    private final Double totalMinusProductsDiscounts;

    private final Double totalPaidPayments;

    private final Long transactionId;

    private final Long userId;

    private final String uid;

    @NotNull
    private final String uuid;

    private final Long workshiftId;

    private final Double subtotal;

    private final Double totalTaxes;

    private final Double totalDiscount;
}
