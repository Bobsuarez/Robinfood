package com.robinfood.app.usecases.completeproducthalldata;

import com.robinfood.core.dtos.OrderDetailProductDTO;

import java.util.List;

/**
 * Use case that completes the order products data from menu hall products service
 */
public interface ICompleteMenuProductsDataFromMenuHallsUseCase {

    /**
     * completes the order products data from menu hall products service
     * @param countryId the country to consult
     * @param flowId the flow to consult
     * @param orderDetailProducts the products to set data
     * @param storeId the store to consult
     * @param token the authentication token
     */
    void invoke(
            Long countryId,
            Long flowId,
            List<OrderDetailProductDTO> orderDetailProducts,
            Long storeId,
            String token
    );
}
