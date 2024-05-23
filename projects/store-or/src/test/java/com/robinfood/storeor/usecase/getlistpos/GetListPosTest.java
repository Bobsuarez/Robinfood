package com.robinfood.storeor.usecase.getlistpos;

import com.robinfood.storeor.dtos.apiresponsebuilder.RestResponsePage;
import com.robinfood.storeor.dtos.listposresponse.PosListResponseDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.listposresponse.PosListResponseEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mappers.IPosResponseMapper;
import com.robinfood.storeor.mocks.entity.listposresponse.PosListResponseEntityMock;
import com.robinfood.storeor.repositories.configurationrepository.IConfigurationRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetListPosTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IConfigurationRepository configurationRepository;

    @Mock
    private IPosResponseMapper posResponseMapper;

    @InjectMocks
    private GetListPos getListPos;

    final PosListResponseEntity posListResponseEntity = PosListResponseEntityMock.build();

    final APIResponseEntity<RestResponsePage<PosListResponseEntity>> ApiResponsePage = new APIResponseEntity<>(
            200,
            new RestResponsePage<>(List.of(posListResponseEntity)),
            "locale",
            "Pos List retrieved successfully",
            "Success"
    );


    @Test
    void test_ListPos_UseCase_Returns_Correctly() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken("token")
                .build());

        when(configurationRepository
                .getListPos(0, "caja", 10, 1L, 1L, Sort.unsorted(), "token"))
                .thenReturn(ApiResponsePage);

        final Page<PosListResponseDTO> posListResponseList = getListPos.
                invoke( 0, "caja", 10,1L, 1L, Sort.unsorted());

        Page<PosListResponseDTO> posListResponseDTOS = ApiResponsePage.getData()
                .map(posResponseMapper::posResponseEntityToPosResponseDTO);
        assertEquals(posListResponseList, posListResponseDTOS);
    }

}
