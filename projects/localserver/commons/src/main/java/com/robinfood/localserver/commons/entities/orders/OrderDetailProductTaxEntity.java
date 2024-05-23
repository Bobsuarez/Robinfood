package com.robinfood.localserver.commons.entities.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDetailProductTaxEntity {

    private Long familyTypeId;

    private Long id;
    
    private Double price;

    private Long taxTypeId;

    private String taxTypeName;

    private Double value;
}
