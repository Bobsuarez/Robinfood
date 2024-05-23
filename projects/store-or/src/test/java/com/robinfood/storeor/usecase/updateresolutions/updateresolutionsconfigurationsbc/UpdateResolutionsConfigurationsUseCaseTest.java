package com.robinfood.storeor.usecase.updateresolutions.updateresolutionsconfigurationsbc;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IResolutionMapper;
import com.robinfood.storeor.mocks.dto.resolutions.ResolutionDTOMock;
import com.robinfood.storeor.mocks.entity.resolutions.ResolutionEntityMock;
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
public class UpdateResolutionsConfigurationsUseCaseTest {

    @Mock
    private IResolutionsConfigurationsRepository resolutionsRepository;

    @Mock
    private IResolutionMapper resolutionMapper;

    @InjectMocks
    private UpdateResolutionsConfigurationsUseCase updateResolutionsConfigurationsUseCase;

    final ResolutionEntity resolutionEntityMock = new ResolutionEntityMock().defaultData();
    final ResolutionDTO resolutionDTO = new ResolutionDTOMock().defaultData();

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
    void test_UpdateResolutionsConfigurationsUseCase_Invoke_Returns_Correctly() throws ResolutionCrudException {

        when(resolutionsRepository.updateStoreResolutions(resolutionEntityMock, 1L, token))
                .thenReturn(apiResponse);

        when(resolutionMapper.resolutionDTOToResolutionEntity(resolutionDTO))
                .thenReturn(resolutionEntityMock);

        updateResolutionsConfigurationsUseCase
                .invoke(resolutionDTO, 1L, token);

        verify(resolutionMapper).resolutionDTOToResolutionEntity(resolutionDTO);
        verify(resolutionsRepository).updateStoreResolutions(resolutionEntityMock, 1L, token);

        ArgumentCaptor<ResolutionEntity> resolutionEntityArgumentCaptor =
                ArgumentCaptor.forClass(ResolutionEntity.class);
        verify(resolutionsRepository).updateStoreResolutions(resolutionEntityArgumentCaptor.capture(), eq(1L)
                , eq(token));
    }

    @Test
    void test_UpdateResolutionsConfigurationsUseCase_Invoke_Returns_Exception() {

        when(resolutionMapper.resolutionDTOToResolutionEntity(resolutionDTO))
                .thenReturn(resolutionEntityMock);

        when(resolutionsRepository.updateStoreResolutions(resolutionEntityMock, 1L, token))
                .thenAnswer(invocation ->
                        apiResponseException
                );

        ResolutionCrudException exceptionResponse = assertThrows(ResolutionCrudException.class,
                () -> updateResolutionsConfigurationsUseCase
                        .invoke(resolutionDTO, 1L, token));

        assertEquals("Resolutions could not be updated in configurations bc " + "Exception error in updated",
                exceptionResponse.getMessage());
    }
}
