package com.robinfood.app.usecases.getorderdetailfinalproduct;

import com.robinfood.app.mappers.GetOrderDetailDeductionMapper;
import com.robinfood.app.usecases.getorderdetaildiscountbyproductids.IGetOrderDetailDiscountByProductIdsUseCase;
import com.robinfood.app.usecases.getorderdetailfinalproductgroup.IGetOrderDetailGroupWithPortionsByProductIdsUseCase;
import com.robinfood.app.usecases.getorderdetailfinalproducttax.IGetOrderDetailFinalProductTaxUseCase;
import com.robinfood.core.dtos.GetOrderDetailDeductionDTO;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductGroupDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductTaxDTO;
import com.robinfood.core.dtos.OrderDeductionFinalProductDTO;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.OrderProductTaxDTO;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of IGetOrderDetailProductsUseCase
 */
@Component
@Slf4j
public class GroupOrderDetailProductsUseCase implements IGroupOrderDetailProductsUseCase {

    private final IGetOrderDetailDiscountByProductIdsUseCase getDetailOrderDiscountByProductIdsUseCase;
    private final IGetOrderDetailFinalProductTaxUseCase getOrderDetailFinalProductTaxUseCase;
    private final IGetOrderDetailGroupWithPortionsByProductIdsUseCase
            getOrderDetailGroupWithPortionsByProductIdsUseCase;
    private final IOrderFinalProductRepository orderFinalProductRepository;

    public GroupOrderDetailProductsUseCase(
            IGetOrderDetailDiscountByProductIdsUseCase getDetailOrderDiscountByProductIdsUseCase,
            IGetOrderDetailFinalProductTaxUseCase getOrderDetailFinalProductTaxUseCase,
            IGetOrderDetailGroupWithPortionsByProductIdsUseCase getOrderDetailGroupWithPortionsByProductIdsUseCase,
            IOrderFinalProductRepository orderFinalProductRepository
    ) {
        this.getDetailOrderDiscountByProductIdsUseCase = getDetailOrderDiscountByProductIdsUseCase;
        this.getOrderDetailFinalProductTaxUseCase = getOrderDetailFinalProductTaxUseCase;
        this.getOrderDetailGroupWithPortionsByProductIdsUseCase = getOrderDetailGroupWithPortionsByProductIdsUseCase;
        this.orderFinalProductRepository = orderFinalProductRepository;
    }

    public Map<Long, List<GetOrderDetailFinalProductDTO>> invoke(
            List<Long> orderIds,
            List<Long> orderFinalProductIds,
            List<OrderDiscountDTO> orderDiscountDTOS,
            List<OrderProductTaxDTO> orderProductTaxDTOS,
            List<OrderDeductionFinalProductDTO> orderDeductionFinalProductDTOS
    ) {

        log.info(
                "Starting process to get order detail product with order ids: [{}], "
                        + "order final product ids: [{}], order discounts: [{}], order product tax: [{}] "
                        + "and order deductions order final product: [{}]",
                orderIds, orderFinalProductIds, orderDiscountDTOS, orderProductTaxDTOS, orderDeductionFinalProductDTOS);

        final List<OrderFinalProductEntity> orderFinalProductEntitiesByOrderIds = orderFinalProductRepository
                .findAllByOrderIdIn(orderIds);

        final Map<Long, List<GetOrderDetailFinalProductGroupDTO>> groupOrderFinalProductDTOS =
                getOrderDetailGroupWithPortionsByProductIdsUseCase.invoke(
                        orderFinalProductIds
                );

        final Map<Long, List<GetOrderDetailDiscountDTO>> getOrderDetailDiscountDTOS =
                getDetailOrderDiscountByProductIdsUseCase.invoke(
                        orderDiscountDTOS
                );

        final Map<Long, List<GetOrderDetailFinalProductTaxDTO>> getOrderDetailFinalProductTaxDTOS =
                getOrderDetailFinalProductTaxUseCase.invoke(
                        orderProductTaxDTOS
                );

        final Map<Long, BigDecimal> orderDetailDeductionsMap =
                orderDeductionFinalProductDTOS.stream()
                        .map(GetOrderDetailDeductionMapper::
                                orderDeductionFinalProductDtoToGetOrderDetailDeductionDTO)
                        .collect(Collectors.toMap(GetOrderDetailDeductionDTO::getId,
                                GetOrderDetailDeductionDTO::getValue));

        final List<GetOrderDetailFinalProductDTO> orderFinalProductDTOS = new ArrayList<>();

        for (OrderFinalProductEntity orderFinalProductEntity : orderFinalProductEntitiesByOrderIds) {
            final Double unitPrice = orderFinalProductEntity.getBasePrice() +
                    (orderFinalProductEntity.getProductsPrice() / orderFinalProductEntity.getQuantity());

            orderFinalProductDTOS.add(
                    new GetOrderDetailFinalProductDTO(
                            orderFinalProductEntity.getArticleId(),
                            orderFinalProductEntity.getArticleTypeId(),
                            orderFinalProductEntity.getBasePrice(),
                            orderFinalProductEntity.getBrandId(),
                            orderFinalProductEntity.getBrandName(),
                            orderFinalProductEntity.getFinalProductCategoryId(),
                            orderFinalProductEntity.getFinalProductCategoryName(),
                            orderFinalProductEntity.getFinalProductId(),
                            orderFinalProductEntity.getImage(),
                            orderFinalProductEntity.getBrandMenuId(),
                            orderFinalProductEntity.getFinalProductName(),
                            orderFinalProductEntity.getQuantity(),
                            unitPrice,
                            orderFinalProductEntity.getSizeId(),
                            orderFinalProductEntity.getSizeName(),
                            orderFinalProductEntity.getSku(),
                            groupOrderFinalProductDTOS.get(orderFinalProductEntity.getId()),
                            BigDecimal.valueOf(orderFinalProductEntity.getDiscountPrice()),
                            getOrderDetailDiscountDTOS.get(orderFinalProductEntity.getId()),
                            orderDetailDeductionsMap.getOrDefault(orderFinalProductEntity.getId(), BigDecimal.ZERO),
                            orderFinalProductEntity.getOrderId(),
                            getOrderDetailFinalProductTaxDTOS.get(orderFinalProductEntity.getId()),
                            Objects.requireNonNullElse((orderFinalProductEntity.getCo2Total()), BigDecimal.ZERO),
                            orderFinalProductEntity.getTotal()
                    )
            );
        }

        return CollectionsKt.groupBy(
                orderFinalProductDTOS, GetOrderDetailFinalProductDTO::getOrderId
        );
    }
}
