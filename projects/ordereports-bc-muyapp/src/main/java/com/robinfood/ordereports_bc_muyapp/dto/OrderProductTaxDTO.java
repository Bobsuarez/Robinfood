package com.robinfood.ordereports_bc_muyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderProductTaxDTO {

    private Long articleId;

    private Long articleTypeId;

    private Long dicTaxId;

    private Long familyTaxTypeId;

    private Long id;

    private Long orderFinalProductId;

    private Long orderId;

    private Double taxPrice;

    private Long taxTypeId;

    private String taxTypeName;

    private Double taxValue;
}
