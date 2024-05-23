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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

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

        log.info("Starting process to save order discount with orders: {}, and order ids: [{}]",
                objectToJson(orderDTOList), orderIds);

        final List<DiscountDTO> orderDiscountDTOList = new ArrayList<>();

        for (int i = 0; i < orderIds.size(); i++) {

            List<OrderFinalProductEntity> listProductFinal = orderFinalProductDataSource.
                    findAllByOrderId(orderIds.get(i));

            Map<String, List<Long>> groupFinalProducts = listProductFinal.stream()
                    .collect(Collectors.groupingBy(OrderFinalProductEntity::getSku,
                            Collectors.mapping(OrderFinalProductEntity::getId, Collectors.toList())
                    ));

            final Long orderId = orderIds.get(i);
            final List<DiscountDTO> discountDTOList = orderDTOList.get(i).getDiscounts();

            for (DiscountDTO discountDTO : discountDTOList) {

                boolean isOrderFinalProductIdExists = DEFAULT_BOOLEAN_FALSE_VALUE;

                if (Objects.nonNull(discountDTO.getOrderFinalProductId())) {
                    isOrderFinalProductIdExists = DEFAULT_BOOLEAN_TRUE_VALUE;
                }

                final List<Long> orderFinalProductIds = groupFinalProducts.get(discountDTO.getSku());
                Long orderFinalProductId = null;

                if (isOrderFinalProductIdExists && Objects.nonNull(orderFinalProductIds)) {
                    orderFinalProductId = orderFinalProductIds.get(DEFAULT_INTEGER_VALUE);
                }

                final DiscountDTO orderDiscountDTO = new DiscountDTO(
                        discountDTO.getId(),
                        discountDTO.getIsProductDiscount(),
                        discountDTO.getIsConsumptionDiscount(),
                        orderFinalProductId,
                        orderId,
                        discountDTO.getTypeId(),
                        discountDTO.getValue(),
                        discountDTO.getSku()
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
