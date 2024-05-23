package com.robinfood.storeor.usecase.getconfigurationbypos;

import com.robinfood.storeor.dtos.response.PosResponseDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.PosTypeEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mappers.IPosMapper;
import com.robinfood.storeor.mocks.entity.pos.PosEntityMock;
import com.robinfood.storeor.repositories.posrepository.IPosRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetConfigurationByPosUseCaseTest {

    @Mock
    private IPosRepository posRepository;
    @Mock
    private  IPosMapper iPosMapper;
    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private  GetConfigurationByPosUseCase getConfigurationByPosUseCase;

    private final String token = "token";

    APIResponseEntity<PosEntity> apiResponse = new APIResponseEntity<>(
            200, PosEntityMock.getDataDefault(true),
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store and User successfully",
            "OK"
    );

    @Test
    void test_GetPosConfiguration_Returns_Correctly() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(posRepository.getPosConfiguration(token, 1L, 1L)).thenReturn(apiResponse);

        PosResponseDTO posResponseDTO = getConfigurationByPosUseCase.invoke( 1L, 1L);

        assertEquals(iPosMapper.posEntityToPosResponseDTO(PosEntityMock.getDataDefault(true)), posResponseDTO);
    }
}
