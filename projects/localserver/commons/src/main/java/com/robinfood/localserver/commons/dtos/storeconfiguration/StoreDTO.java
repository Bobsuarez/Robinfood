package com.robinfood.localserver.commons.dtos.storeconfiguration;

import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.TreasuryDepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StoreDTO {

    private String address;

    private CityDTO city;

    private List<CommandConfigurationDTO> commandConfiguration;

    private CountryDTO country;

    private String email;

    private String identification;

    private String internalName;

    private String location;

    private String name;

    private String phone;

    private StateDTO state;

    private String storeId;

    private String timezone;

    private TreasuryDepartmentDTO treasuryDepartment;

    private CompanyDTO company;

    private List<PosDTO> pos;
}
