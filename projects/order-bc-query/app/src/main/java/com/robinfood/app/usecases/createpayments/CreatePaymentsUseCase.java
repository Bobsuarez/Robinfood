package com.robinfood.app.usecases.createpayments;

import com.robinfood.app.mappers.input.PaymentMethodMappers;
import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import com.robinfood.core.entities.PaymentEntity;
import com.robinfood.repository.payment.IPaymentRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderUserDataUseCase
 */
@Component
@Slf4j
public class CreatePaymentsUseCase implements ICreatePaymentsUseCase {

    private final IPaymentRepository paymentRepository;

    public CreatePaymentsUseCase(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<RequestPaymentMethodDTO> paymentMethodDTOList,
        Long transactionId) {

        log.info("Starting process to save payment methods: [{}], with transaction id [{}]",
            paymentMethodDTOList, transactionId);

        final List<PaymentEntity> paymentEntities = CollectionsKt.map(
            paymentMethodDTOList,
            (RequestPaymentMethodDTO inputPaymentMethodDTO) ->
                PaymentMethodMappers.toPaymentEntity(inputPaymentMethodDTO, transactionId)
        );

        paymentRepository.saveAll(paymentEntities);

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
