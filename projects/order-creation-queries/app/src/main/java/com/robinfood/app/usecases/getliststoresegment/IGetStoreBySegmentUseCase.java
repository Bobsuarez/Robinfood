package com.robinfood.app.usecases.getliststoresegment;

import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.dtos.report.salebysegment.StoreSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.StoresDTOResponse;

import java.util.List;

/**
 * Use case that returns the list of store by app
 */
public interface IGetStoreBySegmentUseCase {

    /**
     * retrieve the stores according to the entered criteria
     *
     * @return object with the data
     */
    StoresDTOResponse invoke(
            String currencyType,
            List<StoreSegmentDTO> storeSegmentDTOS,
            List<StoreWithIdAndNameDTO> storeDTOList
    );
}
