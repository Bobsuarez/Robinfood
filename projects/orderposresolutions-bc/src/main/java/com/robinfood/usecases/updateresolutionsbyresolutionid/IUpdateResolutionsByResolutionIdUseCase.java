package com.robinfood.usecases.updateresolutionsbyresolutionid;

import com.robinfood.dtos.v1.request.ResolutionUpdateDTO;

public interface IUpdateResolutionsByResolutionIdUseCase {

    void invoke(Long resolutionId, ResolutionUpdateDTO resolution);
}
