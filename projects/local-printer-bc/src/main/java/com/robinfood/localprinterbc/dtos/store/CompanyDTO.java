package com.robinfood.localprinterbc.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CompanyDTO {
    private String identification;
    private CountryDTO country;
    private String name;
    private HeadquarterDTO headquarter;
    private HoldingDTO holding;
}
