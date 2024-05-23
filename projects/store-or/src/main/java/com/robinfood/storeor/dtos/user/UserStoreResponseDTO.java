package com.robinfood.storeor.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserStoreResponseDTO {

    private String address;
    private String city;
    private String country;
    private Long id;
    private String internalName;
    private String name;
    private String timeZone;
    private String uuid;
}
