package com.robinfood.app.queue.consumer;

import com.robinfood.app.logging.mappeddiagnosticcontext.MessageConsumerChangeStatusLog;
import com.robinfood.app.usecases.changestatusorders.IChangeOrderStateUseCase;
import com.robinfood.app.usecases.validatechangestate.IValidateChangeStateUseCase;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.robinfood.core.enums.AppOrderBcTraceEnum.RECEIVING_MESSAGE_CHANGE_STATUS_QUEUE;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.RESPONSE_CHANGE_STATUS_QUEUE;
import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

@Component
@Slf4j
@ConditionalOnProperty(name = "feature.broker.orders.status.enabled")
public class ChangeOrderStatusConsumer {

    private final IChangeOrderStateUseCase changeOrderStateUseCase;

    private final IValidateChangeStateUseCase validateChangeStateUseCase;

    public ChangeOrderStatusConsumer(
            IChangeOrderStateUseCase changeOrderStateUseCase,
            IValidateChangeStateUseCase validateChangeStateUseCase
    ) {
        this.changeOrderStateUseCase = changeOrderStateUseCase;
        this.validateChangeStateUseCase = validateChangeStateUseCase;
    }

    @JmsListener(
            destination = "${activemq.orders-change-status.queue}",
            containerFactory = "jmsFactory"
    )
    public void changeStateOrder(ChangeOrderStatusDTO changeOrderStatusDTO) {

        log.info(
                RECEIVING_MESSAGE_CHANGE_STATUS_QUEUE.getMessageWithCode(), objectToJson(changeOrderStatusDTO)
        );

        MessageConsumerChangeStatusLog.invoke(changeOrderStatusDTO);

        validateChangeStateUseCase.invoke(changeOrderStatusDTO);

        WriteChangeStatusDTO result = changeOrderStateUseCase.invoke(changeOrderStatusDTO);

        log.info(
                RESPONSE_CHANGE_STATUS_QUEUE.getMessageWithCode(),
                objectToJson(changeOrderStatusDTO),
                objectToJson(result)
        );
    }
}
