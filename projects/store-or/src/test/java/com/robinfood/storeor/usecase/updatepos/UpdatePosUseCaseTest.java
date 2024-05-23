package com.robinfood.storeor.usecase.updatepos;

import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;
import com.robinfood.storeor.mocks.dto.PosDTOMock;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.storeor.usecase.updateconfigurationbypos.IUpdatePosConfigurationsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePosUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IUpdatePosConfigurationsUseCase updatePosConfigurationsUseCase;

    @InjectMocks
    private UpdatePosUseCase updatePosUseCase;

    private final String TOKEN = "token";

    @Test
    void test_updatePosConfigurationsUseCase_Invoke_Returns_Correctly() throws PosException {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(TOKEN)
                .build()
        );

        doNothing().when(updatePosConfigurationsUseCase).invoke(anyLong(), any(), anyString());

        updatePosUseCase.invoke(1L, PosDTOMock.getDataDefault());

        verify(updatePosConfigurationsUseCase).invoke(anyLong(), any(), anyString());
    }
}
