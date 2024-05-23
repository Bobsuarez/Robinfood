package com.robinfood.app.usecases.createorderintegrations;

import com.robinfood.app.mappers.input.OrderIntegrationMappers;
import com.robinfood.core.dtos.request.order.OrderIntegrationDTO;
import com.robinfood.core.entities.OrderIntegrationEntity;
import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderIntegrationUseCase
 */
@Component
@Slf4j
public class CreateOrderIntegrationUseCase implements ICreateOrderIntegrationUseCase {

    private final IOrderIntegrationRepository orderIntegrationRepository;

    public CreateOrderIntegrationUseCase(IOrderIntegrationRepository orderIntegrationRepository) {
        this.orderIntegrationRepository = orderIntegrationRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<OrderIntegrationDTO> orderIntegrationDTOList) {

        log.info("Starting process to save order integration with data: [{}]",
            orderIntegrationDTOList);

        final List<OrderIntegrationEntity> orderIntegrationEntityList = CollectionsKt.map(
            orderIntegrationDTOList,
            OrderIntegrationMappers::toOrderIntegrationEntity
        );

        orderIntegrationRepository.saveAll(orderIntegrationEntityList);

        return CompletableFuture.completedFuture(true);
    }
}
