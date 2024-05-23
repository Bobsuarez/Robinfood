package com.robinfood.changestatusor.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.usecases.changeorderstatus.IChangeOrderStatusUseCase;
import com.robinfood.changestatusor.usecases.validatechangestate.IValidateChangeStateUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.robinfood.changestatusor.utilities.ObjectMapperSingleton.objectToJson;

@Slf4j
@Component
@ConditionalOnProperty(name = "feature.broker.change.status.enabled")
public class ChangeOrderStatusConsumer {

    private final IChangeOrderStatusUseCase changeOrderStatusUseCase;

    private final IValidateChangeStateUseCase validateChangeStateUseCase;

    public ChangeOrderStatusConsumer(
            IChangeOrderStatusUseCase changeOrderStatusUseCase,
            IValidateChangeStateUseCase validateChangeStateUseCase
    ) {
        this.changeOrderStatusUseCase = changeOrderStatusUseCase;
        this.validateChangeStateUseCase = validateChangeStateUseCase;
    }

    @JmsListener(
            destination = "${activemq.orders-change-status.queue}",
            containerFactory = "jmsFactory"
    )
    public void changeStateOrder(String changeOrderStatus) throws JsonProcessingException {

        log.info(
                "Receiving message for order status change with data: {}", changeOrderStatus
        );

        ChangeOrderStatusDTO changeOrderStatusDTO = convertStringToDTO(changeOrderStatus);

        log.info(
                "changeOrderStatusDTO converter: {}", objectToJson(changeOrderStatusDTO)
        );

        validateChangeStateUseCase.invoke(changeOrderStatusDTO);

        ChangeOrderStatusDTO result = changeOrderStatusUseCase.invoke(changeOrderStatusDTO);

        log.info(
                "Order change state with: {} was successfully " +
                        "change and was send to the queue with the following data {}"
                , objectToJson(changeOrderStatusDTO),
                result
        );
    }


    public static ChangeOrderStatusDTO convertStringToDTO(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, ChangeOrderStatusDTO.class);
    }
}
