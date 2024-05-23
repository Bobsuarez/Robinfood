package com.robinfood.configurations.samples;

import com.robinfood.configurations.models.BrandCompanyChannel;
import com.robinfood.configurations.models.Company;

public class CompanySample {

    public static Company getCompany() {
        return Company.builder()
                .id(1L)
                .country(CountrySample.getCountry())
                .name("")
                .identification("")
                .currencyType("")
                .currencySymbol("")
                .build();
    }
}
