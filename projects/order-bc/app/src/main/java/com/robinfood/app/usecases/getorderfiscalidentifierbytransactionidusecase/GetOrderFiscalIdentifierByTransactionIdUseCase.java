package com.robinfood.app.usecases.getorderfiscalidentifierbytransactionidusecase;

import com.robinfood.app.mappers.OrderFiscalIdentifierMappers;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.entities.OrderFiscalIdentifierEntity;
import com.robinfood.repository.orderfiscalidentifier.IOrderFiscalIdentifierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Implementation of IGetOrderFiscalIdentifierByTransactionIdUseCase
 */
@Component
@Slf4j
public class GetOrderFiscalIdentifierByTransactionIdUseCase implements IGetOrderFiscalIdentifierByTransactionIdUseCase {

    private final IOrderFiscalIdentifierRepository orderFiscalIdentifierRepository;

    public GetOrderFiscalIdentifierByTransactionIdUseCase(
            IOrderFiscalIdentifierRepository orderFiscalIdentifierRepository
    ) {
        this.orderFiscalIdentifierRepository = orderFiscalIdentifierRepository;
    }

    @Override
    public OrderFiscalIdentifierDTO invoke(Long transactionId) {

        log.info("Starting get order fiscal identifier by transaction id: {}", transactionId);

        final OrderFiscalIdentifierEntity findOrderFiscalIdentifierEntityByTransactionId =
                orderFiscalIdentifierRepository.findByTransactionId(transactionId);

        if (Objects.isNull(findOrderFiscalIdentifierEntityByTransactionId)) {

            log.info("Not found get order fiscal identifier by transaction id: {}", transactionId);
            return new OrderFiscalIdentifierDTO();
        }

        log.info(
                "Found get order fiscal identifier by transaction id: {}, data: {}",
                transactionId,
                findOrderFiscalIdentifierEntityByTransactionId
        );

        return OrderFiscalIdentifierMappers.toOrderFiscalIdentifierDTO(findOrderFiscalIdentifierEntityByTransactionId);
    }
}
