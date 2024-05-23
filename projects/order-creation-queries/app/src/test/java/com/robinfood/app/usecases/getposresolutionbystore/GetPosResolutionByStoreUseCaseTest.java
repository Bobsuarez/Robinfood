package com.robinfood.app.usecases.getposresolutionbystore;

import com.robinfood.app.mocks.PosResolutionDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetPosResolutionByStoreUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IPosResolutionRepository iPosResolutionRepository;

    @InjectMocks
    private GetPosResolutionByStoreUseCase getPosResolutionByStoreUseCase;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(iPosResolutionRepository.getDataPosResolutionByStore(any(DataPosResolutionRequestDTO.class), anyString()))
                .thenReturn(new Result.Success<>(List.of(PosResolutionDTOMock.getDataDefault())));

        getPosResolutionByStoreUseCase.invoke(DataPosResolutionRequestDTO.builder().build());

        verify(iPosResolutionRepository)
                .getDataPosResolutionByStore(
                        any(DataPosResolutionRequestDTO.class),
                        anyString()
                );
    }
}