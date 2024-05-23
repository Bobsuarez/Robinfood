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
public class StoreDTO {

    private List<BrandDTO> brands;

    private Long id;

    private Long pickupTime;

}
