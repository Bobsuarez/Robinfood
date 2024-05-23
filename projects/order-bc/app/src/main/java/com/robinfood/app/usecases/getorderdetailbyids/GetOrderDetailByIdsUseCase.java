package com.robinfood.app.usecases.getorderdetailbyids;

import com.robinfood.app.mappers.OrderDetailMappers;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.repository.orderdetail.IOrderDetailRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetOrderDetailByIdsUseCase
 */
@Component
@Slf4j
public class GetOrderDetailByIdsUseCase implements IGetOrderDetailByIdsUseCase {

    private final IOrderDetailRepository orderDetailRepository;

    public GetOrderDetailByIdsUseCase(IOrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetailDTO> invoke(List<Long> orderId) {
        log.info("Starting process to get order detail by [{}] ids", orderId.size());

        return CollectionsKt.map(
                orderDetailRepository.findAllByOrderIdIn(orderId),
                OrderDetailMappers::toOrderDetailDTO
        );
    }
}
