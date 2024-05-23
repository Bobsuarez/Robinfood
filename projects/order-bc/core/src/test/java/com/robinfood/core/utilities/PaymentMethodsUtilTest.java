package com.robinfood.core.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PaymentMethodsUtilTest {

    @InjectMocks
    private PaymentMethodsUtil paymentMethodsUtil;

    final private List<Long> paymentMethodsIds = List.of(1L);

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(paymentMethodsUtil, "paymentMethodIds", paymentMethodsIds);
    }

    @Test
    void test_getPaymentMethodIds_Should_GetPaymentMethods_When_InvokeUtil() {

        Assertions.assertNotNull(paymentMethodsUtil.getPaymentMethodIds());
    }

}