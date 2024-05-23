package com.robinfood.app.usecases.completeorderproductsdata;

import com.robinfood.core.dtos.OrderDetailProductDTO;

import java.util.List;

/**
 * Use case that completes the order products data from menu current service
 */
public interface ICompleteOrderProductsDataFromMenuCurrentUseCase {

    /**
     * completes the order products data from menu current service
     * @param countryId the country to consult
     * @param flowId the flow to consult
     * @param orderDetailProductDTOList the order products to consult
     * @param storeId the store to consult
     * @param token the authentication token
     */
    void invoke(
            Long countryId,
            Long flowId,
            List<OrderDetailProductDTO> orderDetailProductDTOList,
            Long storeId,
            String token
    );
}
