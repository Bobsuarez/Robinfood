package com.robinfood.storeor.usecase.updateresolutions;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.storeor.usecase.updateresolutions.updateresolutionsconfigurationsbc
        .IUpdateResolutionsConfigurationsUseCase;
import com.robinfood.storeor.usecase.updateresolutions.updateresolutionsordersposbc
        .IUpdateResolutionsOrdersPosUseCase;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of IUpdateResolutionsUseCase
 */
@Slf4j
@Service
public class UpdateResolutionsUseCase implements IUpdateResolutionsUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IUpdateResolutionsConfigurationsUseCase updateResolutionsConfigurationsUseCase;
    private final IUpdateResolutionsOrdersPosUseCase updateResolutionsOrdersPosUseCase;

    public UpdateResolutionsUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IUpdateResolutionsConfigurationsUseCase updateResolutionsConfigurationsUseCase,
            IUpdateResolutionsOrdersPosUseCase updateResolutionsOrdersPosUseCase) {
        this.updateResolutionsConfigurationsUseCase = updateResolutionsConfigurationsUseCase;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.updateResolutionsOrdersPosUseCase = updateResolutionsOrdersPosUseCase;
    }

    @Override
    public void invoke(
            @NotNull ResolutionDTO resolutionsDTO,
            Long resolutionId)
            throws ResolutionCrudException {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("Start UpdateResolutionsConfigurationsUseCase update resolution {} with Id {} ",
                resolutionsDTO, resolutionId);

        updateResolutionsConfigurationsUseCase.invoke(resolutionsDTO, resolutionId, token.getAccessToken());

        updateResolutionsOrdersPosUseCase.invoke(resolutionsDTO, resolutionId, token.getAccessToken());

        log.info("Finish UpdateResolutionsConfigurationsUseCase update resolution with resolutionId {} ", resolutionId);
    }
}
