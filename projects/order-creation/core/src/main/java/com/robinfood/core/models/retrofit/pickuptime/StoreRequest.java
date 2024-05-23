package com.robinfood.core.models.retrofit.pickuptime;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreRequest {

    private List<BrandRequest> brands;

    private Long id;

}
