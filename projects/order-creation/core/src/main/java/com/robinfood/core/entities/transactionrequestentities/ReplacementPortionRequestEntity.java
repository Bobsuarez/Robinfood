package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplacementPortionRequestEntity {

    private Long id;

    private String name;

    private PortionProductEntity product;

    private String sku;

    private Long unitId;

    private Double unitNumber;
}
