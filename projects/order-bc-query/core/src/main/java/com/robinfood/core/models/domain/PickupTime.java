package com.robinfood.core.models.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PickupTime {

    private Long id;

    private List<Store> stores;

    private Long transactionId;

}
