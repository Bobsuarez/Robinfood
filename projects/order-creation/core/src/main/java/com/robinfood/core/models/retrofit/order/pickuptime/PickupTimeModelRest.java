package com.robinfood.core.models.retrofit.order.pickuptime;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PickupTimeModelRest {

    private List<StoreModelRest> stores;

    private Long transactionId;

}
