package com.robinfood.core.dtos.configuration;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CompanyDTO {

    private CountryDTO country;

    private final String currency_symbol;

    private final String currency_type;

    private final Long id;

    private final String identification;

    private final String name;
}
