package com.robinfood.storeor.usecase.createresolutions;

import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mocks.dto.resolutions.StoreResolutionsDTOMock;
import com.robinfood.storeor.usecase.createresolutions.createresolutionsconfigurationsbc
        .ICreateResolutionsConfigurationsUseCase;
import com.robinfood.storeor.usecase.createresolutions.createresolutionsordersposbc
        .ICreateResolutionsOrdersPosUseCase;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateResolutionsUseCaseTest {

    @Mock
    private ICreateResolutionsConfigurationsUseCase createResolutionsConfigurationsUseCase;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private ICreateResolutionsOrdersPosUseCase createResolutionsOrdersPosUseCase;

    @InjectMocks
    private CreateResolutionsUseCase createResolutionsUseCase;

    final StoreResolutionsDTO storeResolutionsDTOMock = new StoreResolutionsDTOMock().defaultData();

    List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOs = List.of(
            ResponseResolutionsWithPosDTO.builder()
                    .posId(1L)
                    .id(1L)
                    .build()
    );

    final String token = "token";

    @Test
    void test_CreateResolutionsUseCase_Invoke_Returns_Correctly() throws ResolutionCrudException {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(createResolutionsConfigurationsUseCase.invoke(storeResolutionsDTOMock, token)).thenReturn(
                responseResolutionsWithPosDTOs
        );

        List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOS = createResolutionsUseCase.invoke(
                storeResolutionsDTOMock
        );

        verify(getTokenBusinessCapabilityUseCase).invoke();
        verify(createResolutionsConfigurationsUseCase)
                .invoke(eq(storeResolutionsDTOMock), eq(token));
        verify(createResolutionsOrdersPosUseCase)
                .invoke(eq(responseResolutionsWithPosDTOs), eq(storeResolutionsDTOMock), eq(token));

        assertEquals(responseResolutionsWithPosDTOs.get(0), responseResolutionsWithPosDTOS.get(0));
    }

}
