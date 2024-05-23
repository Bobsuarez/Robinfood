package com.robinfood.repository.mocks.pickuptime;

import com.robinfood.core.models.retrofit.pickuptime.PickupTimeRequest;

public class PickupTimeRequestMock {

    private static final Long TIME = 43252L;
    private static final Long FLOW_ID = 12L;

    public static PickupTimeRequest build() {
        return PickupTimeRequest.builder()
            .time(TIME)
            .flowId(FLOW_ID)
            .build();
    }

}
