package com.robinfood.orderorlocalserver.usecases.processordercreatedfromconsumer;

import com.robinfood.orderorlocalserver.configs.logging.CustomLogging;
import com.robinfood.orderorlocalserver.dtos.orderqueue.OrderCreatedQueueDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.enums.Result;
import com.robinfood.orderorlocalserver.exceptions.GetOrderDetailFailedException;
import com.robinfood.orderorlocalserver.repositories.sqs.IOrderSqsRepository;
import com.robinfood.orderorlocalserver.usecases.getorderdetailprint.IGetOrderDetailPrintUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import static com.robinfood.orderorlocalserver.enums.MessageLogEnum.MESSAGE_SENT_TO_BE_PRINTED_SUCESSFULLY;
import static com.robinfood.orderorlocalserver.enums.MessageLogEnum.ORDER_NOT_PAID;
import static com.robinfood.orderorlocalserver.enums.MessageLogEnum.ORDER_TO_SEND;

@Service
@Slf4j
public class ProcessOrderCreatedFromConsumerUseCase implements IProcessOrderCreatedFromConsumerUseCase {

    private final IGetOrderDetailPrintUseCase getOrderDetailPrintUseCase;
    private final IOrderSqsRepository orderSqsRepository;

    public ProcessOrderCreatedFromConsumerUseCase(
            IGetOrderDetailPrintUseCase getOrderDetailPrintUseCase,
            IOrderSqsRepository orderSqsRepository
    ) {
        this.getOrderDetailPrintUseCase = getOrderDetailPrintUseCase;
        this.orderSqsRepository = orderSqsRepository;
    }

    @Override
    public void invoke(OrderCreatedQueueDTO orderCreatedQueueDTO) throws GetOrderDetailFailedException {

        log.info("Starting use case to process the created order. Order: {}", orderCreatedQueueDTO);

        if (Boolean.FALSE.equals(orderCreatedQueueDTO.getPaid())) {
            log.info("{} getPaid()={}", ORDER_NOT_PAID.getMessage(), orderCreatedQueueDTO.getPaid());
            return;
        }

        final List<Long> orderIds = Collections.singletonList(orderCreatedQueueDTO.getId());

        final Result<List<OrderDetailDTO>> orderDetailDTOResult = getOrderDetailPrintUseCase.invoke(
                orderIds, Collections.emptyList(), Collections.emptyList() );

        log.info("This is order detail result with info: {}", orderDetailDTOResult);

        if (orderDetailDTOResult instanceof Result.Error) {
            log.error("Error while consulting the order detail");

            Result.Error error = ((Result.Error) orderDetailDTOResult);
            throw new GetOrderDetailFailedException(error.getException().getMessage());
        }

        OrderDetailDTO orderDetailDTO = ((Result.Success<List<OrderDetailDTO>>) orderDetailDTOResult)
                .getData()
                .get(0);

        CustomLogging.invoke(orderDetailDTO);

        log.info("{} orderDetail is {}", ORDER_TO_SEND.getMessage(), orderDetailDTO);

        orderSqsRepository.sendOrderCreatedMessage(orderDetailDTO);

        log.info("{}", MESSAGE_SENT_TO_BE_PRINTED_SUCESSFULLY.getMessage());
    }

}
