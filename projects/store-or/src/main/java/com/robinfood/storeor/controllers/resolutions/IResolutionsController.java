package com.robinfood.storeor.controllers.resolutions;

import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.DataResolutionResponseDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface IResolutionsController {

    /**
     * Create resolutions by store
     *
     * @param storeResolutionsDTO contain the stored resoltions provider response information
     * @return basic information on the creation of the resolutions
     */
    ResponseEntity<APIResponseDTO<List<ResponseResolutionsWithPosDTO>>> createStoreResolutions(
            HttpServletRequest httpServletRequest,
            StoreResolutionsDTO storeResolutionsDTO
    ) throws ResolutionCrudException;

    /**
     * Update resolutions by store
     *
     * @param resolutionDTO contain the stored resolution provider response information
     * @return basic information on the updated of the resolutions
     */
    ResponseEntity<APIResponseDTO<ResolutionDTO>> updateStoreResolutions(
            HttpServletRequest httpServletRequest,
            ResolutionDTO resolutionDTO,
            Long resolutionId
    ) throws ResolutionCrudException;

    /**
     * Active or Deactivate resolutions by store
     *
     * @param activateOrDeactivateDTO contain request resolution status provider response information
     * @return basic information on the updated status (active or deactivate) of the resolutions
     */
    ResponseEntity<APIResponseDTO<ResolutionDTO>> activateOrDeactivate(
            HttpServletRequest httpServletRequest,
            ActivateOrDeactivateDTO activateOrDeactivateDTO,
            Long resolutionId
    ) throws ResolutionCrudException;

    /**
     * find all resolutions
     *
     * @param searchResolutionDTO contains the input parameters
     *
     * @return return list Resolutions
     * @throws ResolutionCrudException
     */
    ResponseEntity<APIResponseDTO<DataResolutionResponseDTO>> findAllResolutions(
            Integer page, Integer size, Boolean status, String valueCustomFilter,
            String orderByEndDateResolution, Boolean withPos, Long resolutionId)throws ResolutionCrudException;
}
