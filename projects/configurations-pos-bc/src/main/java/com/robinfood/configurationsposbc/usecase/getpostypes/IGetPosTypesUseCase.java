package com.robinfood.configurationsposbc.usecase.getpostypes;

import com.robinfood.configurationsposbc.dtos.pos.PosTypesDTO;

public interface IGetPosTypesUseCase {

    PosTypesDTO invoke(Long posTypeId);
}
