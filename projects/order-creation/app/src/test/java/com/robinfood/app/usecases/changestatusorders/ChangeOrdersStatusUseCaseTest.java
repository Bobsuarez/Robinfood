package com.robinfood.app.usecases.changestatusorders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.app.mocks.ChangeStatusOrdersRequestDTOMocks;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.changestatusordersrequestdto.ChangeStatusOrdersRequestDTO;
import com.robinfood.core.models.domain.Token;
import com.robinfood.repository.changestatusorders.ChangeStatusOrdersRepository;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChangeOrdersStatusUseCaseTest {

    private final String TOKEN = "token";
    private final ChangeStatusOrdersRequestDTO changeStatusOrdersRequestDTO = new ChangeStatusOrdersRequestDTOMocks()
        .changeStatusOrdersRequestDTO;

    @Mock
    private ChangeStatusOrdersRepository mockChangeStatusOrdersRepository;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private ChangeOrdersStatusUseCase changeStatusOrdersUseCase;

    @Test
    void test_Change_Status_Orders() {
        when(mockChangeStatusOrdersRepository.invoke(changeStatusOrdersRequestDTO, TOKEN))
            .thenReturn(CompletableFuture.completedFuture(true));

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(Token.builder()
            .accessToken(TOKEN)
            .build());

        final Boolean result = changeStatusOrdersUseCase.invoke(changeStatusOrdersRequestDTO);

        assertTrue(result);
    }
}
