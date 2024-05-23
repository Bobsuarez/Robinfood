package com.robinfood.core.dtos.request.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FinalProductPortionDTO {

    @JsonIgnore
    private Boolean addition;

    private Long companyId;

    private Double discount;

    private Integer effectiveSale;

    private Integer free;

    private PortionGroupDTO group;

    private Long id;

    private String name;

    private Long orderFinalProductId;

    private Long orderId;

    private Long orderFinalProductPortionId;

    private Double price;

    private RequestOrderPortionProductDTO product;

    private Integer quantity;

    private ReplacementPortionDTO replacementPortion;

    private String sku;

    private Long storeId;

    private Long unitId;

    private Double unitNumber;

    public FinalProductPortionDTO generateANewReference(FinalProductPortionDTO finalProductPortionDTO) {
        return new FinalProductPortionDTO(
                DEFAULT_BOOLEAN_FALSE_VALUE,
                finalProductPortionDTO.getCompanyId(),
                finalProductPortionDTO.getDiscount(),
                finalProductPortionDTO.getEffectiveSale(),
                finalProductPortionDTO.getFree(),
                finalProductPortionDTO.getGroup(),
                finalProductPortionDTO.getId(),
                finalProductPortionDTO.getName(),
                finalProductPortionDTO.getOrderFinalProductId(),
                finalProductPortionDTO.getOrderId(),
                finalProductPortionDTO.getOrderFinalProductPortionId(),
                finalProductPortionDTO.getPrice(),
                finalProductPortionDTO.getProduct(),
                finalProductPortionDTO.getQuantity(),
                finalProductPortionDTO.getReplacementPortion(),
                finalProductPortionDTO.getSku(),
                finalProductPortionDTO.getStoreId(),
                finalProductPortionDTO.getUnitId(),
                finalProductPortionDTO.getUnitNumber()
        );
    }
}
