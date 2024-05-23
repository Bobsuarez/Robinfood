package com.robinfood.app.usecases.createorderdiscount;

import com.robinfood.app.mappers.input.InputDiscountMappers;
import com.robinfood.core.dtos.request.order.DiscountDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.entities.OrderDiscountEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import com.robinfood.repository.orderdiscount.IOrderDiscountRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderDiscountUseCase
 */
@Component
@Slf4j
public class CreateOrderDiscountUseCase implements ICreateOrderDiscountUseCase {

    private final IOrderDiscountCRUDRepository orderDiscountCRUDRepository;
    private final IOrderDiscountRepository orderDiscountRepository;

    private final IOrderFinalProductRepository orderFinalProductDataSource;

    public CreateOrderDiscountUseCase(
            IOrderDiscountCRUDRepository orderDiscountCRUDRepository,
            IOrderDiscountRepository orderDiscountRepository,
            IOrderFinalProductRepository orderFinalProductDataSource
    ) {
        this.orderDiscountCRUDRepository = orderDiscountCRUDRepository;
        this.orderDiscountRepository = orderDiscountRepository;
        this.orderFinalProductDataSource = orderFinalProductDataSource;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<OrderDTO> orderDTOList, List<Long> orderIds) {

        log.info("Starting process to save order discount with orders: [{}], and order ids: [{}]",
                orderDTOList, orderIds);

        final List<DiscountDTO> orderDiscountDTOList = new ArrayList<>();
        for (int i = 0; i < orderIds.size(); i++) {

            List<OrderFinalProductEntity> listProductFinal = orderFinalProductDataSource.
                    findAllByOrderId(orderIds.get(i));

            Map<Long,Long> finalProducts = new HashMap<>();

            for (OrderFinalProductEntity product:  listProductFinal) {
                finalProducts.put(product.getFinalProductId(),product.getId());
            }

            final Long orderId = orderIds.get(i);
            final List<DiscountDTO> discountDTOList = orderDTOList.get(i).getDiscounts();

            for (DiscountDTO discountDTO : discountDTOList) {

                final DiscountDTO orderDiscountDTO = new DiscountDTO(
                        discountDTO.getId(),
                        discountDTO.getIsProductDiscount(),
                        discountDTO.getIsConsumptionDiscount(),
                        finalProducts.get(discountDTO.getOrderFinalProductId()),
                        orderId,
                        discountDTO.getTypeId(),
                        discountDTO.getValue()
                );
                orderDiscountDTOList.add(orderDiscountDTO);
            }
        }

        final List<OrderDiscountEntity> orderDiscountEntities = CollectionsKt.map(
                orderDiscountDTOList,
                InputDiscountMappers::toOrderDiscountEntity
        );

        final List<OrderDiscountEntity> orderDiscountsStored =
                (List<OrderDiscountEntity>) orderDiscountCRUDRepository.saveAll(orderDiscountEntities);

        orderDiscountRepository.setLocalOrderDiscounts(orderDiscountsStored);

        return CompletableFuture.completedFuture(true);
    }
}
