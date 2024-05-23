package com.robinfood.core.dtos.configuration;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreDTO {

    private final String address;

    private final CompanyByStoreDTO company;

    private String currencySymbol;

    private String currencyType;

    private final String email;

    private final Long id;

    private final String identification;

    private final String internalName;

    private final String location;

    private StoreMultiBrandDTO multiBrand;

    private final String name;

    private final String phone;

    private String taxRegime;

    private final String timezone;

    private final String uuid;
}
