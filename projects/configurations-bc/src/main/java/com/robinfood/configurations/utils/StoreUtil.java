package com.robinfood.configurations.utils;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.dto.v1.StoreDTO;
import com.robinfood.configurations.dto.v1.StoreTaxDTO;
import com.robinfood.configurations.dto.v1.models.CityDTO;
import com.robinfood.configurations.dto.v1.models.CompanyDTO;
import com.robinfood.configurations.dto.v1.models.CountryDTO;
import com.robinfood.configurations.dto.v1.models.StateDTO;
import com.robinfood.configurations.dto.v1.models.StoreIdentifierTypeDTO;
import com.robinfood.configurations.dto.v1.models.StoreTypeDTO;
import com.robinfood.configurations.dto.v1.models.ZoneDTO;
import com.robinfood.configurations.models.Store;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public final class StoreUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    private StoreUtil() {
    }

    @BasicLog
    public static StoreDTO buildStoreDTO(Store store) {
        log.info("Generating mappedStore for Store {}", JsonUtils.convertToJson(store));

        CountryDTO country = modelMapper.map(store.getCity().getState().getCountry(),
            CountryDTO.class);
        StateDTO state = modelMapper.map(store.getCity().getState(), StateDTO.class);

        StoreTaxDTO taxFLow = new StoreTaxDTO();

        CompanyDTO companyDTO = modelMapper.map(store.getCompany(), CompanyDTO.class);

        ZoneDTO zoneDTO = modelMapper.map(store.getZones(), ZoneDTO.class);

        StoreTypeDTO storeTypeDTO = modelMapper.map(store.getStoreType(), StoreTypeDTO.class);

        StoreIdentifierTypeDTO storeIdentifierTypeDTO = modelMapper.map(store.getStoreIdentifierType(),
            StoreIdentifierTypeDTO.class);

        CityDTO city = modelMapper.map(store.getCity(), CityDTO.class);
        return StoreDTO.builder()
            .id(store.getId())
            .name(store.getName())
            .internalName(store.getInternalName())
            .address(store.getAddress())
            .location(store.getLocation())
            .phone(store.getPhone())
            .email(store.getEmail())
            .flowTax(taxFLow)
            .internalName(store.getInternalName())
            .identification(store.getIdentifier())
            .timezone(store.getCity().getTimezone())
            .city(city)
            .state(state)
            .country(country)
            .company(companyDTO)
            .zoneId(zoneDTO)
            .storeTypeId(storeTypeDTO)
            .uuid(store.getUuid())
            .storeIdentifierTypeId(storeIdentifierTypeDTO)
            .currencySymbol(store.getCurrencySymbol())
            .currencyType(store.getCurrencyType())
            .taxRegime(store.getTaxRegime())
            .build();
    }

}
