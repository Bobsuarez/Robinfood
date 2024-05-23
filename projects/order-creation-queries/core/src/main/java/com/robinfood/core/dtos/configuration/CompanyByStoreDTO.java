package com.robinfood.core.dtos.configuration;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CompanyByStoreDTO {

    private final String identification;

    private final CountryByStoreDTO country;
}
