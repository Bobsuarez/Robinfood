package com.robinfood.app.usecases.createorderflagintegrations;

import com.robinfood.app.mappers.input.OrderFlagIntegrationMappers;
import com.robinfood.core.dtos.request.order.OrderFlagIntegrationDTO;
import com.robinfood.core.entities.OrderFlagIntegrationEntity;
import com.robinfood.repository.orderflagintegration.IOrderFlagIntegrationRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of ICreateOrderFlagIntegrationUseCase
 */
@Component
@Slf4j
public class CreateOrderFlagIntegrationsUseCase implements ICreateOrderFlagIntegrationUseCase {

    private final IOrderFlagIntegrationRepository orderFlagIntegrationRepository;

    public CreateOrderFlagIntegrationsUseCase(IOrderFlagIntegrationRepository orderFlagIntegrationRepository) {
        this.orderFlagIntegrationRepository = orderFlagIntegrationRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
        List<OrderFlagIntegrationDTO> orderFlagIntegrationDTOList) {

        log.info("Starting process to save order flag integration with data: {}",
                objectToJson(orderFlagIntegrationDTOList));

        final List<OrderFlagIntegrationEntity> orderFlagIntegrationEntities = CollectionsKt.map(
            orderFlagIntegrationDTOList,
            OrderFlagIntegrationMappers::toOrderFlagIntegrationEntity
        );

        orderFlagIntegrationRepository.saveAll(orderFlagIntegrationEntities);

        return CompletableFuture.completedFuture(true);
    }

}
