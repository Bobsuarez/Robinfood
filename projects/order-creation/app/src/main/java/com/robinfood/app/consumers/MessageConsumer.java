package com.robinfood.app.consumers;

import com.robinfood.core.logging.mappeddiagnosticcontext.MessageConsumerCustomLog;
import com.robinfood.app.usecases.processorderfromconsumer.IProcessOrderFromConsumerUseCase;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.enums.logs.OrderLogEnum;
import com.robinfood.core.exceptions.AppException;
import com.robinfood.core.mappers.transactionv2.OrderToCreateV2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

import static com.robinfood.core.constants.AppConstants.MESSAGE_COUNTRY;
import static com.robinfood.core.constants.AppConstants.MESSAGE_FROM;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Slf4j
@Component
public class MessageConsumer {

    private final IProcessOrderFromConsumerUseCase processOrderFromConsumer;

    public MessageConsumer(
            @NotNull final IProcessOrderFromConsumerUseCase processOrderFromConsumer
    ) {
        this.processOrderFromConsumer = processOrderFromConsumer;
    }

    @JmsListener(
            destination = "${activemq.orders-to-created.topic}",
            containerFactory = "jmsListenerContainerFactoryOrdersQueue"
    )
    public void processMessageConsumer(
            @Payload @NotNull final OrderToCreateDTO orderToCreateDTO,
            @Header(value = MESSAGE_FROM) String messageFrom,
            @Header(value = MESSAGE_COUNTRY) String messageCountry
    ) throws AppException {

        try {
            updateConsumptionValue(orderToCreateDTO);
            MessageConsumerCustomLog.invoke(orderToCreateDTO);

            log.info(
                    OrderLogEnum.ORDER_RECEIVED_SUCCESSFULLY_QUEUE.getMessage() + " : {}, From {} and Country {}",
                    objectToJson(orderToCreateDTO),
                    messageFrom, messageCountry
            );

            OrderToCreateV2Mapper.orderToCreateV1ToOrderToCreateV2(orderToCreateDTO);

            processOrderFromConsumer.invoke(orderToCreateDTO, messageFrom, messageCountry);

            log.info(OrderLogEnum.ORDER_TRANSACTION_CREATED_QUEUE.getMessage(), objectToJson(orderToCreateDTO));

        } catch (Exception e) {
            log.error("Consume message has failed. {}", e.getMessage(), e);
            throw new AppException(e);
        }
    }

    private void updateConsumptionValue(OrderToCreateDTO orderToCreateDTO) {

        orderToCreateDTO.getOrders().forEach(order -> order.setConsumptionValue(BigDecimal.ZERO));
    }
}
