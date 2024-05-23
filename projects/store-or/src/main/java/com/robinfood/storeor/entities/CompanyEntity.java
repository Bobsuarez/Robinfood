package com.robinfood.storeor.entities;

import lombok.Data;

@Data
public class CompanyEntity {

    private String name;

    private String identification;

    private String identificationNumber;

    private HeadquarterEntity headquarter;

    private HoldingEntity holding;

    private CountryEntity country;
}
