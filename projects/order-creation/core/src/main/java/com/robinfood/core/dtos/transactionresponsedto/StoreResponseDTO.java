package com.robinfood.core.dtos.transactionresponsedto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StoreResponseDTO {

    private List<BrandResponseDTO> brands;

    private Long id;

    private LocalDateTime pickupTime;

}
