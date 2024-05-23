package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderToCreateOrderDTO implements Serializable {

    private static final long serialVersionUID = -658917757124043667L;

    @JsonProperty("billingResolutionId")
    private Long billingResolutionId;

    @JsonProperty("brand")
    private OrderToCreateBrandDTO brand;

    @JsonProperty("criteriaInfo")
    private Map<String, Object> criteriaInfo;

    @JsonProperty("couponCriteriaInfo")
    private Map<String, Object> couponCriteriaInfo;

    @JsonProperty("consumptionValue")
    private BigDecimal consumptionValue = BigDecimal.ZERO;

    @JsonProperty("coupons")
    private List<OrderToCreateCouponDTO> coupons;

    @JsonProperty("deliveryTypeId")
    private Long deliveryTypeId;

    @JsonProperty("discounts")
    private List<OrderToCreateDiscountDTO> discounts;

    @JsonProperty("finalProducts")
    private List<OrderToCreateFinalProductDTO> finalProducts;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("hasConsumption")
    private Boolean hasConsumption = Boolean.FALSE;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("origin")
    private OrderToCreateOriginDTO origin;

    @JsonProperty("paymentModelId")
    private Long paymentModelId;

    @JsonProperty("services")
    private List<OrderToCreateServiceDTO> services;

    @JsonProperty("store")
    private OrderToCreateStoreDTO store;

    @JsonProperty("taxCriteria")
    private Map<String, Object> taxCriteria;

    @JsonProperty("thirdParty")
    private OrderToCreateThirdPartyDTO thirdParty;

    @JsonProperty("total")
    private BigDecimal total;

    @JsonProperty("uuid")
    private UUID uuid;
}
