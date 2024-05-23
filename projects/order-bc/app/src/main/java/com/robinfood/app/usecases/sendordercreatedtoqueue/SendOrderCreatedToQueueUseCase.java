package com.robinfood.app.usecases.sendordercreatedtoqueue;

import com.robinfood.app.mappers.queue.OrderCreatedMappers;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdsUseCase;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.queue.OrderCreatedQueueDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.dtos.response.transaction.ResponseCreatedOrderTransactionDTO;
import com.robinfood.repository.queue.IProducerOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SendOrderCreatedToQueueUseCase implements ISendOrderCreatedToQueueUseCase {

    private final IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;
    private final IProducerOrderRepository producerRepository;
    private final OrderCreatedMappers orderCreatedMappers;

    public SendOrderCreatedToQueueUseCase(
            IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase,
            IProducerOrderRepository producerRepository,
            OrderCreatedMappers orderCreatedMappers
    ) {
        this.getOrderPaymentByOrderIdsUseCase = getOrderPaymentByOrderIdsUseCase;
        this.producerRepository = producerRepository;
        this.orderCreatedMappers = orderCreatedMappers;
    }

    @Override
    public void invoke(
            RequestOrderTransactionDTO requestTransactionDTO,
            ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO
    ) {
        log.info(
                "SendOrderCreatedToQueueUseCase invocation with request: {}, and created orders: {}",
                requestTransactionDTO,
                responseCreatedOrderTransactionDTO
        );

        List<OrderPaymentDTO> paymentDTOS = getOrderPaymentByOrderIdsUseCase
                .invoke(getOrderIds(responseCreatedOrderTransactionDTO));

        List<OrderCreatedQueueDTO> ordersCreatedQueue =
                orderCreatedMappers.toOrderCreatedDTOs(
                        requestTransactionDTO,
                        responseCreatedOrderTransactionDTO,
                        paymentDTOS
                );

        ordersCreatedQueue.forEach(producerRepository::sendOrderCreatedMessage);
    }

    private List<Long> getOrderIds(
            ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO
    ) {
        return responseCreatedOrderTransactionDTO.getOrders().stream()
                .map(ResponseCreatedOrderDTO::getId)
                .collect(Collectors.toList());
    }

}
