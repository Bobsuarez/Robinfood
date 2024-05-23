package com.robinfood.storeor.dtos.electronicbilling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateElectronicBillingResponseDTO {

    private LocalDateTime createdAt;

    private String id;

    private String message;
}
