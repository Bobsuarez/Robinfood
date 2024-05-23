package com.robinfood.storeor.usecase.updateresolutions.updateresolutionsordersposbc;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IResolutionMapper;
import com.robinfood.storeor.mocks.dto.resolutions.ResolutionDTOMock;
import com.robinfood.storeor.mocks.entity.resolutions.ResolutionEntityMock;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsOrderPosRepository;
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
public class UpdateResolutionsOrdersPosUseCaseTest {

    @Mock
    private IResolutionsOrderPosRepository resolutionsOrderPosRepository;

    @Mock
    private IResolutionMapper resolutionMapper;

    @InjectMocks
    private UpdateResolutionsOrdersPosUseCase updateResolutionsOrdersPosUseCase;

    final ResolutionEntity resolutionEntityMock = new ResolutionEntityMock().defaultData();
    final ResolutionDTO resolutionDTOMock = new ResolutionDTOMock().defaultData();

    APIResponseEntity<Object> apiResponse = new APIResponseEntity<>(
            202,
            "",
            "2023-08-11T21:31:57.837443Z",
            "Resolutions updated successfully",
            "OK"
    );

    APIResponseEntity<Object> apiResponseException = new APIResponseEntity<>(
            500,
            "",
            "2023-08-11T21:31:57.837443Z",
            "Exception error in updated",
            "OK"
    );

    final String token = "token";

    @Test
    void test_UpdateResolutionsOrdersPosUseCase_Invoke_Returns_Correctly() throws ResolutionCrudException {

        when(resolutionMapper.resolutionDTOToResolutionEntity(resolutionDTOMock))
                .thenReturn(resolutionEntityMock);

        when(resolutionsOrderPosRepository.updateStoreResolutions(resolutionEntityMock, 1L, token))
                .thenAnswer(invocation ->
                        apiResponse
                );

        updateResolutionsOrdersPosUseCase
                .invoke(resolutionDTOMock, 1L, token);

        verify(resolutionMapper).resolutionDTOToResolutionEntity(resolutionDTOMock);
        verify(resolutionsOrderPosRepository).updateStoreResolutions(resolutionEntityMock, 1L, token);

        ArgumentCaptor<ResolutionEntity> resolutionEntityArgumentCaptor =
                ArgumentCaptor.forClass(ResolutionEntity.class);
        verify(resolutionsOrderPosRepository).updateStoreResolutions(resolutionEntityArgumentCaptor.capture(), eq(1L)
                , eq(token));
    }

    @Test
    void test_UpdateResolutionsOrdersPosUseCase_Invoke_Returns_Exception() {

        when(resolutionMapper.resolutionDTOToResolutionEntity(resolutionDTOMock))
                .thenReturn(resolutionEntityMock);

        when(resolutionsOrderPosRepository.updateStoreResolutions(resolutionEntityMock, 1L, token))
                .thenAnswer(invocation ->
                        apiResponseException
                );

        ResolutionCrudException exceptionResponse = assertThrows(ResolutionCrudException.class,
                () -> updateResolutionsOrdersPosUseCase
                        .invoke(resolutionDTOMock, 1L, token));

        assertEquals("Resolutions could not be updated in database orders " + "Exception error in updated",
                exceptionResponse.getMessage());
    }
}
