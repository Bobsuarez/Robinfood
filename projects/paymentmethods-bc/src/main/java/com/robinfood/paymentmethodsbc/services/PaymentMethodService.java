package com.robinfood.paymentmethodsbc.services;

import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.PaymentMethodDetailsDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethodDetailsDTO> getPaymentMethodsByStoreAndChannelAndOrigin(
        Long storeId,
        Long channelId,
        Long originId
    )
        throws BaseException;
}
