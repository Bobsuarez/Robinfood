package com.robinfood.core.dtos.transactionresponsedto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PickupTimeResponseDTO {

    private List<StoreResponseDTO> stores;

}
