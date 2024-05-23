package com.robinfood.app.usecases.createorderflagcorporate;

import com.robinfood.app.mappers.input.InputOrderFlagCorporateMappers;
import com.robinfood.core.dtos.request.order.OrderFlagCorporateDTO;
import com.robinfood.core.entities.OrderFlagCorporateEntity;
import com.robinfood.repository.orderflagcorporate.IOrderFlagCorporateRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderFlagCorporateUseCase
 */
@Component
@Slf4j
public class CreateOrderFlagCorporateUseCase implements ICreateOrderFlagCorporateUseCase {

    private final IOrderFlagCorporateRepository orderFlagCorporateRepository;

    public CreateOrderFlagCorporateUseCase(IOrderFlagCorporateRepository orderFlagCorporateRepository) {
        this.orderFlagCorporateRepository = orderFlagCorporateRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<OrderFlagCorporateDTO> orderFlagCorporateDTO) {

        log.info("Starting process to save order flag corporate with data: {}",
            orderFlagCorporateDTO);

        final List<OrderFlagCorporateEntity> orderFlagCorporateEntities = CollectionsKt.map(
            orderFlagCorporateDTO,
            InputOrderFlagCorporateMappers::toOrderFlagCorporateEntity
        );

        orderFlagCorporateRepository.saveAll(orderFlagCorporateEntities);

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
