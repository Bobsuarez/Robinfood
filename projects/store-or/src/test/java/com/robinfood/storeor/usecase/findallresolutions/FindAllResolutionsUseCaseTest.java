package com.robinfood.storeor.usecase.findallresolutions;

import com.robinfood.storeor.dtos.findAllResolutions.SearchResolutionDTO;
import com.robinfood.storeor.dtos.response.DataResolutionResponseDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.DataResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.SearchResolutionEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mappers.IFindAllResolutionsMapper;
import com.robinfood.storeor.mocks.dto.resolutions.SearchResolutionDTOMock;
import com.robinfood.storeor.mocks.entity.resolutions.DataResolutionEntityMock;
import com.robinfood.storeor.repositories.resolutionsrepository.IResolutionsOrderPosRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllResolutionsUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IResolutionsOrderPosRepository resolutionsOrderPosRepository;

    @Mock
    private IFindAllResolutionsMapper findAllResolutionsMapper;

    @InjectMocks
    private FindAllResolutionsUseCase findAllResolutionsUseCase;

    private final DataResolutionEntity dataResolutionEntity = new DataResolutionEntityMock().defaultData();

    private final SearchResolutionDTO searchResolutionDTO = new SearchResolutionDTOMock().defaultData();

    final APIResponseEntity<DataResolutionEntity> apiResponse = new APIResponseEntity<>(
            200, dataResolutionEntity,
            "2022-12-13T21:31:57.837443Z",
            "find all resolution retrieved successfully",
            "OK"
    );

    private final String token = "token";

    @Test
    void test_invoke_Returns_Correctly() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        SearchResolutionEntity searchResolutionEntity =
                findAllResolutionsMapper.searchResolutionEntityToResolutionsListResponseDTO(searchResolutionDTO);

        when(resolutionsOrderPosRepository.findAllResolutions(searchResolutionEntity, token)).thenReturn(apiResponse);

        DataResolutionResponseDTO dataResolutionResponseDTO = findAllResolutionsUseCase.invoke(searchResolutionDTO);

        assertEquals(findAllResolutionsMapper.dataResolutionEntityToDataResolutionResponseDTO(apiResponse.getData()),
                dataResolutionResponseDTO);
    }


}
