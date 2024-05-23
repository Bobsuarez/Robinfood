package com.robinfood.app.usecases.createorderpaymentdetail;

import com.robinfood.app.mappers.input.PaymentDetailMappers;
import com.robinfood.core.dtos.request.transaction.RequestPaymentDetailDTO;
import com.robinfood.core.entities.OrderPaymentDetailEntity;
import com.robinfood.repository.orderpaymentdetail.IOrderPaymentDetailRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of ICreateOrderPaymentDetailUseCase
 */
@Component
@Slf4j
public class CreateOrderPaymentDetailUseCase implements ICreateOrderPaymentDetailUseCase {

    private final IOrderPaymentDetailRepository orderPaymentDetailRepository;

    public CreateOrderPaymentDetailUseCase(IOrderPaymentDetailRepository orderPaymentDetailRepository) {
        this.orderPaymentDetailRepository = orderPaymentDetailRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(RequestPaymentDetailDTO paymentDetailDTO) {

        log.info("Starting process to save payment detail with data: {}", objectToJson(paymentDetailDTO));

        List<OrderPaymentDetailEntity> orderPaymentDetailEntities = CollectionsKt.map(
                Collections.singletonList(paymentDetailDTO),
            PaymentDetailMappers::toOrderPaymentDetailEntity
        );

        orderPaymentDetailRepository.saveAll(orderPaymentDetailEntities);

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
