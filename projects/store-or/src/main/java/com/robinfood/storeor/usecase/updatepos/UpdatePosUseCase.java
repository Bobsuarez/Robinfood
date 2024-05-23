package com.robinfood.storeor.usecase.updatepos;

import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.storeor.usecase.updateconfigurationbypos.IUpdatePosConfigurationsUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of IUpdatePosUseCase
 */
@Slf4j
@Service
public class UpdatePosUseCase implements IUpdatePosUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IUpdatePosConfigurationsUseCase updatePosConfigurationsUseCase;

    public UpdatePosUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IUpdatePosConfigurationsUseCase updatePosConfigurationsUseCase
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.updatePosConfigurationsUseCase = updatePosConfigurationsUseCase;
    }

    @Override
    public void invoke(Long posId, PosDTO posDTO) throws PosException {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        updatePosConfigurationsUseCase.invoke(posId, posDTO, token.getAccessToken());
    }
}
