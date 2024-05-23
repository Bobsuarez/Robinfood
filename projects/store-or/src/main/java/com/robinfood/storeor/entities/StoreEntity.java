package com.robinfood.storeor.entities;

import lombok.Data;

@Data
public class StoreEntity {

    private final String address;

    private  final CityEntity city;

    private  final CountryEntity country;

    private final String currencyType;

    private final String currencySymbol;

    private  final String email;

    private  final String identification;

    private  final String internalName;

    private  final String location;

    private final StoreMultiBrandEntity multiBrand;

    private  final String name;

    private  final String phone;

    private  final String timezone;

    private  final StateEntity state;

    private final Long id;

    private final ZoneEntity zone;

    private final StoreTypeEntity storeType;

    private final String uuid;

    private final StoreIdentifierTypeEntity storeIdentifierType;

    private final CompanyEntity company;

}
