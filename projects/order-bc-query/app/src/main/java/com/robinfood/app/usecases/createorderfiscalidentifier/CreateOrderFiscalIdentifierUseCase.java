package com.robinfood.app.usecases.createorderfiscalidentifier;

import com.robinfood.app.mappers.OrderFiscalIdentifierMappers;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.entities.OrderFiscalIdentifierEntity;
import com.robinfood.repository.orderfiscalidentifier.IOrderFiscalIdentifierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderFiscalIdentifierUseCase
 */
@Component
@Slf4j
public class CreateOrderFiscalIdentifierUseCase implements ICreateOrderFiscalIdentifierUseCase {

    private final IOrderFiscalIdentifierRepository orderFiscalIdentifierRepository;

    public CreateOrderFiscalIdentifierUseCase(IOrderFiscalIdentifierRepository orderFiscalIdentifierRepository) {
        this.orderFiscalIdentifierRepository = orderFiscalIdentifierRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
            OrderFiscalIdentifierDTO orderFiscalIdentifierDTO,
            Long transactionId
    ) {

        log.info("Starting process to save order fiscal identifier with data (DTO): {}", orderFiscalIdentifierDTO);

        final OrderFiscalIdentifierEntity saveOrderFiscalIdentifierEntity = OrderFiscalIdentifierMappers
                .toOrderFiscalIdentifierEntity(orderFiscalIdentifierDTO, transactionId);

        orderFiscalIdentifierRepository.save(saveOrderFiscalIdentifierEntity);

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
