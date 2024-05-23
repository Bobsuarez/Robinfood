package com.robinfood.usecases.changeorderstatus;

import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;
import com.robinfood.repository.changeorderstatus.IChangeOrderStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class ChangeOrderStatusUseCaseTest {

    @Mock
    IChangeOrderStatusRepository changeOrderStatusRepository;

    RequestChangeOrderStatusDTO requestChangeOrderStatusDTOMock = RequestChangeOrderStatusDTO.builder()
            .deliveryIntegrationId("")
            .notes("")
            .orderId(1L)
            .orderUuid("")
            .origin("")
            .statusCode("")
            .statusId(1L)
            .userId(1L)
            .build();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_invoke_Should_ReturnResponseChangeOrderStatusEntity_When_InvokeTheUseCase() {

        doNothing().when(changeOrderStatusRepository).changeOrderStatus(
                any(RequestChangeOrderStatusDTO.class),
                anyString(),
                anyString());

        ChangeOrderStatusUseCase changeOrderStatusUseCase = new ChangeOrderStatusUseCase(changeOrderStatusRepository);

        changeOrderStatusUseCase.invoke(requestChangeOrderStatusDTOMock, "", "Token");


        verify(changeOrderStatusRepository)
                .changeOrderStatus(any(RequestChangeOrderStatusDTO.class), anyString(), anyString());
    }
}
