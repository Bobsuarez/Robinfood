package com.robinfood.ordereports.repositories.paymentmethod;

import com.robinfood.ordereports.dtos.orders.PaymentDetailDTO;

public interface IPaymentMethodRepository {

    PaymentDetailDTO getPaymentMethod(String token, String transactionUuid);
}
