package com.robinfood.storeor.usecase.createelectronicbilling;

import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import org.jetbrains.annotations.NotNull;

/**
 * Use case that create an electronic billing response
 */

public interface ICreateElectronicBillingUseCase {

    /**
     * @param createElectronicBillingRequestDTO contain the stored billing provider response information
     * @return object with the basic information of the creation of an electronic billing response
     */
    CreateElectronicBillingResponseDTO invoke(
            @NotNull CreateElectronicBillingRequestDTO createElectronicBillingRequestDTO
    );
}
