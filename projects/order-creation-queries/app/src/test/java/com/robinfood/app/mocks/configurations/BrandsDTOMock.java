package com.robinfood.app.mocks.configurations;

import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.dtos.configuration.BrandsDTO;

import java.util.List;

public class BrandsDTOMock {

    public static BrandsDTO getDataDefault() {

        return BrandsDTO.builder()
                .content(List.of(BrandDTOMock.getDataDefault()))
                .build();
    }
}
