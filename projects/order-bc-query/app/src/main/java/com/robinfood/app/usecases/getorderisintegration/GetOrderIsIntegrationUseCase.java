package com.robinfood.app.usecases.getorderisintegration;

import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of IGetOrderIsIntegration
 */
@Service
@Slf4j
public class GetOrderIsIntegrationUseCase implements IGetOrderIsIntegrationUseCase {

    private final IOrderIntegrationRepository orderIntegrationRepository;

    public GetOrderIsIntegrationUseCase(IOrderIntegrationRepository orderIntegrationRepository) {
        this.orderIntegrationRepository = orderIntegrationRepository;
    }

    @Override
    public Boolean invoke(Long orderId) {
        log.info("Starting process to get order is integration by order id: {}", orderId);

        return orderIntegrationRepository.existsById(orderId);
    }

}
