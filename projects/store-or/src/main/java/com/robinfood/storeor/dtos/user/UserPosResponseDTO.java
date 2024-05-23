package com.robinfood.storeor.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPosResponseDTO {

    private Long id;
    private String name;
    private Long countryId;
    private String currency;
    private Boolean isDelivery;
    private Integer flowId;
    private Boolean isMultiBrand;
}
