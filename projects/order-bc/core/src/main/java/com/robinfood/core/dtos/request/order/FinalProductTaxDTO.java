package com.robinfood.core.dtos.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FinalProductTaxDTO {

    private Long articleId;

    private Long articleTypeId;

    private Long dicTaxId;

    private Long familyId;

    private Long familyTaxTypeId;

    private Long id;

    private Long orderFinalProductId;

    private Long orderId;

    private Double taxPrice;

    private Long taxTypeId;

    private String taxTypeName;

    private Long taxId;

    private Double taxValue;
}
