package com.robinfood.storeor.usecase.pos.createposconfiguration;

import com.robinfood.storeor.dtos.configurationpos.StoreCreatePosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.StoreCreatePosEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mappers.IPosMapper;
import com.robinfood.storeor.mocks.dto.pos.StoreCreatePosDTOMock;
import com.robinfood.storeor.mocks.entity.pos.StoreCreatePosEntityMock;
import com.robinfood.storeor.repositories.posconfigurationsbcrepository.IPosConfigurationsBcRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreatePosConfigurationsUseCaseTest {

    @Mock
    private IPosMapper posMapper;

    @Mock
    private IPosConfigurationsBcRepository posConfigurationsBcRepository;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private CreatePosConfigurationsUseCase createPosConfigurationsUseCase;

    final StoreCreatePosEntity storeCreatePosEntity = StoreCreatePosEntityMock.getDataDefault(true);

    final StoreCreatePosDTO storeCreatePosDTO = StoreCreatePosDTOMock.getDataDefault(true);

    APIResponseEntity<StoreCreatePosEntity> apiResponse = new APIResponseEntity<>(
            201,
            storeCreatePosEntity,
            "2023-08-11T21:31:57.837443Z",
            "Pos created successfully",
            "OK"
    );

    APIResponseEntity<StoreCreatePosEntity> apiResponseException = new APIResponseEntity<>(
            500,
            null,
            "2023-08-11T21:31:57.837443Z",
            "Exception error internal",
            "OK"
    );

    private final String token = "token";

    @Test
    void test_CreatePosConfigurationsUseCase_Invoke_Returns_Correctly() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(posConfigurationsBcRepository.createPosConfiguration(token, storeCreatePosEntity))
                .thenReturn(apiResponse);

        when(posMapper.storeCreatePosDTOToStoreCreatePosEntity(storeCreatePosDTO))
                .thenReturn(storeCreatePosEntity);

        StoreCreatePosDTO responseDTO = createPosConfigurationsUseCase
                .invoke(storeCreatePosDTO);

        verify(getTokenBusinessCapabilityUseCase).invoke();

        assertEquals(posMapper
                .storeCreatePosEntityToStoreCreatePosDTO(apiResponse.getData()),
                responseDTO);
    }
}
