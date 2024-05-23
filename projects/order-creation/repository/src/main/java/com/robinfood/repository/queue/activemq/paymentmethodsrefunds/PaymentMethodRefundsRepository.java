package com.robinfood.repository.queue.activemq.paymentmethodsrefunds;

import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodRefundResponseDTO;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundEntity;
import com.robinfood.core.mappers.PaymentMethodRefundMappers;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;


@Slf4j
public class PaymentMethodRefundsRepository implements IPaymentMethodRefundsRepository {

    private final IPaymentMethodRefundsDatasource paymentMethodRefundsDatasource;

    public PaymentMethodRefundsRepository(IPaymentMethodRefundsDatasource paymentMethodRefundsDatasource) {
        this.paymentMethodRefundsDatasource = paymentMethodRefundsDatasource;
    }


    @Override
    public CompletableFuture<PaymentMethodRefundResponseDTO>
    sendRefundMessage(String token, PaymentMethodRefundEntity paymentMethodRefundEntity) {

        log.info("Enter to Payment Method Refunds Repository");

        return paymentMethodRefundsDatasource.sendRefundMessage(
                token,
                paymentMethodRefundEntity
        ).thenApply(PaymentMethodRefundMappers::toPaymentMethodRefundsResponseDTO);
    }
}
