package com.robinfood.localserver.commons.dtos.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CompanyDTO {

    private CountryDTO country;

    private HeadquarterDTO headquarter;

    private HoldDTO holding;

    private String identification;

    private String name;
}
