package com.robinfood.storeor.usecase.updateresolutions;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mocks.dto.resolutions.ResolutionDTOMock;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.storeor.usecase.updateresolutions.updateresolutionsconfigurationsbc
        .IUpdateResolutionsConfigurationsUseCase;
import com.robinfood.storeor.usecase.updateresolutions.updateresolutionsordersposbc.IUpdateResolutionsOrdersPosUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateResolutionsUseCaseTest {

    @Mock
    private IUpdateResolutionsConfigurationsUseCase updateResolutionsConfigurationsUseCase;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IUpdateResolutionsOrdersPosUseCase updateResolutionsOrdersPosUseCase;

    @InjectMocks
    private UpdateResolutionsUseCase updateResolutionsUseCase;

    final ResolutionDTO resolutionDTOMock = new ResolutionDTOMock().defaultData();

    final String token = "token";

    @Test
    void test_UpdateResolutionsUseCase_Invoke_Returns_Correctly() throws ResolutionCrudException {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        updateResolutionsUseCase.invoke(resolutionDTOMock, 1L);

        verify(getTokenBusinessCapabilityUseCase).invoke();
        verify(updateResolutionsConfigurationsUseCase)
                .invoke(eq(resolutionDTOMock), eq(1L), eq(token));
        verify(updateResolutionsOrdersPosUseCase)
                .invoke(eq(resolutionDTOMock), eq(1L), eq(token));

    }
}
