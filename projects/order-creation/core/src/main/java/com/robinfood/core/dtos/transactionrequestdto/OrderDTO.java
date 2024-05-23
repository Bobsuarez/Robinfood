package com.robinfood.core.dtos.transactionrequestdto;

import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.transactionresponsedto.OrderDiscountResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.OrderStatusResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 6623614449999577563L;

    @Positive
    private Long billingResolutionId;

    @NotNull
    private FinalProductBrandDTO brand;

    private Map<String, Object> criteriaInfo;

    @Builder.Default
    private List<CouponDTO> coupons = new ArrayList<>();

    private Map<String, Object> couponCriteriaInfo;

    private BigDecimal consumptionValue = BigDecimal.ZERO;

    @NotNull
    @Positive
    private Long deliveryTypeId;

    @Builder.Default
    private List<DeductionDTO> deductions = new ArrayList<>();

    @NotNull
    @Size
    @Valid
    private List<DiscountDTO> discounts;

    private List<OrderDiscountResponseDTO> discountsApplied;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<FinalProductDTO> finalProducts;

    @Valid
    private FlagDTO flags;

    private BigDecimal foodcoins;

    @Positive
    private Long id;

    private String invoiceNumber;

    @Builder.Default
    private Boolean isMultiBrand = Boolean.FALSE;

    private Boolean hasConsumption = Boolean.FALSE;

    @NotBlank
    private String notes;

    private String orderNumber;

    @NotNull
    @Valid
    private OriginDTO origin;

    @NotNull
    @Positive
    private Long paymentModelId;

    private PosResolutionDTO posResolution;

    @NotNull
    @Size
    @Valid
    private List<ServiceDTO> services;

    private BigDecimal subtotal;

    private OrderStatusResponseDTO status;

    @NotNull
    @Valid
    private StoreDTO store;

    @NotNull
    @Valid
    private Map<String, Object> taxCriteria;

    @NotNull
    @Positive
    private BigDecimal total;

    private BigDecimal totalTaxes;

    private BigDecimal totalDiscount;

    @Valid
    private ThirdPartyDTO thirdParty;

    @Builder.Default
    private BigDecimal co2Total = BigDecimal.ZERO;

    private String uid;

    private UUID uuid;
}
