package com.robinfood.app.usecases.getpaymentmethod;

import com.robinfood.app.mocks.PaymentMethodDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GetPaymentMethodFoodCoinsTest {

    @InjectMocks
    private GetPaymentMethodFoodCoinsUseCase getPaymentMethodFoodCoin;

    private final Long paymentMethodId = 7L;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(getPaymentMethodFoodCoin, "foodCoinsPaymentMethodId", paymentMethodId);
    }

    @Test
    void test_Search_Payment_Method_Food_Coin_Success() {

        final List<PaymentMethodDTO> paymentMethodDTOS = PaymentMethodDTOMock.List();

        paymentMethodDTOS.get(0).setId(paymentMethodId);

        final PaymentMethodDTO response = getPaymentMethodFoodCoin.invoke(paymentMethodDTOS);

        assertNotNull(response);
    }
}
