package com.robinfood.storeor.usecase.getstorebyidusecase;

import com.robinfood.storeor.dtos.UserStoreConfigurationsResponseDTO;
import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * The interface that you must implement to detail of a store
 */
public interface IGetStoreByIdUseCase {

    /**
     * Executes the corresponding process to obtain the detail of an order
     *
     * @param storeId Identifier of the store
     * @return StoreResponseDTO The result of the specified store
     */
    StoreResponseDTO invoke(Long storeId, String token);

    /**
     *  Executes the corresponding process to obtain the store list
     *
     * @param name name of the store
     * @param companyCountryId company country associate to store
     * @param page number of page
     * @param size size of store list
     * @param sort sort store list
     * @return StoreResponseDTO The result of the specified store list
     */
    Page<StoreResponseDTO> invoke(String name, Long companyCountryId, Integer page, Integer size,
        Sort sort, String accessToken);

    UserStoreConfigurationsResponseDTO findStoreByUserId(Long userId, String token);
}
