package com.robinfood.configurations.samples;

import com.robinfood.configurations.dto.v1.CreateStoreDTO;
import com.robinfood.configurations.dto.v1.UpdateStoreDTO;
import com.robinfood.configurations.models.City;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.Country;
import com.robinfood.configurations.models.State;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.StoreIdentifierType;
import com.robinfood.configurations.models.StoreType;
import com.robinfood.configurations.models.Zone;

import java.time.LocalDateTime;

public class StoreSample {

    private static final Long TEST_LONG = 1L;

    private static final LocalDateTime CURRENT_DATE = LocalDateTime.MIN;

    private static final String TEST = "TEST";


    public static Store getStore(){

        Store store = buildStore();
        store.setZones(buildZone());
        store.setStoreType(buildStoreType());
        store.setStoreIdentifierType(
                StoreIdentifierType.builder().id(1L).name("TEST").createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now()).build()
        );
        return store;
    }

    private static Country buildCountry() {
        return Country.builder()
                .id(TEST_LONG)
                .createdAt(CURRENT_DATE)
                .updatedAt(CURRENT_DATE)
                .deletedAt(null)
                .name(TEST)
                .build();
    }

    private static State buildState() {
        return State.builder()
                .id(TEST_LONG)
                .createdAt(CURRENT_DATE)
                .updatedAt(CURRENT_DATE)
                .deletedAt(null)
                .name(TEST)
                .country(buildCountry())
                .build();
    }

    private static City buildCity() {
        return City.builder()
                .id(TEST_LONG)
                .createdAt(CURRENT_DATE)
                .updatedAt(CURRENT_DATE)
                .deletedAt(null)
                .name(TEST)
                .timezone(TEST)
                .state(buildState())
                .build();
    }

    private static Store buildStore() {
        return Store.builder()
                .id(TEST_LONG)
                .createdAt(CURRENT_DATE)
                .updatedAt(CURRENT_DATE)
                .deletedAt(null)
                .name(TEST)
                .address(TEST)
                .location(TEST)
                .phone(TEST)
                .email(TEST)
                .internalName(TEST)
                .identifier(TEST)
                .city(buildCity())
                .company(Company.builder().id(1L).build())
                .storeType(new StoreType(1L))
                .zones(new Zone(1L))
                .storeIdentifierType(new StoreIdentifierType(1L))
                .build();
    }

    private static CreateStoreDTO buildCreateStoreDTO() {
        return CreateStoreDTO.builder()
                .name(TEST)
                .address(TEST)
                .location(TEST)
                .phone(TEST)
                .email(TEST)
                .internalName(TEST)
                .identifier(TEST)
                .cityId(1L)
                .companyCountryId(TEST_LONG)
                .storeTypeId(TEST_LONG)
                .zoneId(TEST_LONG)
                .internalName(TEST)
                .storeIdentifierTypeId(TEST_LONG)
                .build();
    }

    private static UpdateStoreDTO buildUpdateStoreDTO() {
        return UpdateStoreDTO.builder()
                .name(TEST)
                .address(TEST)
                .location(TEST)
                .phone(TEST)
                .email(TEST)
                .internalName(TEST)
                .identifier(TEST)
                .cityId(1L)
                .companyId(TEST_LONG)
                .storeTypeId(TEST_LONG)
                .zoneId(TEST_LONG)
                .internalName(TEST)
                .storeIdentifierTypeId(TEST_LONG)
                .build();
    }

    private static Zone buildZone(){
        return Zone.builder().id(1L)
                .createdAt(LocalDateTime.now())
                .deletedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .city(buildCity())
                .build();
    }

    private static StoreType buildStoreType(){
        return StoreType.builder().id(1l)
                .name("")
                .createdAt(LocalDateTime.now())
                .deletedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();
    }

}
