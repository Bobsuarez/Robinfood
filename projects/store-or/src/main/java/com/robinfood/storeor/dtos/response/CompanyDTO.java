package com.robinfood.storeor.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class CompanyDTO {

    String name;

    String identification;

    String identificationNumber;

    HeadquarterDTO headquarter;

    HoldingDTO holding;

    CountryResponseDTO country;
}
