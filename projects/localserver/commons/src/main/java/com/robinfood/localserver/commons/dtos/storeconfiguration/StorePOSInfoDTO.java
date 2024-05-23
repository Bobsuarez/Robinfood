package com.robinfood.localserver.commons.dtos.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StorePOSInfoDTO {

    private Long id;// storeID

    private String name;

    private String location;

    private String phone;

    private String email;

    private String address;

    private CityDTO city;

    private CompanyDTO company;

    private PosDTO pos;
}
