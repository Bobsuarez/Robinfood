package com.robinfood.app.usecases.getorderstatushistorybyorderuuid;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_FORMAT_YYYY_MM_DD_AND_HH_MM_SS;

import com.robinfood.app.mappers.orderstatushistory.OrderStatusMapper;
import com.robinfood.app.mappers.orderstatushistory.OrderUserDataMapper;
import com.robinfood.core.dtos.orderstatushistory.OrderStatusDTO;
import com.robinfood.core.dtos.orderstatushistory.OrderStatusHistoryDTO;
import com.robinfood.core.dtos.orderstatushistory.OrderUserDataDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderHistoryEntity;
import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.status.IStatusRepository;
import com.robinfood.repository.userdata.IUserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetOrderStatusHistoryByOrderUuidUseCase implements IGetOrderStatusHistoryByOrderUuidUseCase {

    private final IOrderHistoryRepository orderHistoryRepository;
    private final IOrdersRepository ordersRepository;
    private final IStatusRepository statusRepository;

    public GetOrderStatusHistoryByOrderUuidUseCase(
            IOrderHistoryRepository orderHistoryRepository,
            IOrdersRepository ordersRepository,
            IStatusRepository statusRepository
    ) {
        this.orderHistoryRepository = orderHistoryRepository;
        this.ordersRepository = ordersRepository;
        this.statusRepository = statusRepository;
    }

    private OrderEntity getOrderEntityByUuid(String uuid) {
        return this.ordersRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    private List<OrderHistoryEntity> getOrderHistoryEntitiesByOrderId(Long orderId) {
        return this.orderHistoryRepository.findAllByOrderId(orderId);
    }

    private List<StatusEntity> getStatusEntitiesByOrderHistoryEntities(List<OrderHistoryEntity> orderHistoryEntities) {

        final List<Long> getStatusIds = orderHistoryEntities.stream().map(OrderHistoryEntity::getOrderStatusId)
                .collect(Collectors.toList());

        return statusRepository.findAllByIdIn(getStatusIds);
    }

    private StatusEntity searchStatusEntityByStatusIdHistory(
            List<StatusEntity> statusEntities,
            Long statusIdHistory
    ) {
        return statusEntities.stream()
                .filter(statusEntity -> statusEntity.getId().equals(statusIdHistory))
                .findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found"));
    }

    @Override
    public List<OrderStatusHistoryDTO> invoke(String uuid) throws ResourceNotFoundException {

        List<OrderStatusHistoryDTO> orderStatusHistoryDTOS = new ArrayList<>();

        final OrderEntity orderEntity = getOrderEntityByUuid(uuid);
        final List<OrderHistoryEntity> orderHistoryEntities = getOrderHistoryEntitiesByOrderId(orderEntity.getId());
        final List<StatusEntity> statusEntities = getStatusEntitiesByOrderHistoryEntities(orderHistoryEntities);

        for (OrderHistoryEntity orderHistoryEntity : orderHistoryEntities) {

            final StatusEntity statusEntity = searchStatusEntityByStatusIdHistory(
                    statusEntities,
                    orderHistoryEntity.getOrderStatusId()
            );

            final OrderStatusDTO orderStatusDTOS = OrderStatusMapper.orderStatusEntityToDTO(statusEntity);

            final OrderStatusHistoryDTO orderStatusHistoryDTO = OrderStatusHistoryDTO.builder()
                    .createdAt(
                            orderHistoryEntity.getCreatedAt().format(DEFAULT_FORMAT_YYYY_MM_DD_AND_HH_MM_SS)
                    )
                    .id(orderHistoryEntity.getId())
                    .userId(orderHistoryEntity.getUserId())
                    .observation(orderHistoryEntity.getObservation())
                    .status(orderStatusDTOS)
                    .build();

            orderStatusHistoryDTOS.add(orderStatusHistoryDTO);
        }

        return orderStatusHistoryDTOS;
    }
}
