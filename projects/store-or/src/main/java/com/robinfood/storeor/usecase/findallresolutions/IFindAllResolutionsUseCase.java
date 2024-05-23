package com.robinfood.storeor.usecase.findallresolutions;

import com.robinfood.storeor.dtos.findAllResolutions.SearchResolutionDTO;
import com.robinfood.storeor.dtos.response.DataResolutionResponseDTO;


public interface IFindAllResolutionsUseCase {

    DataResolutionResponseDTO invoke(SearchResolutionDTO searchResolutionDTO);
}
