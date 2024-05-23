package com.robinfood.changestatusor.usecases.changeorderstatus;

import com.robinfood.changestatusor.datamock.ChangeOrderStatusDTOMock;
import com.robinfood.changestatusor.datamock.ChangeStateOrderRespondEntityMock;
import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.models.domain.Token;
import com.robinfood.changestatusor.repository.changestateorders.IChangeSateOrderRepository;
import com.robinfood.changestatusor.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChangeOrderStatusUseCaseTest {

    @Mock
    IChangeSateOrderRepository changeSateOrderRepository;

    @Mock
    IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    ChangeOrderStatusUseCase changeOrderStatusUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_invoke_Should_ReturnChangeOrderStatusDTO_When_InvokeTheUseCase() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(Token.builder()
                        .accessToken("TOKEN")
                        .expiresIn(1234567898765432L)
                .build());

        when(changeSateOrderRepository.changeOrderStatus(any(ChangeOrderStatusDTO.class), anyString()))
                .thenReturn(ChangeStateOrderRespondEntityMock.getDefault());

        changeOrderStatusUseCase = new ChangeOrderStatusUseCase(
                changeSateOrderRepository,
                getTokenBusinessCapabilityUseCase
        );

        changeOrderStatusUseCase.invoke(ChangeOrderStatusDTOMock.getDefault());

        verify(changeSateOrderRepository)
                .changeOrderStatus(any(ChangeOrderStatusDTO.class), anyString());
    }
}
