package com.robinfood.storeor.usecase.activateordeactivateresolutions.statusresolutionsconfigurationsbc;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IActiveResolutionMapper;
import com.robinfood.storeor.mocks.dto.resolutions.ActivateOrDeactivateDTOMock;
import com.robinfood.storeor.mocks.entity.resolutions.ActivateOrDeactivateEntityMock;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsConfigurationsRepository;
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
public class ActivateOrDeactivateResolutionsConfigurationsUseCaseTest {

    @Mock
    private IResolutionsConfigurationsRepository resolutionsRepository;

    @Mock
    private IActiveResolutionMapper activeResolutionMapper;

    @InjectMocks
    private ActivateOrDeactivateResolutionsConfigurationsUseCase activateOrDeactivateResolutionsConfigurationsUseCase;

    final ActivateOrDeactivateDTO activateOrDeactivateDTO = new ActivateOrDeactivateDTOMock().defaultData();
    final ActivateOrDeactivateEntity activateOrDeactivateEntity = new ActivateOrDeactivateEntityMock().defaultData();

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
    void test_ActivateOrDeactivate_ResolutionsConfigurationsUseCase_Invoke_Returns_Correctly()
            throws ResolutionCrudException {

        when(resolutionsRepository.activateOrDeactivate(activateOrDeactivateEntity, 1L, token))
                .thenReturn(apiResponse);

        when(activeResolutionMapper.activeResolutionDTOActiveResolutionEntity(activateOrDeactivateDTO))
                .thenReturn(activateOrDeactivateEntity);

        activateOrDeactivateResolutionsConfigurationsUseCase
                .invoke(activateOrDeactivateDTO, 1L, token);

        verify(activeResolutionMapper).activeResolutionDTOActiveResolutionEntity(activateOrDeactivateDTO);
        verify(resolutionsRepository).activateOrDeactivate(activateOrDeactivateEntity, 1L, token);

        ArgumentCaptor<ActivateOrDeactivateEntity> activateOrDeactivateEntityArgumentCaptor =
                ArgumentCaptor.forClass(ActivateOrDeactivateEntity.class);
        verify(resolutionsRepository).activateOrDeactivate(activateOrDeactivateEntityArgumentCaptor.capture(),
                eq(1L),
                eq(token));
    }

    @Test
    void test_ActivateOrDeactivate_ResolutionsConfigurationsUseCase_Invoke_Returns_Exception() {

        when(activeResolutionMapper.activeResolutionDTOActiveResolutionEntity(activateOrDeactivateDTO))
                .thenReturn(activateOrDeactivateEntity);

        when(resolutionsRepository.activateOrDeactivate(activateOrDeactivateEntity, 1L, token))
                .thenAnswer(invocation ->
                        apiResponseException
                );

        ResolutionCrudException exceptionResponse = assertThrows(ResolutionCrudException.class,
                () -> activateOrDeactivateResolutionsConfigurationsUseCase
                        .invoke(activateOrDeactivateDTO, 1L, token));

        assertEquals("Resolutions could not be updated in configurations bc " + "Exception error in updated",
                exceptionResponse.getMessage());
    }
}
