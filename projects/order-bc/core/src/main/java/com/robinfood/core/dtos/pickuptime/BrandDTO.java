package com.robinfood.core.dtos.pickuptime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BrandDTO {

    private Long id;
    private Long printTime;

}
