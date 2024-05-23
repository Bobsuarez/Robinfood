package com.robinfood.storeor.repositories.resolutionsrepository;

import com.robinfood.storeor.configs.apis.OrdersPosResolutionsApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.entities.configurationposbystore.DataResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.SearchResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;
import com.robinfood.storeor.mocks.entity.resolutions.StoreResolutionsEntityMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResolutionsOrderPosRepositoryTest {

    @Mock
    private OrdersPosResolutionsApi ordersPosResolutionsApi;

    @InjectMocks
    private ResolutionsOrderPosRepository resolutionsOrderPosRepository;

    final StoreResolutionsEntity storeResolutionsEntityMock = new StoreResolutionsEntityMock().defaultData();

    APIResponseEntity<?> apiResponse = new APIResponseEntity<>(
            201,
            "",
            "2023-08-11T21:31:57.837443Z",
            "Resolutions created successfully",
            "OK"
    );

    APIResponseEntity<Object> apiUpdateResponse = new APIResponseEntity<>(
            202,
            "",
            "2023-08-11T21:31:57.837443Z",
            "Resolutions created successfully",
            "OK"
    );

    APIResponseEntity<Object> findAllResolutionResponse = new APIResponseEntity<>(
            200,
            "",
            "2023-08-11T21:31:57.837443Z",
            "Resolutions created successfully",
            "OK"
    );

    final String token = "token";

    @Test
    void test_ResolutionsOrderPosRepository_createStoreResolutions_Returns_Successfully() {

        when(ordersPosResolutionsApi.createStoreResolutions(storeResolutionsEntityMock, token))
                .thenAnswer(invocation ->
                        apiResponse
                );

        final APIResponseEntity<?> apiResponseEntity = resolutionsOrderPosRepository
                .createStoreResolutions(token, storeResolutionsEntityMock);

        assertEquals(201, apiResponseEntity.getCode());
    }

    @Test
    void test_ResolutionsOrderPosRepository_updateStoreResolutions_Returns_Successfully() {

        when(ordersPosResolutionsApi.updateStoreResolutions(storeResolutionsEntityMock.getResolutions().get(0),
                1L, token))
                .thenAnswer(invocation ->
                        apiUpdateResponse
                );

        final APIResponseEntity<Object> apiResponseEntity = resolutionsOrderPosRepository
                .updateStoreResolutions(storeResolutionsEntityMock.getResolutions().get(0), 1L, token);

        assertEquals(202, apiResponseEntity.getCode());
    }

    @Test
    void test_ResolutionsOrderPosRepository_activateOrDeactivateResolutions_Returns_Successfully() {

        ActivateOrDeactivateEntity activateOrDeactivateEntity = new ActivateOrDeactivateEntity(false);

        when(ordersPosResolutionsApi.activateOrDeactivate(activateOrDeactivateEntity,
                1L, token))
                .thenAnswer(invocation ->
                        apiUpdateResponse
                );

        final APIResponseEntity<Object> apiResponseEntity = resolutionsOrderPosRepository
                .activateOrDeactivate(activateOrDeactivateEntity, 1L, token);

        assertEquals(202, apiResponseEntity.getCode());
    }

    @Test
    void test_FindAllResolutions_Returns_Successfully() {

        Integer page = 1;
        Integer size = 10;

        SearchResolutionEntity searchResolutionEntity = new SearchResolutionEntity(null, page, null, size, null, null,  null);

        when(ordersPosResolutionsApi.findAllResolutions(null, page,  size, null, null, null, null, token))
                .thenAnswer(invocation ->
                        findAllResolutionResponse
                );

        final APIResponseEntity<DataResolutionEntity> apiResponseEntity = resolutionsOrderPosRepository
                .findAllResolutions(searchResolutionEntity, token);

        assertEquals(200, apiResponseEntity.getCode());
    }
}
