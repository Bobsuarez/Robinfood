package com.robinfood.localserver.commons.dtos.storeconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class HeadquarterDTO {

    private String address;

    private String email;

    private Long id;

    private String phone;
}
