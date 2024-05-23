package com.robinfood.core.entities.configurations;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreEntity {

    private final String address;

    private String currencySymbol;

    private String currencyType;

    private final String email;

    private final Long id;

    private final String identification;

    private final String internalName;

    private final String location;

    private StoreMultiBrandEntity multiBrand;

    private final String name;

    private final String phone;

    private String taxRegime;

    private final String timezone;

    private final String uuid;
}
