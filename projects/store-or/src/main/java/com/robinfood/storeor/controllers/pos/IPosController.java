package com.robinfood.storeor.controllers.pos;

import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.configurationpos.StoreCreatePosDTO;
import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivatePosDTO;
import com.robinfood.storeor.dtos.listposresponse.PosListResponseDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IPosController {

    /**
     * Create Pos by store
     *
     * @param storeCreatePosDTO contain the stored Pos provider response information
     * @return basic information on the creation of the pos
     */
    ResponseEntity<APIResponseDTO<StoreCreatePosDTO>> createStorePos(
            HttpServletRequest httpServletRequest,
            StoreCreatePosDTO storeCreatePosDTO
    );

    /**
     * Update pos by id
     *
     * @param posDTO contain the stored resolution provider response information
     * @return basic information on the updated of the resolutions
     */
    ResponseEntity<APIResponseDTO<String>> updatePos(
            PosDTO posDTO,
            Long posId
    ) throws PosException;

    /**
     * Active or Deactivate pos by store
     *
     * @param activateOrDeactivatePosDTO contain request pos status provider response information
     * @return basic information on the updated status (active or deactivate) of the pos
     */
    ResponseEntity<APIResponseDTO<String>> activateOrDeactivate(
            HttpServletRequest httpServletRequest,
            ActivateOrDeactivatePosDTO activateOrDeactivatePosDTO,
            Long posId
    ) throws PosException;

    ResponseEntity<APIResponseDTO<Page<PosListResponseDTO>>> getPosList(
            Integer page, String posName, Integer size,
            Boolean status, Long storeId, Sort sort

    );
}
