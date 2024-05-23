package com.robinfood.app.usecases.getorderintegration;

import com.robinfood.app.mappers.OrderIntegrationMappers;
import com.robinfood.core.dtos.OrderIntegrationDTO;
import com.robinfood.core.entities.OrderIntegrationEntity;
import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Implementation of IGetOrderIntegrationUseCase
 */
@Service
@Slf4j
public class GetOrderIntegrationUseCase implements IGetOrderIntegrationUseCase {

    private final IOrderIntegrationRepository orderIntegrationRepository;

    public GetOrderIntegrationUseCase(IOrderIntegrationRepository orderIntegrationRepository) {
        this.orderIntegrationRepository = orderIntegrationRepository;
    }

    @Override
    public OrderIntegrationDTO invoke(Long orderId) {

        log.info("Starting process to get order integration by order id: {}", orderId);

        OrderIntegrationEntity orderIntegrationEntity = orderIntegrationRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(orderId.toString()));

        return OrderIntegrationMappers.toOrderIntegrationDTO(orderIntegrationEntity);
    }
}
