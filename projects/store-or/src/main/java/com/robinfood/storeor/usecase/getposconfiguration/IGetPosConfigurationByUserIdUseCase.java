package com.robinfood.storeor.usecase.getposconfiguration;

import com.robinfood.storeor.dtos.user.PosConfigurationResponseDTO;

public interface IGetPosConfigurationByUserIdUseCase {

    PosConfigurationResponseDTO invoke(Long userId);
}
