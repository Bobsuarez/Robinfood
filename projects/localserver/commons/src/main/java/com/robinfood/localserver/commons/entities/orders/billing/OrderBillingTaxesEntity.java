package com.robinfood.localserver.commons.entities.orders.billing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderBillingTaxesEntity {

    private Long familyTypeId;

    private Long id;

    private Double price;

    private String taxTypeName;

    private Long taxTypeId;

    private List<TreasuryTaxesEntity> treasuryTaxes;

    private Double value;
}
