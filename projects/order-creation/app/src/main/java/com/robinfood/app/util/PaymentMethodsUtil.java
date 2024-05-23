package com.robinfood.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public final class PaymentMethodsUtil {

    @Value("#{${orders.payment-method-ids}}")
    private Map<Long, Map<Long, Long>> paymentMethodIds;

    private PaymentMethodsUtil() {
        //Constructor empty
    }

    public Map<Long, Long> getByCountry(Long countryID) {
        return paymentMethodIds.get(countryID);
    }

    public List<Long> getIDsByCountryID(Long countryID) {

        final Map<Long, Long> getPaymentMethodIds = paymentMethodIds.get(countryID);
        return new ArrayList<>(getPaymentMethodIds.keySet());
    }
}
