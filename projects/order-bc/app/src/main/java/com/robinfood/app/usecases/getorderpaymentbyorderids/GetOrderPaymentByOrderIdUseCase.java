package com.robinfood.app.usecases.getorderpaymentbyorderids;

import com.robinfood.app.mappers.OrderPaymentMappers;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GetOrderPaymentByOrderIdUseCase implements IGetOrderPaymentByOrderIdUseCase {

    private final IOrderPaymentRepository orderPaymentRepository;

    public GetOrderPaymentByOrderIdUseCase(IOrderPaymentRepository orderPaymentRepository) {
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Override
    public List<OrderPaymentDTO> invoke(Long orderId) {

        log.info(
            "Start of the process that searches for the payment methods of an order with orderId {}",
            orderId
        );

        List<OrderPaymentDTO> paymentDTOS = CollectionsKt.map(
            orderPaymentRepository.findAllByOrderId(orderId),
            OrderPaymentMappers::toOrderPaymentDTO
        );

        log.info("Payment methods found {}", paymentDTOS);

        return paymentDTOS;
    }

}
