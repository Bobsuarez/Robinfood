package com.robinfood.app.usecases.getordercategory;

import com.robinfood.app.mappers.OrderCategoryMapper;
import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.core.utilities.CalculateDateDisplacementUTCUtil;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.orderservices.IOrderServiceRepository;
import lombok.extern.slf4j.Slf4j;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_EMPTY_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_EMPTY_VALUE;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_START;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;
import static com.robinfood.core.constants.GlobalConstants.ORDER_STATUS_CANCELLED;

@Service
@Slf4j
public class GetOrderCategoryUseCase implements IGetOrderCategoryUseCase {

    private final IOrderFinalProductRepository orderFinalProductRepository;

    private final IOrderServiceRepository orderServiceRepository;

    private final IOrdersRepository ordersRepository;

    public GetOrderCategoryUseCase(
            IOrderFinalProductRepository orderFinalProductRepository,
            IOrderServiceRepository orderServiceRepository,
            IOrdersRepository ordersRepository
    ) {
        this.orderFinalProductRepository = orderFinalProductRepository;
        this.orderServiceRepository = orderServiceRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<OrderCategoryDTO> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long posId,
            String timeZone
    ) {

        log.info("Start of the process that obtains the orders by category grouping by pos: {}", posId);

        final Map<String, LocalDateTime> localDateTimeZone = CalculateDateDisplacementUTCUtil.getLocalDateByRange(
                localDateEnd,
                localDateStart,
                timeZone
        );

        final List<OrderEntity> orderList = ordersRepository.findByCreatedAtBetweenAndPaidAndPosIdAndStatusIdNot(
                localDateTimeZone.get(LOCAL_DATE_START),
                localDateTimeZone.get(LOCAL_DATE_END),
                ORDER_PAID,
                posId,
                ORDER_STATUS_CANCELLED
        ).orElseThrow(() -> new GenericOrderBcException("Orders no found"));

        final List<Long> orderIds = orderList.stream()
                .map(OrderEntity::getId)
                .collect(Collectors.toList());

        final List<OrderFinalProductEntity> orderFinalProductEntities = orderFinalProductRepository.findAllByOrderIdIn(
                orderIds
        );

        final List<Long> finalProductCategoryIds = orderFinalProductEntities.stream()
                .map(OrderFinalProductEntity::getFinalProductCategoryId)
                .distinct()
                .collect(Collectors.toList());

        final List<OrderServicesEntity> orderServicesEntities = orderServiceRepository.findAllByOrderIdIsIn(orderIds);

        List<OrderCategoryDTO> orderCategoryDTOList = new ArrayList<>();

        finalProductCategoryIds.forEach((Long finalProductCategoryId) -> orderCategoryDTOList.add(
                this.buildCategoryGroupingOrders(
                        finalProductCategoryId,
                        orderFinalProductEntities
                )
        ));

        buildServiceCategory(orderServicesEntities, orderCategoryDTOList);

        return orderCategoryDTOList;
    }

    private OrderCategoryDTO buildCategoryGroupingOrders(
            Long finalProductCategoryId,
            List<OrderFinalProductEntity> orderFinalProductEntities
    ) {

        final List<OrderFinalProductEntity> orderFinalProductEntitiesByCategory = orderFinalProductEntities.stream()
                .filter(orderFinalProductEntity -> orderFinalProductEntity.getFinalProductCategoryId()
                        .equals(finalProductCategoryId)
                ).collect(Collectors.toList());

        final OrderFinalProductEntity orderFinalProductEntity = orderFinalProductEntitiesByCategory.stream()
                .findFirst()
                .orElseThrow();

        final Double compensation = getCompensation(orderFinalProductEntitiesByCategory);

        final Double discounts = getDiscounts(orderFinalProductEntitiesByCategory);

        final Double grossValue = getGrossValue(orderFinalProductEntitiesByCategory);

        final Double netValue = getNetValue(orderFinalProductEntitiesByCategory);

        final Double taxes = getTaxes(orderFinalProductEntitiesByCategory);

        return OrderCategoryMapper.informationToOrderCategoryDTO(
                compensation,
                discounts,
                grossValue,
                netValue,
                orderFinalProductEntity,
                taxes
        );
    }

    @NotNull
    private Double getTaxes(List<OrderFinalProductEntity> orderFinalProductEntitiesByCategory) {

        return orderFinalProductEntitiesByCategory.stream()
                .mapToDouble(OrderFinalProductEntity::getTotalTaxPrice)
                .sum();
    }

    @NotNull
    private Double getNetValue(List<OrderFinalProductEntity> orderFinalProductEntitiesByCategory) {

        return orderFinalProductEntitiesByCategory.stream()
                .mapToDouble(OrderFinalProductEntity::getTotal)
                .sum();
    }

    @NotNull
    private Double getGrossValue(List<OrderFinalProductEntity> orderFinalProductEntitiesByCategory) {

        return orderFinalProductEntitiesByCategory.stream()
                .mapToDouble(OrderFinalProductEntity::getTotalPriceNt)
                .sum();
    }

    @NotNull
    private Double getDiscounts(List<OrderFinalProductEntity> orderFinalProductEntitiesByCategory) {

         return orderFinalProductEntitiesByCategory.stream()
                .mapToDouble(OrderFinalProductEntity::getDiscountPrice)
                .sum();
    }

    @NotNull
    private Double getCompensation(List<OrderFinalProductEntity> orderFinalProductEntitiesByCategory) {

        return orderFinalProductEntitiesByCategory.stream()
                .mapToDouble(orderFinalProduct -> orderFinalProduct.getCo2Total().doubleValue())
                .sum();
    }

    private void buildServiceCategory(
            List<OrderServicesEntity> orderServicesEntities,
            List<OrderCategoryDTO> orderCategoryDTOList
    ) {
        Double discountServices = orderServicesEntities.stream()
                .mapToDouble(OrderServicesEntity::getDiscount)
                .sum();

        Double grossValueServices = orderServicesEntities.stream()
                .mapToDouble(OrderServicesEntity::getPriceNt)
                .sum();

        Double netValueServices = orderServicesEntities.stream()
                .mapToDouble(OrderServicesEntity::getTotal)
                .sum();

        Double taxesServices = orderServicesEntities.stream()
                .mapToDouble(OrderServicesEntity::getTaxPrice)
                .sum();

        if (!netValueServices.equals(DEFAULT_DOUBLE_EMPTY_VALUE)) {

            orderCategoryDTOList.add(
                    OrderCategoryMapper.informationServicesToOrderCategoryDTO(
                            discountServices,
                            grossValueServices,
                            netValueServices,
                            taxesServices
                    )
            );

        }
    }
}
