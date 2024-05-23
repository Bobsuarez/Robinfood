package com.robinfood.core.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class PaymentMethodsUtil {

    @Value("${orders.payment-method-ids}")
    private List<Long> paymentMethodIds;

    private PaymentMethodsUtil() {
        // Constructor empty
    }

    public List<Long> getPaymentMethodIds() {
        return new ArrayList<>(paymentMethodIds);
    }

}
