package com.robinfood.app.usecases.paymentmethodproducer;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.repository.queue.activemq.IProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentMethodProducerUseCase implements IPaymentMethodProducerUseCase{

    private final IProducerRepository producerRepository;

    public PaymentMethodProducerUseCase(IProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public void invoke(ChangeOrderStatusDTO changeOrderStatusDTO) {
        producerRepository.sendChangeStatusMessage(changeOrderStatusDTO);
    }
}
