package com.robinfood.storeor.repositories.configurationposbystorerepository;

import com.robinfood.storeor.configs.apis.ConfigurationsBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.StorePosEntity;
import com.robinfood.storeor.mocks.entity.configurationposbystore.StorePosEntityMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StorePosRepositoryTest {

    @Mock
    private ConfigurationsBcApi configurationsBcApi;

    @InjectMocks
    private StorePosRepository storePosRepository;

    final List<StorePosEntity> storePosEntitiesMocks = new StorePosEntityMock().storePosEntities;

    APIResponseEntity<List<StorePosEntity>> apiResponse = new APIResponseEntity<>(
            200, storePosEntitiesMocks,
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store successfully",
            "OK"
    );

    @Test
    void test_getConfigurationPosByStore_Returns_Successfully() throws Exception {

        final String token = "token";
        final Long storeId = 1L;

        when(configurationsBcApi.getConfigurationPosByStore(token, storeId))
                .thenReturn(apiResponse);

        final APIResponseEntity<List<StorePosEntity>> apiResponseEntity = storePosRepository
                .getConfigurationPosByStore(storeId, token);

        assertEquals(apiResponseEntity, apiResponse);
    }


}
