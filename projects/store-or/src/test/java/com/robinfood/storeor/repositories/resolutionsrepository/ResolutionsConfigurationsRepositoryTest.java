package com.robinfood.storeor.repositories.resolutionsrepository;

import com.robinfood.storeor.configs.apis.ConfigurationsBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;
import com.robinfood.storeor.entities.response.ResponseResolutionsWithPosEntity;
import com.robinfood.storeor.mocks.entity.resolutions.StoreResolutionsEntityMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResolutionsConfigurationsRepositoryTest {

    @Mock
    private ConfigurationsBcApi configurationsBcApi;

    @InjectMocks
    private ResolutionsConfigurationsRepository resolutionsConfigurationsRepository;

    final StoreResolutionsEntity storeResolutionsEntityMock = new StoreResolutionsEntityMock().defaultData();

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

    APIResponseEntity<Object> apiUpdateResponse = new APIResponseEntity<>(
            202,
            null,
            "2023-08-11T21:31:57.837443Z",
            "Resolutions update successfully",
            "OK"
    );

    final String token = "token";

    @Test
    void test_ResolutionsConfigurationsRepository_createStoreResolutions_Returns_Successfully() {

        when(configurationsBcApi.createStoreResolutions(storeResolutionsEntityMock.getResolutions(), token))
                .thenReturn(apiResponse);

        final APIResponseEntity<List<ResponseResolutionsWithPosEntity>>
                apiResponseEntity = resolutionsConfigurationsRepository.createStoreResolutions(
                storeResolutionsEntityMock.getResolutions(),
                token
        );


        assertNotNull(apiResponseEntity.getData());
        assertEquals(apiResponseEntity.getData().get(0).getId(),
                responseResolutionsWithPosEntityList.get(0).getPosId());
    }

    @Test
    void test_ResolutionsConfigurationsRepository_updateStoreResolutions_Returns_Successfully() {

        when(configurationsBcApi.updateStoreResolution(storeResolutionsEntityMock.getResolutions().get(0),
                1L, token))
                .thenReturn(apiUpdateResponse);

        final APIResponseEntity<Object>
                apiResponseEntity = resolutionsConfigurationsRepository.updateStoreResolutions(
                storeResolutionsEntityMock.getResolutions().get(0),
                1L,
                token
        );

        assertEquals(202, apiResponseEntity.getCode());
    }

    @Test
    void test_ResolutionsConfigurationsRepository_activateOrDeactivateResolutions_Returns_Successfully() {

        ActivateOrDeactivateEntity activateOrDeactivateEntity = new ActivateOrDeactivateEntity(false);

        when(configurationsBcApi.activateOrDeactivate(activateOrDeactivateEntity,
                1L, token))
                .thenReturn(apiUpdateResponse);

        final APIResponseEntity<Object>
                apiResponseEntity = resolutionsConfigurationsRepository.activateOrDeactivate(
                activateOrDeactivateEntity,
                1L,
                token
        );

        assertEquals(202, apiResponseEntity.getCode());
    }
}
