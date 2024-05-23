package com.robinfood.storeor.repositories.posrepository;

import com.robinfood.storeor.configs.apis.ConfigurationPosBcApi;
import com.robinfood.storeor.configs.apis.ConfigurationsBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.PosTypeEntity;
import com.robinfood.storeor.entities.StoreCreatePosEntity;
import com.robinfood.storeor.mocks.entity.pos.PosEntityMock;
import com.robinfood.storeor.mocks.entity.pos.StoreCreatePosEntityMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PosRepositoryTest {

    @Mock
    private ConfigurationPosBcApi configurationPosBcApi;

    @InjectMocks
    private PosRepository PosRepository;

    APIResponseEntity<PosEntity> apiResponse = new APIResponseEntity<>(
            200, PosEntityMock.getDataDefault(true),
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store and User successfully",
            "OK"
    );

    APIResponseEntity<StoreCreatePosEntity> apiCreatePosStatusTrueResponse = new APIResponseEntity<>(
            200, StoreCreatePosEntityMock.getDataDefault(true),
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store and User successfully",
            "OK"
    );

    @Test
    void test_GetPos_Returns_Successfully() throws Exception {

        final String token = "token";
        final Long userId = 1L;
        final Long storeId = 1L;

        when(configurationPosBcApi.getConfigurationPosByUserIdAndStoreId(token, storeId, userId))
                .thenReturn(apiResponse);

        final APIResponseEntity<PosEntity> posEntity = PosRepository.getPosConfiguration(token, userId, storeId);

        assertEquals(posEntity, apiResponse);
    }

}
