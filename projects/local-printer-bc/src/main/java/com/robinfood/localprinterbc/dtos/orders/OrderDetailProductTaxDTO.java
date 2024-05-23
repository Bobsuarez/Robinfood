package com.robinfood.localprinterbc.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDetailProductTaxDTO {

    private  Long familyTypeId;

    private  Long id;

    private  Double price;

    private String taxTypeName;

    private  Long taxTypeId;

    private  Double value;
}
