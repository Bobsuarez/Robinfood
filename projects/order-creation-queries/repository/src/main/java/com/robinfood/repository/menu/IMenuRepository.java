package com.robinfood.repository.menu;

import com.robinfood.core.dtos.MenuCurrentDTO;
import com.robinfood.core.dtos.menu.MenuHallProductResponseDTO;
import com.robinfood.core.dtos.menu.MenuSuggestedPortionResponseDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Repository for menu current related data
 */
public interface IMenuRepository {

    /**
     * @param brandId the brand to consult
     * @param countryId the country to consult
     * @param flowId the flow to consult
     * @param storeId the store to consult
     * @param token the authentication token
     * @return the menu current DTO
     */
    Result<MenuCurrentDTO> getMenuCurrent(
            Long brandId,
            Long countryId,
            Long flowId,
            Long storeId,
            String token
    );

    /**
     * Retrieves the product detail based on products identifiers given
     * @param token the authentication token to be used
     * @param countryId country identifier of the product
     * @param brandId brand identifier of the product
     * @param flowId flow identifier of the product
     * @param storeId store identifier of the product
     * @param productId the final product identifiers
     * @return the product with detailed info
     */
    Result<MenuHallProductResponseDTO> getProductDetail(
            Long brandId,
            Long countryId,
            Long flowId,
            Long productId,
            Long storeId,
            String token
    );

    /**
     * Retrieves the suggested portions detail based on portion identifiers given
     *
     * @param portionsIds the portions identifiers
     * @param token the authentication token to be used
     * @return the suggested portions with detailed info
     */
    Result<List<MenuSuggestedPortionResponseDTO>> getSuggestedPortions(
            List<Long> portionsIds,
            String token
    );
}
