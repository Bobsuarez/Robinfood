package com.robinfood.app.usecases.getmenuproducts;

import com.robinfood.core.dtos.menu.MenuHallProductResponseDTO;

import java.util.List;

/**
 * Use case get the menu products data from menu service
 */
public interface IGetMenuProductsUseCase {

    /**
     * Retrieves a list with the menu products details based on products identifiers given
     * @param brandId the brand to consult
     * @param countryId the country to consult
     * @param flowId the flow to consult
     * @param storeId the store to consult
     * @param token the authentication token
     * @return the menu current
     */
    List<MenuHallProductResponseDTO> invoke(
            Long brandId,
            Long countryId,
            Long flowId,
            List<Long> productsIds,
            Long storeId,
            String token
    );
}
