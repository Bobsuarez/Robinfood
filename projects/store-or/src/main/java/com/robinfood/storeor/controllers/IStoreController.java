package com.robinfood.storeor.controllers;

import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;
import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IStoreController {

    /**
     *
     * @param storeId
     * @param includePos Whether to include related pos information
     * @return configuration of store
     */
    ResponseEntity<APIResponseDTO<StoreResponseDTO>> getStore(@PathVariable("storeId") Long storeId,
                                                              Boolean includePos);

    /**
     *
     * @param storeId
     * @return configuration Pos by store
     */
    ResponseEntity<APIResponseDTO<List<StorePosDTO>>> getConfigurationPosByStore(Long storeId);

}
