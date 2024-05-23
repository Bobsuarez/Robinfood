package co.com.robinfood.queue.chaintopic.concreteHandler;

import co.com.robinfood.queue.Exceptions.ValidateFieldsException;
import co.com.robinfood.queue.chaintopic.IProcessMessageTopic;
import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;
import co.com.robinfood.queue.persistencia.dto.ordertocreatedto.OrderToCreateDTO;
import co.com.robinfood.queue.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderToCreatedTopicHandler implements IProcessMessageTopic {

    private IProcessMessageTopic nextInChain;

    @Override
    public void setNextChain(IProcessMessageTopic iValidateDtoClassChain) {
        this.nextInChain = iValidateDtoClassChain;
    }

    @Override
    public void convertToString(DataRequestQueue dataRequestQueue) {

        try {
            var classToString = JsonUtils.jsonToClass(dataRequestQueue.getMessageJson(),
                    OrderToCreateDTO.class);

            dataRequestQueue.setInformationMessage(classToString);

        } catch (JsonProcessingException e) {
            log.error(" [ OrderToCreatedTopicHandler ] Error {}", e.getMessage());
            throw new ValidateFieldsException("The json does not belong to any DTO class Orders");
        }
    }
}
