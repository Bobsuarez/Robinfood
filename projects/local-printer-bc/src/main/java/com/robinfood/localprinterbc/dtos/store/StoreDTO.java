package com.robinfood.localprinterbc.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StoreDTO {
    private Long id;
    private String name;
    private String location;
    private String phone;
    private String email;
    private String address;
    private CityDTO city;
    private CompanyDTO company;
    private PosDTO pos;
}
