package com.robinfood.app.usecases.createorderdiscountproduct;

import com.robinfood.app.mappers.input.InputDiscountMappers;
import com.robinfood.core.dtos.request.order.DiscountDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.entities.OrderDiscountEntity;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.PRODUCT_DISCOUNT_ID;
import static com.robinfood.core.constants.GlobalConstants.PRODUCT_TYPE_DISCOUNT_ID;

/**
 * Implementation of ICreateOrderDiscountProductUseCase
 */
@Component
@Slf4j
public class CreateOrderDiscountProductUseCase implements ICreateOrderDiscountProductUseCase {

    private final IOrderDiscountCRUDRepository orderDiscountCRUDRepository;

    public CreateOrderDiscountProductUseCase(IOrderDiscountCRUDRepository orderDiscountCRUDRepository) {
        this.orderDiscountCRUDRepository = orderDiscountCRUDRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<OrderDTO> orderDTOList, List<Long> orderIds) {
        log.info("Starting process to save order discount with products: [{}], and order ids: [{}]",
                orderDTOList,
                orderIds
        );

        List<DiscountDTO> discountDTOList = addOrderDiscountByProduct(orderDTOList, orderIds);

        final List<OrderDiscountEntity> orderDiscountEntities = CollectionsKt.map(
                discountDTOList,
                InputDiscountMappers::toOrderDiscountEntity
        );

        orderDiscountCRUDRepository.saveAll(orderDiscountEntities);

        return CompletableFuture.completedFuture(true);
    }

    /**
     * Allows you to add product discounts
     *
     * @param orderDTOList orders list
     * @param orderIds     orders ids list
     */
    private List<DiscountDTO> addOrderDiscountByProduct(
            List<OrderDTO> orderDTOList,
            List<Long> orderIds
    ) {

        List<FinalProductDTO> finalProductsDTO = orderDTOList.iterator().next().getFinalProducts();

        List<DiscountDTO> discountDTOList = new ArrayList<>();

        for (Long orderId : orderIds) {
            for (FinalProductDTO finalProductDTO : finalProductsDTO) {

                final Long getOrderId = orderId;

                List<DiscountDTO> getDiscounts = finalProductDTO.getDiscounts().stream()
                        .filter(finalProductDiscountDTO -> Boolean.TRUE.equals(
                                finalProductDiscountDTO.getIsProductDiscount()
                        )).map(finalProductDiscountDTO -> DiscountDTO.builder()
                                .id(PRODUCT_DISCOUNT_ID)
                                .isConsumptionDiscount(DEFAULT_BOOLEAN_FALSE_VALUE)
                                .orderFinalProductId(finalProductDTO.getId())
                                .orderId(getOrderId)
                                .typeId(PRODUCT_TYPE_DISCOUNT_ID)
                                .value(finalProductDiscountDTO.getValue() * finalProductDTO.getQuantity())
                                .build())
                        .collect(Collectors.toList());

                discountDTOList.addAll(getDiscounts);
            }
        }
        return discountDTOList;
    }
}
