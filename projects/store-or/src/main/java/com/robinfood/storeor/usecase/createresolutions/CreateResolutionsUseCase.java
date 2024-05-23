package com.robinfood.storeor.usecase.createresolutions;

import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.storeor.usecase.createresolutions.createresolutionsconfigurationsbc
        .ICreateResolutionsConfigurationsUseCase;
import com.robinfood.storeor.usecase.createresolutions.createresolutionsordersposbc.ICreateResolutionsOrdersPosUseCase;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.LOG_REQUEST_CREATE_RESOLUTIONS;

/**
 * Implementation of ICreateResolutionsUseCase
 */
@Slf4j
@Service
public class CreateResolutionsUseCase implements ICreateResolutionsUseCase {

    private final ICreateResolutionsConfigurationsUseCase createResolutionsConfigurationsUseCase;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final ICreateResolutionsOrdersPosUseCase createResolutionsOrdersPosUseCase;

    public CreateResolutionsUseCase(
            ICreateResolutionsConfigurationsUseCase createResolutionsConfigurationsUseCase,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            ICreateResolutionsOrdersPosUseCase createResolutionsOrdersPosUseCase) {
        this.createResolutionsConfigurationsUseCase = createResolutionsConfigurationsUseCase;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.createResolutionsOrdersPosUseCase = createResolutionsOrdersPosUseCase;
    }

    @Override
    public List<ResponseResolutionsWithPosDTO> invoke(@NotNull StoreResolutionsDTO storeResolutions)
            throws ResolutionCrudException {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        log.info(LOG_REQUEST_CREATE_RESOLUTIONS, storeResolutions);

        List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOS = createResolutionsConfigurationsUseCase
                .invoke(storeResolutions, token.getAccessToken());

        createResolutionsOrdersPosUseCase
                .invoke(responseResolutionsWithPosDTOS, storeResolutions, token.getAccessToken());

        return responseResolutionsWithPosDTOS;

    }
}
