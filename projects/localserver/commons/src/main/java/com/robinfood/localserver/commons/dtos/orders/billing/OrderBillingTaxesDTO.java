package com.robinfood.localserver.commons.dtos.orders.billing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderBillingTaxesDTO {

    private Long familyTypeId;

    private Long id;

    private Double price;

    private String taxTypeName;

    private Long taxTypeId;

    private List<TreasuryTaxesDTO> treasuryTaxes;

    private Double value;

}
