package com.robinfood.app.usecases.messageproducer;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.repository.queue.activemq.IProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Implementation of IMessageProducerUseCase
 */
@Slf4j
@Component
public class MessageProducerUseCase implements IMessageProducerUseCase {

    private final IProducerRepository producerRepository;

    public MessageProducerUseCase(
            IProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public void invoke(
            @NotNull final OrderToCreateDTO orderToCreateDTO,
            String messageFrom,
            String messageCountry
    ) {
        producerRepository.sendOrderToCreateMessage(orderToCreateDTO, messageFrom, messageCountry);
    }
}
