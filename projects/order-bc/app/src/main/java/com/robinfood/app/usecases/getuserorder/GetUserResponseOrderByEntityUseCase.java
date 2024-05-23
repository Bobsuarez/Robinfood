package com.robinfood.app.usecases.getuserorder;

import com.robinfood.app.mappers.OrderHistoryMapper;
import com.robinfood.app.usecases.getstatusbyid.IGetStatusByIdUseCase;
import com.robinfood.app.usecases.gettransactionbyid.IGetTransactionFlowByIdUseCase;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.TransactionFlowDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.entities.OrderDeviceEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderdevice.IOrderDeviceRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.APIConstants.TRANSACTION_FLOW_DEFAULT;
import static java.util.Objects.nonNull;

/**
 * Implementation of IGetUserResponseOrderByEntityUseCase
 */
@Component
@Slf4j
@Transactional(readOnly = true)
public class GetUserResponseOrderByEntityUseCase implements IGetUserResponseOrderByEntityUseCase {

    private final IGetStatusByIdUseCase getStatusByIdUseCase;
    private final IGetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase;
    private final IOrderDeviceRepository orderDeviceRepository;
    private final IOrderFinalProductRepository orderFinalProductRepository;

    public GetUserResponseOrderByEntityUseCase(
            IGetStatusByIdUseCase getStatusByIdUseCase,
            IGetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase,
            IOrderDeviceRepository orderDeviceRepository,
            IOrderFinalProductRepository orderFinalProductRepository
    ) {
        this.getStatusByIdUseCase = getStatusByIdUseCase;
        this.getTransactionFlowByIdUseCase = getTransactionFlowByIdUseCase;
        this.orderDeviceRepository = orderDeviceRepository;
        this.orderFinalProductRepository = orderFinalProductRepository;
    }

    @Override
    public ResponseOrderDTO invoke(
            OrderEntity order
    ) {
        log.info("Getting response order: {}", order.getId());

        List<OrderFinalProductEntity> finalProducts = orderFinalProductRepository.
                findAllByOrderId(order.getId());

        Optional<OrderDeviceEntity> orderDeviceEntity = orderDeviceRepository
                .findFirstByOrderIdOrderByIdDesc(order.getId());

        OrderStatusDTO orderStatusDTO = getOrderStatus(order.getStatusId());

        ResponseOrderDTO orderHistory = OrderHistoryMapper.mapOrderEntityToResponseDTO(
                order
        );

        if (orderDeviceEntity.isPresent()) {
            orderHistory = orderHistory.toBuilder()
                    .platformId(orderDeviceEntity.get().getPlatformId())
                    .build();
        }

        if (nonNull(orderStatusDTO)) {
            orderHistory = orderHistory.toBuilder()
                    .status(
                            OrderHistoryMapper.mapOrderStatusToResponseDTO(
                                    orderStatusDTO
                            )
                    )
                    .build();
        }

        return orderHistory.toBuilder()
                .flowId(getFlowByTransaction(order.getTransactionId()))
                .brands(
                        finalProducts.stream()
                                .map(OrderHistoryMapper::getBrandFinalProduct)
                                .distinct()
                                .collect(Collectors.toList())
                )
                .products(
                        finalProducts.stream()
                                .map(OrderHistoryMapper::mapFinalProductToResponseDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    /**
     * Method that get flow id without GenericOrderBcException
     *
     * @param transactionId Transaction id
     * @return Flow id
     */
    private Long getFlowByTransaction(Long transactionId) {
        try {
            TransactionFlowDTO transactionFlowDTO = getTransactionFlowByIdUseCase.invoke(transactionId);
            return transactionFlowDTO.getFlowId();
        } catch (GenericOrderBcException e) {
            log.info("Transaction flow not found exception", e);
            log.info("Default flow is returned: {}", TRANSACTION_FLOW_DEFAULT);

            return TRANSACTION_FLOW_DEFAULT;
        }
    }

    /**
     * Method that get order status without NotFoundException
     *
     * @param orderStatusId order status id
     * @return Order status
     */
    private OrderStatusDTO getOrderStatus(Long orderStatusId) {
        try {
            return getStatusByIdUseCase.invoke(orderStatusId);
        } catch (NotFoundException e) {
            log.error("Error getting order status, id:{}, {}", orderStatusId, e);
        }
        return null;
    }

}
