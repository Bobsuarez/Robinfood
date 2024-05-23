package com.robinfood.app.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PaymentMethodsUtilTest {

    @InjectMocks
    private PaymentMethodsUtil paymentMethodsUtil;

    private final Long countryId = 1L;
    private final Map<Long, Long> paymentMethodsIds = new HashMap<>();
    private final Map<Long, Map<Long, Long>> paymentMethodsIdsByCountryId = new HashMap<>();

    @BeforeEach
    void setUp() {
        paymentMethodsIds.put(6L, 1L);
        paymentMethodsIdsByCountryId.put(countryId, paymentMethodsIds);
        ReflectionTestUtils.setField(paymentMethodsUtil, "paymentMethodIds", paymentMethodsIdsByCountryId);
    }

    @Test
    void test_Get_Payment_By_Country_Success() {

        final Map<Long, Long> response = paymentMethodsUtil.getByCountry(countryId);
        assertNotNull(response);
    }

    @Test
    void test_Get_Payments_By_Country_Success() {

        final List<Long> response = paymentMethodsUtil.getIDsByCountryID(countryId);
        assertNotNull(response);
    }
}
