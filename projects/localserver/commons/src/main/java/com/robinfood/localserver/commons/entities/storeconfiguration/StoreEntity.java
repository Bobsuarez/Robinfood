package com.robinfood.localserver.commons.entities.storeconfiguration;

import com.robinfood.localserver.commons.entities.storeconfiguration.treasurydepartment.TreasuryDepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Document("local_configuration")
public class StoreEntity {

    private String address;

    private CityEntity city;

    List<CommandConfigurationEntity> commandConfiguration;

    private CountryEntity country;

    private String email;

    private String identification;

    private String internalName;

    private String location;

    private String name;

    private String phone;

    private StateEntity state;

    @Id
    private String storeId;

    private String timezone;

    private TreasuryDepartmentEntity treasuryDepartment;

    private CompanyEntity company;

    private List<PosEntity> pos;

}
