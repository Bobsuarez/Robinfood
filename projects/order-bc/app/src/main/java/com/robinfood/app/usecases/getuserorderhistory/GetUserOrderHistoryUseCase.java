package com.robinfood.app.usecases.getuserorderhistory;

import com.robinfood.app.usecases.getuserorder.IGetUserResponseOrderByEntityUseCase;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of IGetOrderHistoryByUserIdUseCase
 */
@Component
@Slf4j
@Transactional(readOnly = true)
public class GetUserOrderHistoryUseCase implements IGetUserOrderHistoryUseCase {

    private final IGetUserResponseOrderByEntityUseCase getUserResponseOrderByEntityUseCase;
    private final IOrdersRepository ordersRepository;

    @Value("#{'${user.history-orders.diff-status}'.split(',')}")
    private List<Long> notStatusIds;

    public GetUserOrderHistoryUseCase(
            IGetUserResponseOrderByEntityUseCase getUserResponseOrderByEntityUseCase,
            IOrdersRepository ordersRepository
    ) {
        this.getUserResponseOrderByEntityUseCase = getUserResponseOrderByEntityUseCase;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Page<ResponseOrderDTO> invoke(
            Integer currentPage,
            Integer perPage,
            Long userId
    ) {
        log.info("Getting order detail with user id: {}", userId);

        final Page<OrderEntity> orderHistoryPage = ordersRepository.findAllByUserIdAndStatusIdNotInOrderByCreatedAtDesc(
                userId,
                notStatusIds,
                PageRequest.of(currentPage - 1, perPage)
        );

        return orderHistoryPage
                .map(getUserResponseOrderByEntityUseCase::invoke);
    }

}
