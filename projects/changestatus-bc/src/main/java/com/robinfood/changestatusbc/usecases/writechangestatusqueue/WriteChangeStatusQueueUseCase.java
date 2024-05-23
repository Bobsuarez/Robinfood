package com.robinfood.changestatusbc.usecases.writechangestatusqueue;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;
import com.robinfood.changestatusbc.mappers.WriteChangeStatusMapper;
import com.robinfood.changestatusbc.repositories.queue.IProducerOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.robinfood.changestatusbc.configs.constants.GlobalConstants.ORDER_DISCARDED;
import static com.robinfood.changestatusbc.enums.AppTraceEnum.ORDER_DISCARDED_STATUS;
import static com.robinfood.changestatusbc.enums.AppTraceEnum.WRITE_CHANGE_STATUS_QUEUE;
import static com.robinfood.changestatusbc.utilities.ObjectMapperSingleton.objectToJson;

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
