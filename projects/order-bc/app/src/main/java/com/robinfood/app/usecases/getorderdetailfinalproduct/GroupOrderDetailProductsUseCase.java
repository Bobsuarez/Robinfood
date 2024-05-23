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

            final BigDecimal unitPrice = orderFinalProductEntity.getBasePrice().add(BigDecimal.valueOf((
                    orderFinalProductEntity.getProductsPrice() / orderFinalProductEntity.getQuantity()
            )));

            orderFinalProductDTOS.add(
                     GetOrderDetailFinalProductDTO.builder()
                             .articleId(orderFinalProductEntity.getArticleId())
                             .articleTypeId(orderFinalProductEntity.getArticleTypeId())
                             .basePrice(orderFinalProductEntity.getBasePrice())
                             .brandId(orderFinalProductEntity.getBrandId())
                             .brandName(orderFinalProductEntity.getBrandName())
                             .categoryId(orderFinalProductEntity.getFinalProductCategoryId())
                             .categoryName(orderFinalProductEntity.getFinalProductCategoryName())
                             .id(orderFinalProductEntity.getFinalProductId())
                             .image(orderFinalProductEntity.getImage())
                             .brandMenuId(orderFinalProductEntity.getBrandMenuId())
                             .name(orderFinalProductEntity.getFinalProductName())
                             .quantity(orderFinalProductEntity.getQuantity())
                             .unitPrice(unitPrice)
                             .sizeId(orderFinalProductEntity.getSizeId())
                             .sizeName(orderFinalProductEntity.getSizeName())
                             .sku(orderFinalProductEntity.getSku())
                             .groups(groupOrderFinalProductDTOS.get(orderFinalProductEntity.getId()))
                             .discount(BigDecimal.valueOf(orderFinalProductEntity.getDiscountPrice()))
                             .discounts(getOrderDetailDiscountDTOS.get(orderFinalProductEntity.getId()))
                             .deduction(
                                     orderDetailDeductionsMap.getOrDefault(orderFinalProductEntity.getId(),
                                             BigDecimal.ZERO)
                             )
                             .orderId(orderFinalProductEntity.getOrderId())
                             .taxes(getOrderDetailFinalProductTaxDTOS.get(orderFinalProductEntity.getId()))
                             .co2Total(Objects.requireNonNullElse(
                                     (orderFinalProductEntity.getCo2Total()),
                                     BigDecimal.ZERO)
                             )
                             .total(orderFinalProductEntity.getTotal())
                             .build()
            );
        }

        return CollectionsKt.groupBy(
                orderFinalProductDTOS, GetOrderDetailFinalProductDTO::getOrderId
        );
    }
}
