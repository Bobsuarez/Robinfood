package com.robinfood.configurations.samples;

import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.Headquarter;
import com.robinfood.configurations.models.Holding;

public class HeadquarterSample {

    public static Headquarter getHeadquarter() {
        return Headquarter.builder()
            .id(1L)
            .company(Company.builder().id(1L).name("RobinFood")
                    .holding(Holding.builder().id(1L).identification("123").logo("qwerty").build())
                    .build())
            .address("Carrera 15 # 79 - 83")
            .cityName("Bogotá")
            .email("muy@muy.com.co")
            .phone("30112345678")
            .build();
    }
}
