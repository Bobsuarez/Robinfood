package com.robinfood.usecases.saveresolutionsbystore;

import com.robinfood.dtos.v1.request.StoreResolutionDTO;

public interface ISaveResolutionsByStoreUseCase {

    void invoke(StoreResolutionDTO storeResolutionDTO);
}
