package com.robinfood.usecases.savethirdparty;

import com.robinfood.dtos.sendordertosimba.request.ThirdPartyDTO;

public interface ISaveThirdPartyUseCase {

    void invoke(Long orderId, ThirdPartyDTO thirdPartyDTO);
}
