package com.robinfood.app.usecases.sendorderdiscardedtoqueue;

import com.robinfood.core.constants.StatusCodeConstants;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.repository.queue.activemq.IProducerRepository;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendOrderDiscardedToQueueUseCase implements ISendOrderDiscardedToQueueUseCase {

    private final IProducerRepository producerRepository;

    public SendOrderDiscardedToQueueUseCase(IProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public TransactionRequestDTO invoke(TransactionRequestDTO transactionRequest) {

        for (OrderDTO order : transactionRequest.getOrders()) {

            if (!Objects.isNull(order.getId())) {

                ChangeOrderStatusDTO changeStatus = ChangeOrderStatusDTO.builder()
                        .notes("Order discarded")
                        .orderId(order.getId())
                        .origin("order-or-command for order discarded")
                        .statusCode(StatusCodeConstants.ORDER_DISCARDED.name())
                        .userId(transactionRequest.getUser().getId())
                        .uuid(order.getUuid())
                        .build();

                producerRepository.sendChangeStatusMessage(changeStatus);

            }
        }

        return transactionRequest;
    }
}
