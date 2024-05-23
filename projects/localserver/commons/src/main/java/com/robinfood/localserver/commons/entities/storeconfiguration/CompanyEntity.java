package com.robinfood.localserver.commons.entities.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CompanyEntity {
    private CountryEntity country;
    private HeadquarterEntity headquarter;
    private HoldEntity holding;
    private String identification;
    private String name;
}
