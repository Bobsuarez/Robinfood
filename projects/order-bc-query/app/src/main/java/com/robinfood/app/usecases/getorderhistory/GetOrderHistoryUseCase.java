package com.robinfood.app.usecases.getorderhistory;

import com.google.gson.Gson;
import com.robinfood.app.mappers.orderhistory.OrderHistoryMapper;
import com.robinfood.app.strategies.orderhistoryfilter.chain.IOrderHistoryFilterChain;
import com.robinfood.app.strategies.orderhistoryfilter.chain.OrderHistoryFilterBasicChain;
import com.robinfood.app.strategies.orderhistoryfilter.strategies.IOrderHistoryFilterStrategy;
import com.robinfood.app.usecases.getdeliverytypes.IGetDeliveryTypesUseCase;
import com.robinfood.app.usecases.getstatusbyorderids.IGetStatusByOrderIds;
import com.robinfood.app.usecases.getuserdatabyorderids.IGetUserDataByOrderIdsUseCase;
import com.robinfood.core.dtos.DeliveryTypeDTO;
import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import com.robinfood.core.dtos.response.PaginationDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.utilities.CalculateDateDisplacementUTCUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DELIVERY_TYPE_EXTERNAL_ID;

/**
 * Implementation of IGetOrderHistoryUseCase
 */
@Component
@Slf4j
public class GetOrderHistoryUseCase implements IGetOrderHistoryUseCase {

    private final IGetDeliveryTypesUseCase getDeliveryTypesUseCase;
    private final IGetStatusByOrderIds getStatusByOrderIds;
    private final IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;
    private final IOrderHistoryFilterChain orderHistoryFilterBasicChain;

    public GetOrderHistoryUseCase(
            IGetDeliveryTypesUseCase getDeliveryTypesUseCase,
            IGetStatusByOrderIds getStatusByOrderIds,
            IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase,
            OrderHistoryFilterBasicChain orderHistoryFilterBasicChain
    ) {
        this.getDeliveryTypesUseCase = getDeliveryTypesUseCase;
        this.getStatusByOrderIds = getStatusByOrderIds;
        this.getUserDataByOrderIdsUseCase = getUserDataByOrderIdsUseCase;
        this.orderHistoryFilterBasicChain = orderHistoryFilterBasicChain;
    }

    @Override
    public EntityDTO<OrderHistoryItemDTO> invoke(OrderHistoryRequestDTO orderHistoryRequestDTO) {

        log.info("Starting process to get order history with: {}", new Gson().toJson(orderHistoryRequestDTO));

        final List<Long> deliveryTypeIds = getDeliveryTypeIds(orderHistoryRequestDTO.getIsDelivery());

        final Map<String, LocalDateTime> localDateTime = CalculateDateDisplacementUTCUtil.getLocalDateTimeByRange(
                orderHistoryRequestDTO.getLocalDateEnd(),
                orderHistoryRequestDTO.getLocalDateStart(),
                orderHistoryRequestDTO.getTimeZone()
        );

        final IOrderHistoryFilterStrategy orderHistoryFilterStrategy = orderHistoryFilterBasicChain.handler(
                orderHistoryRequestDTO
        );

        final Page<OrderEntity> orderEntities = orderHistoryFilterStrategy.execute(
                deliveryTypeIds,
                localDateTime,
                orderHistoryRequestDTO
        );

        final List<Long> orderHistoryItemIds = orderEntities.stream()
                .map(OrderEntity::getId)
                .collect(Collectors.toList());

        final List<UserDataDTO> userDataItems = getUserDataByOrderIdsUseCase.invoke(orderHistoryItemIds);

        final List<Long> orderHistoryStatusesIds = orderEntities.stream()
                .map(OrderEntity::getStatusId)
                .collect(Collectors.toList());

        final List<OrderStatusDTO> statuses = getStatusByOrderIds.invoke(orderHistoryStatusesIds);

        final List<OrderHistoryItemDTO> pagesContent = OrderHistoryMapper.orderEntityToOrderHistoryItemDTO(
                orderEntities.getContent(),
                statuses,
                userDataItems
        );

        return new EntityDTO<>(
                pagesContent,
                PaginationDTO.builder()
                        .perPage(orderHistoryRequestDTO.getPerPage())
                        .page(orderHistoryRequestDTO.getCurrentPage())
                        .lastPage(orderEntities.getTotalPages())
                        .total(orderEntities.getTotalElements())
                        .build()
        );

    }

    private List<Long> getDeliveryTypeIds(Boolean isDelivery) {

        final List<DeliveryTypeDTO> deliveryTypes = getDeliveryTypesUseCase.invoke();

        List<Long> deliveryTypeIds = deliveryTypes.stream()
                .map(DeliveryTypeDTO::getId)
                .filter(id -> !DELIVERY_TYPE_EXTERNAL_ID.equals(id))
                .collect(Collectors.toList());

        if (Boolean.TRUE.equals(isDelivery)) {
            deliveryTypeIds.clear();

            deliveryTypeIds = deliveryTypes.stream()
                    .map(DeliveryTypeDTO::getId)
                    .filter(DELIVERY_TYPE_EXTERNAL_ID::equals)
                    .collect(Collectors.toList());
        }

        return deliveryTypeIds;
    }
}
