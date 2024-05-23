package com.robinfood.storeor.usecase.activateordeactivateresolutions;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.storeor.usecase.activateordeactivateresolutions.statusresolutionsconfigurationsbc
        .IActivateOrDeactivateResolutionsConfigurationsUseCase;
import com.robinfood.storeor.usecase.activateordeactivateresolutions.statusresolutionsordersposbc
        .IActivateOrDeactivateResolutionsOrdersPosUseCase;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of IActivateOrDeactivateResolutionsUseCase
 */
@Slf4j
@Service
public class ActivateOrDeactivateResolutionsUseCase implements IActivateOrDeactivateResolutionsUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IActivateOrDeactivateResolutionsConfigurationsUseCase
            activateOrDeactivateResolutionsConfigurationsUseCase;
    private final IActivateOrDeactivateResolutionsOrdersPosUseCase activateOrDeactivateResolutionsOrdersPosUseCase;

    public ActivateOrDeactivateResolutionsUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IActivateOrDeactivateResolutionsConfigurationsUseCase activateOrDeactivateResolutionsConfigurationsUseCase,
            IActivateOrDeactivateResolutionsOrdersPosUseCase activateOrDeactivateResolutionsOrdersPosUseCase) {

        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.activateOrDeactivateResolutionsConfigurationsUseCase
                = activateOrDeactivateResolutionsConfigurationsUseCase;
        this.activateOrDeactivateResolutionsOrdersPosUseCase = activateOrDeactivateResolutionsOrdersPosUseCase;
    }

    @Override
    public void invoke(
            @NotNull ActivateOrDeactivateDTO activateOrDeactivateDTO,
            Long resolutionId)
            throws ResolutionCrudException {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("Start ActivateOrDeactivateResolutionsUseCase update status {} with Id {} ",
                activateOrDeactivateDTO, resolutionId);

        activateOrDeactivateResolutionsConfigurationsUseCase
                .invoke(activateOrDeactivateDTO, resolutionId, token.getAccessToken());

        activateOrDeactivateResolutionsOrdersPosUseCase
                .invoke(activateOrDeactivateDTO, resolutionId, token.getAccessToken());

        log.info("Finish ActivateOrDeactivateResolutionsUseCase update status with resolutionId {} ", resolutionId);
    }
}
