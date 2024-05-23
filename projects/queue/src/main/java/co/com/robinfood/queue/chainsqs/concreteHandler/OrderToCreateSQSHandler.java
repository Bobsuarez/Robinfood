package co.com.robinfood.queue.chainsqs.concreteHandler;

import co.com.robinfood.queue.Exceptions.ValidateFieldsException;
import co.com.robinfood.queue.chainsqs.IProcessMessageSQS;
import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;
import co.com.robinfood.queue.persistencia.dto.orderdetail.OrderDetailDTO;
import co.com.robinfood.queue.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class OrderToCreateSQSHandler implements IProcessMessageSQS {

    private IProcessMessageSQS nextInChain;

    @Override
    public void setNextChain(IProcessMessageSQS iValidateDtoClassChain) {
        this.nextInChain = iValidateDtoClassChain;
    }

    @Override
    public void convertToString(DataRequestQueue dataRequestQueue) {

        try {
            var classToString = JsonUtils.parseJsonToArray(dataRequestQueue.getMessageJson(),
                    OrderDetailDTO.class);

            if (Objects.isNull(classToString)) {
                throw new ValidateFieldsException("");
            }

            dataRequestQueue.setInformationMessageSQS(classToString);

        } catch (ValidateFieldsException e) {
            log.error(" [ OrderToCreatedTopicHandler ] Error {}", e.getMessage());
            throw new ValidateFieldsException("to send an SQS message you must send a list of DTOs");
        }
    }
}
