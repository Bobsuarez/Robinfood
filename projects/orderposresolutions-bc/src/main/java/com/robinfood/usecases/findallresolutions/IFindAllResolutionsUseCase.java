package com.robinfood.usecases.findallresolutions;

import com.robinfood.dtos.v1.request.SearchResolutionsDTO;
import com.robinfood.dtos.v1.response.searchResolutions.DataResolutionResponseDTO;

public interface IFindAllResolutionsUseCase {

    DataResolutionResponseDTO invoke(SearchResolutionsDTO searchResolutions);
}
