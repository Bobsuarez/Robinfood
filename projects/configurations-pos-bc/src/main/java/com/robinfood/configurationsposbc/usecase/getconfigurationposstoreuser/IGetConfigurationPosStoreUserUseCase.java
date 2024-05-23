package com.robinfood.configurationsposbc.usecase.getconfigurationposstoreuser;

import com.robinfood.configurationsposbc.dtos.pos.PosResponseDTO;

public interface IGetConfigurationPosStoreUserUseCase {

    PosResponseDTO invoke(Long storeId, Long userId);
}
