package com.robinfood.usecases.enabledisableresolution;

import com.robinfood.dtos.v1.request.EnabledDisabledResolutionDTO;

public interface IEnabledDisabledResolutionUseCase {

    String invoke(EnabledDisabledResolutionDTO alternateResolutionDTO, String resolutionIdPath);
}
