package com.robinfood.app.usecases.getstateorders;

import com.robinfood.app.mappers.OrderStateMappers;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderIntegrationEntity;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.core.entities.TransactionEntity;
import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.status.IStatusRepository;
import com.robinfood.repository.transaction.ITransactionCRUDRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

/**
 * Implementation of IGetStatusByIdUseCase
 */
@Component
@Slf4j
public class GetStateOrderUseCase implements IGetStateOrderUseCase {

    private final IOrdersRepository ordersRepository;
    private final IStatusRepository statusRepository;
    private final IOrderIntegrationRepository orderIntegrationRepository;
    private final OrderStateMappers orderStateMappers;

    private final ITransactionCRUDRepository transactionRepository;

    private OrderStateDTO orderStateDTO;

    public GetStateOrderUseCase(
            IOrdersRepository ordersRepository,
            IStatusRepository statusRepository,
            OrderStateMappers orderStateMappers,
            IOrderIntegrationRepository orderIntegrationRepository,
            ITransactionCRUDRepository transactionRepository) {

        this.ordersRepository = ordersRepository;
        this.statusRepository = statusRepository;
        this.orderStateMappers = orderStateMappers;
        this.orderIntegrationRepository = orderIntegrationRepository;
        this.transactionRepository = transactionRepository;
        orderStateDTO = new OrderStateDTO();
    }

    @Override
    public OrderStateDTO invoke(Long idOrder) {

        log.info("Getting state with id: {}", idOrder);
        OrderEntity orderEntity = ordersRepository.findById(idOrder).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Order with id [%s] is not found", idOrder)
                )
        );

        return getOrderStateDTO(orderEntity);
    }

    @Override
    public OrderStateDTO invokeUuid(String uuid) {

        log.info("Getting state with uuid: {}", uuid);
        OrderEntity orderEntity = ordersRepository.findByUuid(uuid).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Order with uuid [%s] is not found", uuid)
                )
        );

        return getOrderStateDTO(orderEntity);
    }

    @Override
    public OrderStateDTO invokeDeliveryIntegrationId(String integrationId) {

        log.info("Getting state with integration Id: {}", integrationId);

        OrderIntegrationEntity orderIntegrationEntity = orderIntegrationRepository.findByIntegrationId(integrationId)
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Integration with id [%s] is not found", integrationId)
                        )
                );

        OrderEntity orderEntity = ordersRepository.findById(orderIntegrationEntity.getOrderId()).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Order with id [%s] is not found", orderIntegrationEntity.getOrderId())
                )
        );

        return getOrderStateDTO(orderEntity);
    }

    public OrderStateDTO getOrderStateDTO(OrderEntity orderEntity) {

        TransactionEntity transactionEntity = transactionRepository.findById(orderEntity.getTransactionId())
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Transaction with id [%s] is not found", orderEntity.getTransactionId())
                        )
                );

        StatusEntity status = statusRepository.findById(orderEntity.getStatusId()).orElseThrow(() ->
                new IllegalArgumentException("Status is not created"));

        if (status.getOrder().compareTo(BigDecimal.valueOf(status.getParentId())) >
                GlobalConstants.DEFAULT_INTEGER_VALUE) {

            StatusEntity parent = statusRepository.findById(status.getParentId()).orElseThrow(() ->
                    new IllegalArgumentException("sub state not created"));

            orderStateDTO = orderStateMappers.buildOrderState(status, parent);
            orderStateDTO.setOrderUuid(orderEntity.getUuid());

            orderStateDTO.setOrderId(orderEntity.getId());
            orderStateDTO.setTransactionId(orderEntity.getTransactionId());
            orderStateDTO.setTransactionUuid(transactionEntity.getUniqueIdentifier());
            orderStateDTO.setOriginId(orderEntity.getOriginId());
            orderStateDTO.setIsPaid(orderEntity.getPaid());

            return orderStateDTO;

        }

        orderStateDTO = orderStateMappers.buildOrderState(status);
        orderStateDTO.setOrderUuid(orderEntity.getUuid());
        orderStateDTO.setOrderId(orderEntity.getId());
        orderStateDTO.setTransactionId(orderEntity.getTransactionId());
        orderStateDTO.setTransactionUuid(transactionEntity.getUniqueIdentifier());
        orderStateDTO.setOriginId(orderEntity.getOriginId());
        orderStateDTO.setIsPaid(orderEntity.getPaid());

        return orderStateDTO;
    }
}
