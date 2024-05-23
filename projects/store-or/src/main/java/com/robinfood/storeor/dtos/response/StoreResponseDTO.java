package com.robinfood.storeor.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinfood.storeor.dtos.CommandConfiguration.CommandConfigurationResponseDTO;
import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class StoreResponseDTO {

    String address;

    CityResponseDTO city;

    List<CommandConfigurationResponseDTO> commandConfiguration;

    CountryResponseDTO country;

    String currencyType;

    String currencySymbol;

    String email;

    String identification;

    String internalName;

    String location;

    StoreMultiBrandDTO multiBrand;

    String name;

    String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<StorePosDTO> pos;

    String timezone;

    StateResponseDTO state;

    TreasureDepartmentsDTO treasuryDepartment;

    Long id;

    ZoneDTO zone;

    StoreTypeDTO storeType;

    String uuid;

    StoreIdentifierTypeDTO storeIdentifierType;

    CompanyDTO company;

}
