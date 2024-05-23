package com.robinfood.configurationsposbc.usecase.getpos;

import com.robinfood.configurationsposbc.dtos.pos.PosDTO;

public interface IGetPosUseCase {

    PosDTO invoke(Long posId);
}
