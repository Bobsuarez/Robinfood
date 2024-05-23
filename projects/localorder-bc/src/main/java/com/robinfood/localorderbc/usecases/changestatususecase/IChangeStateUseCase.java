package com.robinfood.localorderbc.usecases.changestatususecase;

import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;

public interface IChangeStateUseCase {

    ChangeStateDTO invoke(ChangeStateDTO changeStateDTO);

}
