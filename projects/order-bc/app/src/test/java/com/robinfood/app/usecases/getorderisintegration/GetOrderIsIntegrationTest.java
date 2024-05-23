package com.robinfood.app.usecases.getorderisintegration;

import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderIsIntegrationTest {

    @Mock
    private IOrderIntegrationRepository mockOrderIntegrationRepository;

    @InjectMocks
    private GetOrderIsIntegrationUseCase getOrderIsIntegrationUseCase;

    @Test
    void test_Order_Is_Integration_True() {
        when(mockOrderIntegrationRepository.existsById(1L))
                .thenReturn(true);

        final Boolean result = getOrderIsIntegrationUseCase.invoke(1L);

        Assertions.assertTrue(result);

    }

    @Test
    void test_Order_Is_Integration_False() {
        when(mockOrderIntegrationRepository.existsById(1L))
                .thenReturn(false);

        final Boolean result = getOrderIsIntegrationUseCase.invoke(1L);

        Assertions.assertFalse(result);

    }
}
