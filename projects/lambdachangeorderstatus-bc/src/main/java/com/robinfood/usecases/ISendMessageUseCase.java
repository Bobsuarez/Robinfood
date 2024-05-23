package com.robinfood.usecases;

import com.robinfood.dtos.request.RequestChangeStateDTO;

public interface ISendMessageUseCase {

    void invoke(RequestChangeStateDTO requestDTO, Long storeId);

}
