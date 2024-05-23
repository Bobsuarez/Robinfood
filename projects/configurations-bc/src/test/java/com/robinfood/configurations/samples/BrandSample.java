package com.robinfood.configurations.samples;

import com.robinfood.configurations.models.BrandCompanyChannel;

public class BrandSample {

    public static BrandCompanyChannel getBrandCompanyCountry() {
        return BrandCompanyChannel.builder()
            .id(1L)
            .icon("MuyEpaLaArepa")
            .banner("MuyEpaLaArepa.png")
            .build();
    }
}
