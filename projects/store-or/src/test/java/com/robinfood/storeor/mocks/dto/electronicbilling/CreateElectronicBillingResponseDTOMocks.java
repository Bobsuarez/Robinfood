package com.robinfood.storeor.mocks.dto.electronicbilling;

import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import java.time.LocalDateTime;

public class CreateElectronicBillingResponseDTOMocks {

    public final CreateElectronicBillingResponseDTO createElectronicBillingResponseDTO =
        new CreateElectronicBillingResponseDTO(
            LocalDateTime.now(),
            "1",
            "created order electronic billing"
    );
}
