package com.robinfood.app.usecases.getlistbrandsegment;

import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.BrandsDTOResponse;

import java.util.List;

/**
 * Use case that returns the list of brands by app
 */
public interface IGetBrandBySegmentUseCase {

    /**
     * retrieve the stores according to the entered criteria
     *
     * @return object with the data
     */
    BrandsDTOResponse invoke(String currencyType, List<BrandSegmentDTO> brands, List<BrandDTO> brandDTOList);
}
