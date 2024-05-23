package com.robinfood.core.entities.transactionrequestentities;

import com.robinfood.core.dtos.DeductionDTO;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestEntity {

    private Long billingResolutionId;

    private BrandEntity brand;

    private BigDecimal consumptionValue;

    private Long deliveryTypeId;

    private List<DiscountEntity> discounts;

    private List<DeductionDTO> deductions;

    private List<FinalProductRequestEntity> finalProducts;

    private FlagEntity flags;

    private BigDecimal foodcoins;

    private Boolean hasConsumption;

    private Long id;

    private String notes;

    private OriginEntity origin;

    private Boolean paid;

    private Long paymentModelId;

    private List<ServiceEntity> services;

    private StoreEntity store;

    private BigDecimal subtotal;

    private BigDecimal totalTaxes;

    private BigDecimal totalDiscount;

    private BigDecimal total;

    private BigDecimal totalMinusProductsDiscounts;

    private BigDecimal totalPaidPayments;

    private String uid;

    private String uuid;

    private Long workshiftId;

    private BigDecimal co2Total;
}
