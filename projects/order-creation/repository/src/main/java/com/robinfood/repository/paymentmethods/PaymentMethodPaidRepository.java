package com.robinfood.repository.paymentmethods;

import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidRequestDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidResponseDTO;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidRequestEntity;
import com.robinfood.core.mappers.PaymentMethodPaidMappers;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class PaymentMethodPaidRepository implements IPaymentMethodPaidRepository {

    private final IPaymentMethodPaidRemoteDatasource paymentMethodPaidDatasource;

    public PaymentMethodPaidRepository(IPaymentMethodPaidRemoteDatasource paymentMethodPaidDatasource) {
        this.paymentMethodPaidDatasource = paymentMethodPaidDatasource;
    }

    @Override
    public CompletableFuture<PaymentMethodPaidResponseDTO> send(
        String token,
        PaymentMethodPaidRequestDTO paymentMethodPaidRequestDTO
    ) {

        log.info("Enter to Payment Method Paid Repository");

        final PaymentMethodPaidRequestEntity paymentMethodRequestEntity =
            PaymentMethodPaidMappers.toPaymentMethodPaidRequestEntity(paymentMethodPaidRequestDTO);

        return paymentMethodPaidDatasource.buildPaymentMethod(
            token,
            paymentMethodRequestEntity
        ).thenApply(PaymentMethodPaidMappers::toPaymentMethodPaidResponseDTO);
    }
}
