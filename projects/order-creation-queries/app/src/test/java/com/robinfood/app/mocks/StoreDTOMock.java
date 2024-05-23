package com.robinfood.app.mocks;

import com.robinfood.app.mocks.configurations.CompanyByStoreDTOMock;
import com.robinfood.core.dtos.configuration.StoreDTO;

public final class StoreDTOMock {

    public static StoreDTO getDataDefault() {

        return StoreDTO.builder()
                .id(1L)
                .company(CompanyByStoreDTOMock.getDataDefault())
                .name("RobinFood 27")
                .location("Colombia")
                .phone("300573927")
                .email("muy27@muy.com")
                .address("Calle 83")
                .internalName("robinFood-27")
                .identification("63946392")
                .timezone("America/Bogota")
                .uuid("06d41d70-beba-4d1a-a3c0-ce0c6a90ccc1")
                .currencyType("COL")
                .currencySymbol("$")
                .taxRegime("Person")
                .multiBrand(StoreMultiBrandDTOMock.getDataDefault())
                .build();
    }
}
