package com.robinfood.app.usecases.createorderbrandhistory;

import com.robinfood.app.mappers.input.OrderBrandHistoryMappers;
import com.robinfood.app.usecases.getbrandidsbyorder.IGetBrandIdsByOrder;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.OrderBrandHistoryDTO;
import com.robinfood.core.entities.OrderBrandHistoryEntity;
import com.robinfood.repository.orderbrandhistory.IOrderBrandHistoryRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.ORDER_STATUS_CREATED;

/**
 * Implementation of ICreateOrderBrandHistoryUseCase
 */
@Component
@Slf4j
public class CreateOrderBrandHistoryUseCase implements ICreateOrderBrandHistoryUseCase {

    private final IGetBrandIdsByOrder getBrandIdsByOrder;
    private final IOrderBrandHistoryRepository orderBrandHistoryRepository;

    public CreateOrderBrandHistoryUseCase(
            IGetBrandIdsByOrder getBrandIdsByOrder,
            IOrderBrandHistoryRepository orderBrandHistoryRepository
    ) {
        this.getBrandIdsByOrder = getBrandIdsByOrder;
        this.orderBrandHistoryRepository = orderBrandHistoryRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
            List<FinalProductDTO> finalProductDTO,
            Long orderId,
            Double orderValueTotal,
            Boolean paid,
            Double totalPaymentMethods,
            Long userId
    ) {

        log.info(
                "Starting process to save order brand history with final products: [{}], order id: {}, order value total: {}, paid: {}, total payment methods: {} and user id: {}",
                finalProductDTO, orderId, orderValueTotal, paid, totalPaymentMethods, userId);

        List<Long> brandIds = getBrandIdsByOrder.invoke(finalProductDTO);

        List<OrderBrandHistoryDTO> orderBrandHistoryDTOList = new ArrayList<>();

        for (Long brandId : brandIds) {
            orderBrandHistoryDTOList.add(
                    new OrderBrandHistoryDTO(
                            brandId,
                            orderId,
                            ORDER_STATUS_CREATED,
                            userId
                    )
            );
        }

        List<OrderBrandHistoryEntity> orderBrandHistoryEntities = CollectionsKt.map(
                orderBrandHistoryDTOList,
                OrderBrandHistoryMappers::toOrderBrandHistoryEntity
        );

        orderBrandHistoryRepository.saveAll(orderBrandHistoryEntities);

        return CompletableFuture.completedFuture(true);
    }
}
