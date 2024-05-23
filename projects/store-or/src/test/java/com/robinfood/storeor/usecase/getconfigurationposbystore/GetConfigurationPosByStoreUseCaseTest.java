package com.robinfood.storeor.usecase.getconfigurationposbystore;

import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.StorePosEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mappers.IStorePosMapper;
import com.robinfood.storeor.mocks.entity.configurationposbystore.StorePosEntityMock;
import com.robinfood.storeor.repositories.configurationposbystorerepository.IStorePosRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetConfigurationPosByStoreUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IStorePosRepository storePosRepository;

    @InjectMocks
    private GetConfigurationPosByStoreUseCase getConfigurationPosByStoreUseCase;

    private final String token = "token";

    final List<StorePosEntity> storePosEntitiesMocks = new StorePosEntityMock().storePosEntities;

    APIResponseEntity<List<StorePosEntity>> apiResponse = new APIResponseEntity<>(
            200, storePosEntitiesMocks,
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store successfully",
            "OK"
    );

    @Mock
    private IStorePosMapper storePosMapper;

    @Test
    void test_invoke_Returns_Correctly() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(storePosRepository.getConfigurationPosByStore(1L, token)).thenReturn(apiResponse);

        List<StorePosDTO> storePosDTOS = getConfigurationPosByStoreUseCase.invoke(1L);

        assertEquals(storePosMapper.storePosEntitiesToStorePosDTOs(apiResponse.getData()), storePosDTOS);
    }
}
