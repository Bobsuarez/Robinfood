package com.robinfood.storeor.usecase.activateordeactivateresolutions;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mocks.dto.resolutions.ActivateOrDeactivateDTOMock;
import com.robinfood.storeor.usecase.activateordeactivateresolutions.statusresolutionsconfigurationsbc
        .ActivateOrDeactivateResolutionsConfigurationsUseCase;
import com.robinfood.storeor.usecase.activateordeactivateresolutions.statusresolutionsordersposbc
        .IActivateOrDeactivateResolutionsOrdersPosUseCase;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivateOrDeactivateResolutionsUseCaseTest {

    @Mock
    private ActivateOrDeactivateResolutionsConfigurationsUseCase activateOrDeactivateResolutionsConfigurationsUseCase;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IActivateOrDeactivateResolutionsOrdersPosUseCase activateOrDeactivateResolutionsOrdersPosUseCase;

    @InjectMocks
    private ActivateOrDeactivateResolutionsUseCase activateOrDeactivateResolutionsUseCase;

    final ActivateOrDeactivateDTO activateOrDeactivateDTO = new ActivateOrDeactivateDTOMock().defaultData();

    final String token = "token";

    @Test
    void test_UpdateResolutionsUseCase_Invoke_Returns_Correctly() throws ResolutionCrudException {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        activateOrDeactivateResolutionsUseCase.invoke(activateOrDeactivateDTO, 1L);

        verify(getTokenBusinessCapabilityUseCase).invoke();
        verify(activateOrDeactivateResolutionsConfigurationsUseCase)
                .invoke(eq(activateOrDeactivateDTO), eq(1L), eq(token));
        verify(activateOrDeactivateResolutionsOrdersPosUseCase)
                .invoke(eq(activateOrDeactivateDTO), eq(1L), eq(token));

    }
}
