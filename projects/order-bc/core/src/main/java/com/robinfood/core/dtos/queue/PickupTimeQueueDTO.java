package com.robinfood.core.dtos.queue;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PickupTimeQueueDTO implements Serializable {

    private static final long serialVersionUID = -7376980265909798519L;

    private Long storeId;

    private Long pickupTime;

}
