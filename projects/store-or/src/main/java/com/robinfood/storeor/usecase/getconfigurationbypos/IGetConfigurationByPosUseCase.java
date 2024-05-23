package com.robinfood.storeor.usecase.getconfigurationbypos;

import com.robinfood.storeor.dtos.response.PosResponseDTO;

public interface IGetConfigurationByPosUseCase {

    PosResponseDTO invoke(Long storeId, Long userId);
}
