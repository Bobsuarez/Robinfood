package com.robinfood.app.usecases.writechangestatusqueue;

import com.robinfood.app.mappers.WriteChangeStatusMapper;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import com.robinfood.repository.queue.IProducerOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.robinfood.core.constants.GlobalConstants.ORDER_DISCARDED;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.ORDER_DISCARDED_STATUS;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.WRITE_CHANGE_STATUS_QUEUE;
import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

@Component
@Slf4j
public class WriteChangeStatusQueueUseCase implements IWriteChangeStatusQueueUseCase {

    private final IProducerOrderRepository producerOrderRepository;
    private final WriteChangeStatusMapper writeChangeStatusMapper;

    public WriteChangeStatusQueueUseCase(
            IProducerOrderRepository producerOrderRepository,
            WriteChangeStatusMapper writeChangeStatusMapper
    ) {
        this.producerOrderRepository = producerOrderRepository;
        this.writeChangeStatusMapper = writeChangeStatusMapper;
    }

    @Override
    public WriteChangeStatusDTO invoke(ChangeOrderStatusDTO nextState) {

        log.info(WRITE_CHANGE_STATUS_QUEUE.getMessageWithCode(), objectToJson(nextState));

        if (Boolean.FALSE.equals(ORDER_DISCARDED.equals(nextState.getStatusCode()))) {

            WriteChangeStatusDTO writeChangeStatusDTO = writeChangeStatusMapper
                    .changeOrderStatusDTOToWriteChangeStatusDTO(nextState);

            producerOrderRepository.sendChangeStatusMessage(writeChangeStatusDTO);

            return writeChangeStatusDTO;
        }

        log.info(ORDER_DISCARDED_STATUS.getMessageWithCode(), objectToJson(nextState.getStatusCode()));

        return new WriteChangeStatusDTO();
    }
}
