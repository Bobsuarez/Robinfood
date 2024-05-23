package com.robinfood.app.usecases.getuseractiveorder;

import com.robinfood.app.usecases.getuserorder.IGetUserResponseOrderByEntityUseCase;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IGetUserActiveOrderUseCase
 */
@Component
@Slf4j
@Transactional(readOnly = true)
public class GetUserActiveOrderUseCase implements IGetUserActiveOrderUseCase {

    private final IGetUserResponseOrderByEntityUseCase getUserResponseOrderByEntityUseCase;
    private final IOrdersRepository ordersRepository;

    @Value("#{'${user.active-orders.diff-status}'.split(',')}")
    private List<Long> notActiveStatusIds;
    @Value("${user.active-orders.origin}")
    private Long originId;
    @Value("${user.active-orders.hours}")
    private Integer minusHours;

    public GetUserActiveOrderUseCase(
            IGetUserResponseOrderByEntityUseCase getUserResponseOrderByEntityUseCase,
            IOrdersRepository ordersRepository
    ) {
        this.getUserResponseOrderByEntityUseCase = getUserResponseOrderByEntityUseCase;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<ResponseOrderDTO> invoke(
            Long userId
    ) {
        log.info("Getting active orders with user id: {}", userId);

        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);

        final List<OrderEntity> activeOrders = ordersRepository
                .findAllByUserIdAndOriginIdAndStatusIdNotInAndCreatedAtBetweenOrderByCreatedAtDesc(
                        userId,
                        originId,
                        notActiveStatusIds,
                        currentDateTime.minusHours(minusHours),
                        currentDateTime
                );

        return activeOrders.stream()
                .map(getUserResponseOrderByEntityUseCase::invoke)
                .collect(Collectors.toList());
    }

}
