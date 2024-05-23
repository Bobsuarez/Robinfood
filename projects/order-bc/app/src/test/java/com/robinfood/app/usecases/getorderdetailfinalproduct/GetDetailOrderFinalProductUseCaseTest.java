package com.robinfood.app.usecases.getorderdetailfinalproduct;

import com.robinfood.app.usecases.getorderdetaildiscountbyproductids.IGetOrderDetailDiscountByProductIdsUseCase;
import com.robinfood.app.usecases.getorderdetailfinalproductgroup.IGetOrderDetailGroupWithPortionsByProductIdsUseCase;
import com.robinfood.app.usecases.getorderdetailfinalproducttax.IGetOrderDetailFinalProductTaxUseCase;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductGroupDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductTaxDTO;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.OrderProductTaxDTO;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetDetailOrderFinalProductUseCaseTest {

    @Mock
    private IGetOrderDetailDiscountByProductIdsUseCase getDetailOrderDiscountByProductIdsUseCase;

    @Mock
    private IGetOrderDetailFinalProductTaxUseCase getOrderDetailFinalProductTaxUseCase;

    @Mock
    private IGetOrderDetailGroupWithPortionsByProductIdsUseCase getOrderDetailGroupWithPortionsByProductIdsUseCase;

    @Mock
    private IOrderFinalProductRepository orderFinalProductRepository;

    @InjectMocks
    private GroupOrderDetailProductsUseCase getOrderDetailProductsUseCase;

    private final List<Long> orderIds = new ArrayList<>(Collections.singletonList(1L));

    private final List<Long> orderFinalProductIds = new ArrayList<>(Collections.singletonList(1L));

    private final List<OrderDiscountDTO> orderDiscountDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDiscountDTO(
                            1L,
                            0.0,
                            1L,
                            1L,
                            1L,
                            1L
                    )
            )
    );


    private final List<OrderProductTaxDTO> orderProductTaxDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderProductTaxDTO(
                            1L,
                            1L,
                            1L,
                            1L,
                            1L,
                            1L,
                            1L,
                            0.0,
                            1L,
                            "IMPOCONSUMO",
                            0.0
                    )
            )
    );

    private final List<OrderFinalProductEntity> orderFinalProductEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderFinalProductEntity(
                            1L,
                            1L,
                            1L,
                            1L,
                            "muy",
                            new BigDecimal("8900.0"),
                            1L,
                            8900.0,
                            1L,
                            "Category",
                            1L,
                            "paisa",
                            "image.png",
                            1L,
                            1L,
                            0.0,
                            1,
                            1L,
                            "muy",
                            0.0,
                            8900.0,
                            0.0
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailFinalProductGroupDTO>> groupOrderFinalProductDTOSMap = new HashMap<>();

    private final List<GetOrderDetailFinalProductGroupDTO> getOrderDetailFinalProductGroupDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailFinalProductGroupDTO(
                            1L,
                            1L,
                            "ingredientes",
                            1L,
                            null,
                            new ArrayList<>(),
                            "sku"
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailDiscountDTO>> groupOrderDetailDiscountDTOSMap = new HashMap<>();

    private final List<GetOrderDetailDiscountDTO> getOrderDetailDiscountDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailDiscountDTO(
                            1L,
                            1L,
                            1L,
                            1L,
                            0.0
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailFinalProductTaxDTO>> getOrderDetailFinalProductTaxDTOSMap = new HashMap<>();

    private final List<GetOrderDetailFinalProductTaxDTO> getOrderDetailFinalProductTaxDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailFinalProductTaxDTO(
                            1L,
                            1L,
                            1L,
                            "Impoconsumo",
                            0.0,
                            1L,
                            0.0,
                            1L
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailFinalProductDTO>> getOrderDetailFinalProductDTOSMap = new HashMap<>();

    private final List<GetOrderDetailFinalProductDTO> getOrderDetailFinalProductDTOS = List.of(
            GetOrderDetailFinalProductDTO.builder()
                    .articleId(1L)
                    .articleTypeId(1L)
                    .basePrice(new BigDecimal("8900.0"))
                    .brandId(1L)
                    .brandName("muy")
                    .categoryId(1L)
                    .categoryName("Category")
                    .id(1L)
                    .image("image.png")
                    .brandMenuId(1L)
                    .name("paisa")
                    .quantity(1)
                    .unitPrice(new BigDecimal("8900.0"))
                    .sizeId(1L)
                    .sizeName("muy")
                    .discount(BigDecimal.valueOf(8900.0))
                    .deduction(BigDecimal.ZERO)
                    .orderId(1L)
                    .co2Total(BigDecimal.ZERO)
                    .total(0.0)
                    .build()
    );

    @Test
    void test_GetOrderDetailProducts_Returns_Correctly() {

        groupOrderFinalProductDTOSMap.put(1L, getOrderDetailFinalProductGroupDTOS);

        groupOrderDetailDiscountDTOSMap.put(1L, getOrderDetailDiscountDTOS);

        getOrderDetailFinalProductTaxDTOSMap.put(1L, getOrderDetailFinalProductTaxDTOS);

        getOrderDetailFinalProductDTOSMap.put(1L, getOrderDetailFinalProductDTOS);

        when(orderFinalProductRepository
                .findAllByOrderIdIn(orderIds)).thenReturn(orderFinalProductEntities);

        when(getOrderDetailGroupWithPortionsByProductIdsUseCase
                .invoke(orderFinalProductIds)).thenReturn(groupOrderFinalProductDTOSMap);

        when(getDetailOrderDiscountByProductIdsUseCase
                .invoke(orderDiscountDTOS)).thenReturn(groupOrderDetailDiscountDTOSMap);

        when(getOrderDetailFinalProductTaxUseCase
                .invoke(orderProductTaxDTOS)).thenReturn(getOrderDetailFinalProductTaxDTOSMap);

        final Map<Long, List<GetOrderDetailFinalProductDTO>> result = getOrderDetailProductsUseCase
                .invoke(
                        orderIds,
                        orderFinalProductIds,
                        orderDiscountDTOS,
                        orderProductTaxDTOS,
                        anyList()
                );

        assertEquals(getOrderDetailFinalProductDTOSMap, result);
    }
}
