package com.robinfood.core.models.retrofit.pickuptime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BrandResponse {

    private Long id;
    private Long printTime;

}
