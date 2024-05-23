package co.com.robinfood.queue.chainqueue.concreteHandler;

import co.com.robinfood.queue.chainqueue.IProcessMessageQueue;
import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;
import co.com.robinfood.queue.persistencia.dto.queue.OrderCreatedQueueDTO;
import co.com.robinfood.queue.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderOrLocalServerQueueHandler implements IProcessMessageQueue {

    private IProcessMessageQueue nextInChain;

    @Override
    public void setNextChain(IProcessMessageQueue iValidateDtoClassChain) {
        this.nextInChain = iValidateDtoClassChain;
    }

    @Override
    public void convertToString(DataRequestQueue dataRequestQueue) {

        try {
            var classToString = JsonUtils.jsonToClassToString(dataRequestQueue.getMessageJson(),
                    OrderCreatedQueueDTO.class);

            dataRequestQueue.setInformationMessage(classToString);

        } catch (JsonProcessingException e) {
            log.error(" [ OrderOrLocalServerQueueHandler ] Error {}", e.getMessage());
            nextInChain.convertToString(dataRequestQueue);
        }
    }
}
