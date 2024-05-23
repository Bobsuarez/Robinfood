package com.robinfood.localserver.commons.entities.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Document("local_configuration")
public class StorePOSInfoEntity {

    private Long id;// storeID

    private String name;

    private String location;

    private String phone;

    private String email;

    private String address;

    private CityEntity city;

    private CompanyEntity company;

    private PosEntity pos;
}
