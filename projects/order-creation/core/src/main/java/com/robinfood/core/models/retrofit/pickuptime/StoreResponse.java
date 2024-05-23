package com.robinfood.core.models.retrofit.pickuptime;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StoreResponse {

    private List<BrandResponse> brands;

    private Long id;

    private Long pickupTime;

}
