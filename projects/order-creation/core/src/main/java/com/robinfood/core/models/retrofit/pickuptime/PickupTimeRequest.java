package com.robinfood.core.models.retrofit.pickuptime;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PickupTimeRequest {

    private Long flowId;

    private List<StoreRequest> stores;

    private Long time;

}
