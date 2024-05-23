package com.robinfood.storeor.usecase.activateordeactivatepos;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivatePosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivatePosEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;
import com.robinfood.storeor.mappers.IActiveOrDeactivatePosMapper;
import com.robinfood.storeor.mocks.dto.pos.ActivateOrDeactivatePosDTOMock;
import com.robinfood.storeor.mocks.entity.pos.ActivateOrDeactivatePosEntityMock;
import com.robinfood.storeor.repositories.posconfigurationsbcrepository.IPosConfigurationsBcRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivateOrDeactivatePosConfigurationsUseCaseTest {

    @Mock
    private IPosConfigurationsBcRepository posConfigurationsBcRepository;

    @Mock
    private IActiveOrDeactivatePosMapper activeOrDeactivatePosMapper;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private ActivateOrDeactivatePosConfigurationsUseCase activateOrDeactivatePosConfigurationsUseCase;

    final ActivateOrDeactivatePosDTO activateOrDeactivateDTO = new ActivateOrDeactivatePosDTOMock().defaultData();
    final ActivateOrDeactivatePosEntity activateOrDeactivateEntity = new ActivateOrDeactivatePosEntityMock()
            .defaultData();

    private final String token = "token";

    APIResponseEntity<Object> apiResponse = new APIResponseEntity<>(
            202,
            "{}",
            "2023-08-11T21:31:57.837443Z",
            "Resolutions updated successfully",
            "OK"
    );

    APIResponseEntity<Object> apiResponseException = new APIResponseEntity<>(
            500,
            null,
            "2023-08-11T21:31:57.837443Z",
            "Exception error in updated",
            "OK"
    );

    @Test
    void test_ActivateOrDeactivate_PosConfigurationsUseCase_Invoke_Returns_Correctly()
            throws PosException {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(posConfigurationsBcRepository.activateOrDeactivatePosConfigurations(
                activateOrDeactivateEntity, 1L, token))
                .thenReturn(apiResponse);

        when(activeOrDeactivatePosMapper.activeOrDeactivatePosDTOActiveOrDeactivateEntity(activateOrDeactivateDTO))
                .thenReturn(activateOrDeactivateEntity);

        activateOrDeactivatePosConfigurationsUseCase
                .invoke(activateOrDeactivateDTO, 1L);

        verify(activeOrDeactivatePosMapper).activeOrDeactivatePosDTOActiveOrDeactivateEntity(activateOrDeactivateDTO);
        verify(posConfigurationsBcRepository).activateOrDeactivatePosConfigurations(
                activateOrDeactivateEntity, 1L, token);

        ArgumentCaptor<ActivateOrDeactivatePosEntity> activateOrDeactivateEntityArgumentCaptor =
                ArgumentCaptor.forClass(ActivateOrDeactivatePosEntity.class);

        verify(posConfigurationsBcRepository).activateOrDeactivatePosConfigurations(
                activateOrDeactivateEntityArgumentCaptor.capture(),
                eq(1L),
                eq(token));
    }

    @Test
    void test_ActivateOrDeactivate_PosConfigurationsUseCase_Invoke_Returns_Exception() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(activeOrDeactivatePosMapper.activeOrDeactivatePosDTOActiveOrDeactivateEntity(activateOrDeactivateDTO))
                .thenReturn(activateOrDeactivateEntity);

        when(posConfigurationsBcRepository.activateOrDeactivatePosConfigurations(
                activateOrDeactivateEntity, 1L, token))
                .thenAnswer(invocation ->
                        apiResponseException
                );

        PosException exceptionResponse = assertThrows(PosException.class,
                () -> activateOrDeactivatePosConfigurationsUseCase
                        .invoke(activateOrDeactivateDTO, 1L));

        assertEquals("Pos could not be updated in configurations bc " + "Exception error in updated",
                exceptionResponse.getMessage());
    }
}
