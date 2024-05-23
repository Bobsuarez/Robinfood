package com.robinfood.core.dtos.transactionresponsedto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BrandResponseDTO {

    private Long id;

    private LocalDateTime printTime;

}
