package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaxesDTO implements Serializable {

    private  Long familyTypeId;

    private  Long id;

    private  Double price;

    private  Long taxTypeId;

    private  String taxTypeName;

    private  Double value;
}
