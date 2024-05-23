package com.robinfood.localserver.commons.entities.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class HeadquarterEntity {
    private String address;
    private String email;
    private  Long id;
    private String phone;
}
