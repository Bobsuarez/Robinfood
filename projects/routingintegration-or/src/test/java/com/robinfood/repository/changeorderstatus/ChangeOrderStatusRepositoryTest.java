package com.robinfood.repository.changeorderstatus;

import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;
import com.robinfood.network.api.ChangeOrderStatusBcApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class ChangeOrderStatusRepositoryTest {

    @Mock
    ChangeOrderStatusBcApi changeOrderStatusBcApi;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getChannelIntegration_Should_ReturnEntity_When_InvokeTheRepository() {

        doNothing().when(changeOrderStatusBcApi).changeOrderStatus(
                any(RequestChangeOrderStatusDTO.class),
                anyString(),
                anyString());

        ChangeOrderStatusRepository changeOrderStatusRepository =
                new ChangeOrderStatusRepository(changeOrderStatusBcApi);

        changeOrderStatusRepository.changeOrderStatus(
                RequestChangeOrderStatusDTO.builder().build(),
                "",
                "Token"
        );

        verify(changeOrderStatusBcApi).changeOrderStatus(
                any(RequestChangeOrderStatusDTO.class),
                anyString(),
                anyString()
        );

    }
}
