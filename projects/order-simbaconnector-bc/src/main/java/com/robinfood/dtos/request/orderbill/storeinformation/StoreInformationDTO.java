package com.robinfood.dtos.request.orderbill.storeinformation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StoreInformationDTO {

    private String address;
    private CityDTO city;
    private CountryDTO country;
    private String email;
    private FlowTaxDTO flowTax;
    private Long id;
    private String identification;
    private String internalName;
    private String location;
    private String name;
    private String phone;
    private StateDTO state;
    private String timezone;
}
