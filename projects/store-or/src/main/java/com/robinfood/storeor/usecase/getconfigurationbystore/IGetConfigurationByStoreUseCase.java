package com.robinfood.storeor.usecase.getconfigurationbystore;

import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface IGetConfigurationByStoreUseCase {

    /**
     * use case that consolidates billing-bc and configuration-bc responses for a store
     *
     * @param storeId Identifier of the store
     * @param includePos Whether to include related pos information
     * @return store configuration
     */
    StoreResponseDTO invoke(Long storeId, Boolean includePos);

    /**
     * use case that consolidates configuration-bc responses for a store list
     *
     * @param name name of the store
     * @param companyCountryId company country associate to store
     * @param page number of page
     * @param size size of store list
     * @param sort sort store list
     * @return store configuration
     */
    Page<StoreResponseDTO> invoke(String name, Long companyCountryId, Integer page, Integer size, Sort sort);
}
