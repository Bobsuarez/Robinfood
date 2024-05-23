package co.com.robinfood.queue.chainqueue.concreteHandler;

import co.com.robinfood.queue.Exceptions.ValidateFieldsException;
import co.com.robinfood.queue.chainqueue.IProcessMessageQueue;
import co.com.robinfood.queue.persistencia.dto.ChangeOrderStatusDTO;
import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;
import co.com.robinfood.queue.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChangeStatusQueueHandler implements IProcessMessageQueue {

    private IProcessMessageQueue nextInChain;

    @Override
    public void setNextChain(IProcessMessageQueue iValidateDtoClassChain) {
        this.nextInChain = iValidateDtoClassChain;
    }

    @Override
    public void convertToString(DataRequestQueue dataRequestQueue) {

        try {
            var classToString = JsonUtils.jsonToClass(dataRequestQueue.getMessageJson(),
                    ChangeOrderStatusDTO.class);

            dataRequestQueue.setInformationMessage(classToString);

        } catch (JsonProcessingException e) {
            log.error(" [ ChangeStatusQueueHandler ] Error {}", e.getMessage());
            throw new ValidateFieldsException("The json does not belong to any DTO class");
        }
    }
}
