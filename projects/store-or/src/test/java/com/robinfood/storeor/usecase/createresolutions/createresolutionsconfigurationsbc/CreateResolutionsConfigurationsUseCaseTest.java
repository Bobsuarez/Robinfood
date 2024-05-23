package com.robinfood.storeor.usecase.createresolutions.createresolutionsconfigurationsbc;

import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;
import com.robinfood.storeor.entities.response.ResponseResolutionsWithPosEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.mappers.IResolutionMapper;
import com.robinfood.storeor.mappers.IResponseResolutionsWithPosMapper;
import com.robinfood.storeor.mocks.dto.resolutions.StoreResolutionsDTOMock;
import com.robinfood.storeor.mocks.entity.resolutions.StoreResolutionsEntityMock;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsConfigurationsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateResolutionsConfigurationsUseCaseTest {

    @Mock
    private IResolutionsConfigurationsRepository resolutionsRepository;

    @Mock
    private IResponseResolutionsWithPosMapper responseResolutionsWithPosMapper;

    @Mock
    private IResolutionMapper resolutionMapper;

    @InjectMocks
    private CreateResolutionsConfigurationsUseCase createResolutionsConfigurationsUseCase;

    final StoreResolutionsEntity storeResolutionsEntityMock = new StoreResolutionsEntityMock().defaultData();
    final StoreResolutionsDTO storeResolutionsDTOMock = new StoreResolutionsDTOMock().defaultData();

    private final String token = "token";

    List<ResponseResolutionsWithPosEntity> responseResolutionsWithPosEntityList = List.of(
            ResponseResolutionsWithPosEntity.builder()
                    .posId(1L)
                    .id(1L)
                    .build()
    );

    APIResponseEntity<List<ResponseResolutionsWithPosEntity>> apiResponse = new APIResponseEntity<>(
            201,
            responseResolutionsWithPosEntityList,
            "2023-08-11T21:31:57.837443Z",
            "Resolutions created successfully",
            "OK"
    );

    APIResponseEntity<List<ResponseResolutionsWithPosEntity>> apiResponseException = new APIResponseEntity<>(
            500,
            null,
            "2023-08-11T21:31:57.837443Z",
            "Exception error internal",
            "OK"
    );


    @Test
    void test_CreateResolutionsConfigurationsUseCase_Invoke_Returns_Correctly() throws ResolutionCrudException {

        when(resolutionsRepository.createStoreResolutions(storeResolutionsEntityMock.getResolutions(), token))
                .thenReturn(apiResponse);

        when(resolutionMapper.resolutionDTOListToResolutionEntityList(storeResolutionsDTOMock.getResolutions()))
                .thenReturn(storeResolutionsEntityMock.getResolutions());

        List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOS = createResolutionsConfigurationsUseCase
                .invoke(storeResolutionsDTOMock, token);

        assertEquals(responseResolutionsWithPosMapper
                        .responseResolutionsWithPosEntityToResponseResolutionsWithPosDTO(apiResponse.getData()),
                responseResolutionsWithPosDTOS);
    }

    @Test
    void test_CreateResolutionsConfigurationsUseCase_Invoke_Returns_Exception() {

        when(resolutionsRepository.createStoreResolutions(storeResolutionsEntityMock.getResolutions(), token))
                .thenReturn(apiResponseException);

        when(resolutionMapper.resolutionDTOListToResolutionEntityList(storeResolutionsDTOMock.getResolutions()))
                .thenReturn(storeResolutionsEntityMock.getResolutions());

        ResolutionCrudException exceptionResponse = assertThrows(ResolutionCrudException.class,
                () -> createResolutionsConfigurationsUseCase
                        .invoke(storeResolutionsDTOMock, token));

        assertEquals("resolutions could not be created in configurations bc " + "Exception error internal",
                exceptionResponse.getMessage());
    }
}
