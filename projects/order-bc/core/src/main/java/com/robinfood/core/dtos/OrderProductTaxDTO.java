package com.robinfood.core.dtos;

import lombok.Data;

@Data
public class OrderProductTaxDTO {

    private final Long articleId;

    private final Long articleTypeId;

    private final Long dicTaxId;

    private final Long familyTaxTypeId;

    private final Long id;

    private final Long orderFinalProductId;

    private final Long orderId;

    private final Double taxPrice;

    private final Long taxTypeId;

    private final String taxTypeName;

    private final Double taxValue;
}
