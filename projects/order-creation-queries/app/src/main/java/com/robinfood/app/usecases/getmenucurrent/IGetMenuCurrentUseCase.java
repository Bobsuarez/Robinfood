package com.robinfood.app.usecases.getmenucurrent;

import com.robinfood.core.dtos.MenuCurrentDTO;

/**
 * Use case get menu current
 */
public interface IGetMenuCurrentUseCase {

    /**
     * Retrieves the menu with the following query parameters
     * @param brandId the brand to consult
     * @param countryId the country to consult
     * @param flowId the flow to consult
     * @param storeId the store to consult
     * @param token the authentication token
     * @return the menu current
     */
    MenuCurrentDTO invoke(
            Long brandId,
            Long countryId,
            Long flowId,
            Long storeId,
            String token
    );
}
