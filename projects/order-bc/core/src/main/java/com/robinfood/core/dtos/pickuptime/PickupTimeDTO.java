package com.robinfood.core.dtos.pickuptime;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PickupTimeDTO {

    private List<StoreDTO> stores;

    private Long transactionId;

}
