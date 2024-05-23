package com.robinfood.app.usecases.getuserorderdetail;

import com.robinfood.app.mappers.OrderDetailOrderMapper;
import com.robinfood.app.usecases.getstatusbyid.IGetStatusByIdUseCase;
import com.robinfood.app.usecases.gettransactionbyid.IGetTransactionFlowByIdUseCase;
import com.robinfood.core.dtos.TransactionFlowDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.entities.OrderDeviceEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderHistoryEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import com.robinfood.repository.orderdevice.IOrderDeviceRepository;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.APIConstants.PLATFORM_ID_DEFAULT;
import static com.robinfood.core.constants.APIConstants.TRANSACTION_FLOW_DEFAULT;

/**
 * Implementation of IGetUserOrderDetailByUIdUseCase
 */
@RequiredArgsConstructor
@Component
@Slf4j
@Transactional(readOnly = true)
public class GetUserOrderDetailByUIdUseCase implements IGetUserOrderDetailByUIdUseCase {

    private final IGetStatusByIdUseCase getStatusByIdUseCase;
    private final IGetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase;
    private final IGetUserOrderDetailAdditionalInfoUseCase getUserOrderDetailAdditionalInfoUseCase;
    private final IOrdersRepository ordersRepository;
    private final IOrderDeviceRepository orderDeviceRepository;
    private final IOrderHistoryRepository orderHistoryRepository;

    @Value("#{'${user.history-orders.diff-status}'.split(',')}")
    private List<Long> notStatusIds;

    @Override
    public ResponseOrderDetailDTO invoke(
        String orderUId,
        Long userId
    ) throws ResourceNotFoundException {

        log.info("Getting order detail with uid: {}", orderUId);

        OrderEntity orderEntity = getOrderEntity(orderUId, userId);

        Long flowId = getFlowByTransaction(orderEntity.getTransactionId());

        List<OrderHistoryEntity> orderHistory = getOrderHistoryEntities(orderEntity.getId());

        OrderHistoryEntity currentStatus = getCurrentStatusByOrderHistory(orderHistory, orderEntity.getStatusId());

        Long platformId = getPlatformIdByOrder(orderEntity.getId());

        ResponseOrderDetailDTO orderDetailDTO = OrderDetailOrderMapper.mapOrderEntityToResponseDTO(
            orderEntity
        );

        orderDetailDTO = orderDetailDTO.toBuilder()
            .origin(
                orderDetailDTO.getOrigin().toBuilder()
                    .platformId(platformId)
                    .build()
            )
            .flowId(flowId)
            .status(
                OrderDetailOrderMapper.mapOrderStatusToResponseDTO(
                        currentStatus,
                        getStatusByIdUseCase.invoke(currentStatus.getOrderStatusId())
                    ).toBuilder()
                    .trace(
                        orderHistory.stream()
                            .map(status -> OrderDetailOrderMapper.mapOrderTraceToResponseDTO(
                                status,
                                getStatusByIdUseCase.invoke(status.getOrderStatusId())
                            ))
                            .collect(Collectors.toList())
                    )
                    .build()
            )
            .build();

        return getUserOrderDetailAdditionalInfoUseCase.invoke(orderDetailDTO);
    }

    private OrderEntity getOrderEntity(
        String orderUId,
        Long userId
    ) throws ResourceNotFoundException {
        return ordersRepository.findByUidAndUserIdAndStatusIdNotIn(
            orderUId,
            userId,
            notStatusIds
        ).orElseThrow(() ->
            new ResourceNotFoundException("Order is not found")
        );
    }

    private List<OrderHistoryEntity> getOrderHistoryEntities(Long orderId) {
        return orderHistoryRepository.
            findAllByOrderId(orderId)
            .stream().sorted(
                Comparator.comparing(OrderHistoryEntity::getId).reversed()
            )
            .collect(Collectors.toList());
    }

    private OrderHistoryEntity getCurrentStatusByOrderHistory(
        List<OrderHistoryEntity> orderHistory,
        Long statusId
    ) {
        return orderHistory.stream()
            .filter(status -> status.getOrderStatusId().equals(statusId))
            .findFirst()
            .orElseThrow(() ->
                new GenericOrderBcException("Order history is not found")
            );
    }

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

    private Long getPlatformIdByOrder(Long orderId) {
        Optional<OrderDeviceEntity> orderDeviceEntity = orderDeviceRepository
            .findFirstByOrderIdOrderByIdDesc(orderId);

        if(orderDeviceEntity.isPresent()) {
            return orderDeviceEntity.get().getPlatformId();
        }

        log.info("Order device not found default platformId is returned: {}", PLATFORM_ID_DEFAULT);
        return PLATFORM_ID_DEFAULT;
    }

}
