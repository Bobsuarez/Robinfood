package com.robinfood.storeor.usecase.createresolutions.createresolutionsordersposbc;

import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IStoreResolutionsMapper;
import com.robinfood.storeor.mocks.dto.resolutions.StoreResolutionsDTOMock;
import com.robinfood.storeor.mocks.entity.resolutions.StoreResolutionsEntityMock;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsOrderPosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateResolutionsOrdersPosUseCaseTest {

    @Mock
    private IResolutionsOrderPosRepository resolutionsOrderPosRepository;

    @Mock
    private IStoreResolutionsMapper storeResolutionsMapper;

    @InjectMocks
    private CreateResolutionsOrdersPosUseCase createResolutionsOrdersPosUseCase;

    final StoreResolutionsEntity storeResolutionsEntityMock = new StoreResolutionsEntityMock().defaultData();
    final StoreResolutionsDTO storeResolutionsDTOMock = new StoreResolutionsDTOMock().defaultData();

    List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosEntityList = List.of(
            ResponseResolutionsWithPosDTO.builder()
                    .posId(1L)
                    .id(1L)
                    .build()
    );

    List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosEntityDifferentList = List.of(
            ResponseResolutionsWithPosDTO.builder()
                    .posId(2L)
                    .id(1L)
                    .build()
    );

    APIResponseEntity<Object> apiResponse = new APIResponseEntity<>(
            201,
            "",
            "2023-08-11T21:31:57.837443Z",
            "Resolutions created successfully",
            "OK"
    );

    APIResponseEntity<Object> apiResponseException = new APIResponseEntity<>(
            500,
            "",
            "2023-08-11T21:31:57.837443Z",
            "Exception error in created",
            "OK"
    );

    final String token = "token";

    @Test
    void test_CreateResolutionsOrdersPosUseCase_Invoke_Returns_Correctly() throws ResolutionCrudException {

        when(storeResolutionsMapper.storeResolutionDTOToStoreResolutionEntity(storeResolutionsDTOMock))
                .thenReturn(storeResolutionsEntityMock);

        when(resolutionsOrderPosRepository.createStoreResolutions(token, storeResolutionsEntityMock))
                .thenAnswer(invocation ->
                        apiResponse
                );

        createResolutionsOrdersPosUseCase
                .invoke(responseResolutionsWithPosEntityList, storeResolutionsDTOMock, token);

        verify(storeResolutionsMapper).storeResolutionDTOToStoreResolutionEntity(storeResolutionsDTOMock);
        verify(resolutionsOrderPosRepository).createStoreResolutions(token, storeResolutionsEntityMock);

        ArgumentCaptor<StoreResolutionsEntity> storeResolutionsEntityCaptor =
                ArgumentCaptor.forClass(StoreResolutionsEntity.class);
        verify(resolutionsOrderPosRepository).createStoreResolutions(eq(token), storeResolutionsEntityCaptor.capture());
    }

    @Test
    void test_CreateResolutionsOrdersPosUseCase_Invoke_Returns_Resolutions_Empty_Correctly()
            throws ResolutionCrudException {

        StoreResolutionsEntity storeResolutionsEntityMockEmpty = new StoreResolutionsEntityMock().defaultData();
        StoreResolutionsDTO storeResolutionsDTOMockEmpty = new StoreResolutionsDTOMock().defaultData();

        storeResolutionsEntityMockEmpty.setResolutions(new ArrayList<>());
        storeResolutionsDTOMockEmpty.setResolutions(new ArrayList<>());

        when(storeResolutionsMapper.storeResolutionDTOToStoreResolutionEntity(storeResolutionsDTOMockEmpty))
                .thenReturn(storeResolutionsEntityMockEmpty);

        when(resolutionsOrderPosRepository.createStoreResolutions(token, storeResolutionsEntityMockEmpty))
                .thenAnswer(invocation ->
                        apiResponse
                );

        createResolutionsOrdersPosUseCase
                .invoke(responseResolutionsWithPosEntityList, storeResolutionsDTOMockEmpty, token);

        verify(storeResolutionsMapper).storeResolutionDTOToStoreResolutionEntity(storeResolutionsDTOMockEmpty);
        verify(resolutionsOrderPosRepository).createStoreResolutions(token, storeResolutionsEntityMockEmpty);

        ArgumentCaptor<StoreResolutionsEntity> storeResolutionsEntityCaptor =
                ArgumentCaptor.forClass(StoreResolutionsEntity.class);
        verify(resolutionsOrderPosRepository).createStoreResolutions(eq(token), storeResolutionsEntityCaptor.capture());
    }

    @Test
    void test_CreateResolutionsOrdersPosUseCase_Invoke_Returns_Resolutions_Different()
            throws ResolutionCrudException {

        when(storeResolutionsMapper.storeResolutionDTOToStoreResolutionEntity(storeResolutionsDTOMock))
                .thenReturn(storeResolutionsEntityMock);

        when(resolutionsOrderPosRepository.createStoreResolutions(token, storeResolutionsEntityMock))
                .thenAnswer(invocation ->
                        apiResponse
                );

        createResolutionsOrdersPosUseCase
                .invoke(responseResolutionsWithPosEntityDifferentList, storeResolutionsDTOMock, token);

        verify(storeResolutionsMapper).storeResolutionDTOToStoreResolutionEntity(storeResolutionsDTOMock);
        verify(resolutionsOrderPosRepository).createStoreResolutions(token, storeResolutionsEntityMock);

        ArgumentCaptor<StoreResolutionsEntity> storeResolutionsEntityCaptor =
                ArgumentCaptor.forClass(StoreResolutionsEntity.class);
        verify(resolutionsOrderPosRepository).createStoreResolutions(eq(token), storeResolutionsEntityCaptor.capture());
    }

    @Test
    void test_CreateResolutionsOrdersPosUseCase_Invoke_Returns_Exception() {

        when(storeResolutionsMapper.storeResolutionDTOToStoreResolutionEntity(storeResolutionsDTOMock))
                .thenReturn(storeResolutionsEntityMock);

        when(resolutionsOrderPosRepository.createStoreResolutions(token, storeResolutionsEntityMock)).thenAnswer(invocation ->
                apiResponseException
        );

        ResolutionCrudException exceptionResponse = assertThrows(ResolutionCrudException.class,
                () -> createResolutionsOrdersPosUseCase
                        .invoke(responseResolutionsWithPosEntityList, storeResolutionsDTOMock, token));

        assertEquals("Resolutions could not be created in database orders " + "Exception error in created",
                exceptionResponse.getMessage());
    }
}
