package org.example.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDTO {

    private Long billingResolutionId;

    private FinalProductBrandDTO brand;

    private Map<String, Object> criteriaInfo;

    private BigDecimal co2Total = BigDecimal.ZERO;

    private List<CouponDTO> coupons = new ArrayList<>();

    private Map<String, Object> couponCriteriaInfo;

    private BigDecimal consumptionValue = BigDecimal.ZERO;

    private Long deliveryTypeId;

    private List<DeductionDTO> deductions = new ArrayList<>();

    private List<DiscountDTO> discounts;

    private List<OrderDiscountDTO> discountsApplied;

    private List<FinalProductDTO> finalProducts;

    private FlagDTO flags;

    private BigDecimal foodcoins;

    private Long id;

    private String invoiceNumber;

    private Boolean isMultiBrand = Boolean.FALSE;

    private Boolean hasConsumption = Boolean.FALSE;

    private String notes;

    private String orderNumber;

    private OriginDTO origin;

    private Long paymentModelId;

    private PosResolutionDTO posResolution;

    private List<ServiceDTO> services;

    private BigDecimal subtotal;

    private OrderStatusDTO status;

    private StoreDTO store;

    private Map<String, Object> taxCriteria;

    private BigDecimal total;

    private BigDecimal totalTaxes;

    private BigDecimal totalDiscount;

    private ThirdPartyDTO thirdParty;

    private String uid;

    private UUID uuid;
}
