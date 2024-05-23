package com.robinfood.orderorlocalserver.repositories.orderdetailprint;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDiscountDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailPaymentMethodDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductDiscountDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductGroupDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductGroupPortionDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductTaxDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailRemovedPortionDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailUserDTO;
import com.robinfood.orderorlocalserver.entities.APIResponseEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.CouponEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailDiscountEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailPaymentMethodEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductDiscountEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductGroupEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductGroupPortionEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductTaxEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailRemovedPortionEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailUserEntity;
import com.robinfood.orderorlocalserver.enums.Result;
import com.robinfood.orderorlocalserver.mocks.dtos.ElectronicBillDTOMock;
import com.robinfood.orderorlocalserver.mocks.dtos.OrderFiscalIdentifierDTOMock;
import com.robinfood.orderorlocalserver.mocks.entities.ElectronicBillEntityMock;
import com.robinfood.orderorlocalserver.mocks.entities.OrderFiscalIdentifierEntityMock;
import com.robinfood.orderorlocalserver.network.OrderBcAPI;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailPrintRepositoryTest {

    @Mock
    private OrderBcAPI orderBcAPI;

    @InjectMocks
    private OrderDetailPrintRepository orderDetailPrintRepository;

    private final String token = "token";

    private final List<Long> orderIds = new ArrayList<>(Collections.singletonList(1L));
    private final List<String> orderUids = new ArrayList<>(Collections.singletonList("uids"));

    final List<OrderDetailProductDTO> orderDetailProductDTOSDefault = new ArrayList<>();
    final List<OrderDetailPaymentMethodDTO> orderDetailPaymentMethodDTOSDefault = new ArrayList<>();
    final List<OrderDetailDiscountDTO> orderDetailDiscountDTOSDefault = new ArrayList<>();
    final List<OrderDetailProductGroupDTO> orderDetailProductGroupDTOSDefault = new ArrayList<>();
    final List<OrderDetailProductDiscountDTO> orderDetailProductDiscountDTOSDefault = new ArrayList<>();
    final List<OrderDetailProductTaxDTO> orderDetailProductTaxDTOSDefault = new ArrayList<>();


    private final List<OrderDetailPaymentMethodEntity> orderDetailPaymentMethodEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailPaymentMethodEntity(
                            0.0,
                            1L,
                            4L,
                            8900.0,
                            0.0,
                            8900.0
                    )
            )
    );

    private final List<OrderDetailProductGroupPortionEntity> orderDetailProductGroupPortionEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionEntity(
                            true,
                            null,
                            BigDecimal.ZERO,
                            1,
                            1L,
                            "arroz",
                            8900.0,
                            1L,
                            1,
                            "sku",
                            1L,
                            1.0
                    )
            )
    );

    private final List<OrderDetailRemovedPortionEntity> orderDetailRemovedPortionEntities = Collections.singletonList(
            new OrderDetailRemovedPortionEntity(
                    1L,
                    "Rice"
            )
    );

    private final List<OrderDetailProductGroupEntity> orderDetailProductGroupEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupEntity(
                            1L,
                            "ingredientes",
                            orderDetailProductGroupPortionEntities,
                            orderDetailRemovedPortionEntities,
                            "sku"
                    )
            )
    );

    private final List<OrderDetailProductTaxEntity> orderDetailProductTaxEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductTaxEntity(
                            1L,
                            1L,
                            0.0,
                            1L,
                            "IMPOCONSUMO",
                            0.0
                    )
            )
    );

    private final List<OrderDetailProductDiscountEntity> orderDetailProductDiscountEntities = Collections.singletonList(
            new OrderDetailProductDiscountEntity(
                    1L,
                    1L,
                    0.0
            )
    );

    private final List<OrderDetailProductEntity> orderDetailProductEntities = new ArrayList<>(
            Collections.singletonList(
                    OrderDetailProductEntity.builder()
                            .articleId(1L)
                            .articleTypeId(1L)
                            .basePrice(8900.0)
                            .brandId(1L)
                            .brandName("muy")
                            .categoryId(1L)
                            .categoryName("Category")
                            .co2Total(BigDecimal.ONE)
                            .deduction(BigDecimal.ZERO)
                            .discount(BigDecimal.ZERO)
                            .discounts(orderDetailProductDiscountEntities)
                            .finalProductId(1L)
                            .groups(orderDetailProductGroupEntities)
                            .id(1L)
                            .image("image.png")
                            .brandMenuId(1L)
                            .menuHallProductId(1L)
                            .name("paisa")
                            .quantity(1)
                            .sizeId(1L)
                            .sizeName("muy")
                            .sku("sku")
                            .taxes(orderDetailProductTaxEntities)
                            .unitPrice(new BigDecimal("8900.0"))
                            .total(0.0)
                            .build()
            )
    );

    private final OrderDetailUserEntity orderDetailUserEntity = new OrderDetailUserEntity(
            "bruno@email",
            "bruno",
            1L,
            "jason",
            "300"
    );

    private final List<OrderDetailDiscountEntity> orderDetailDiscountEntities = Collections.singletonList(
            new OrderDetailDiscountEntity(
                    1L,
                    1L,
                    0.0
            )
    );

    private final List<OrderDetailEntity> orderDetailEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailEntity(
                            new OrderFiscalIdentifierEntityMock().getDataDefault(),
                            1L,
                            "muy",
                            BigDecimal.ZERO,
                            1L,
                            "cop",
                            1L,
                            BigDecimal.ZERO,
                            List.of(new CouponEntity("pruaba", BigDecimal.ONE)),
                            BigDecimal.ZERO,
                            0.0,
                            orderDetailDiscountEntities,
                            1L,
                            ElectronicBillEntityMock.getDataDefault(),
                            "",
                            "Notes",
                            1L,
                            "pos",
                            "2005",
                            "3483eghf",
                            null,
                            "test 1",
                            true,
                            "0000",
                            "2022-01-01",
                            "09:27:44",
                            orderDetailPaymentMethodEntities,
                            1L,
                            false,
                            orderDetailProductEntities,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            null,
                            "1234",
                            orderDetailUserEntity
                    )
            )
    );

    private final APIResponseEntity<List<OrderDetailEntity>> apiResponseEntity =
            APIResponseEntity.<List<OrderDetailEntity>>builder()
                    .code(200)
                    .data(orderDetailEntities)
                    .locale("CO")
                    .message("")
                    .status("Order detail returned")
                    .build();

    private final List<OrderDetailPaymentMethodDTO> orderDetailPaymentMethodDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailPaymentMethodDTO(
                            0.0,
                            1L,
                            4L,
                            8900.0,
                            0.0,
                            8900.0
                    )
            )
    );

    private final List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionDTO(
                            true,
                            null,
                            BigDecimal.ZERO,
                            1L,
                            "arroz",
                            1L,
                            8900.0,
                            1,
                            1,
                            "sku",
                            1L,
                            1.0
                    )
            )
    );

    private final List<OrderDetailRemovedPortionDTO> orderDetailRemovedPortionDTOS = Collections.singletonList(
            new OrderDetailRemovedPortionDTO(
                    1L,
                    "Rice"
            )
    );

    private final List<OrderDetailProductGroupDTO> orderDetailProductGroupDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupDTO(
                            1L,
                            "ingredientes",
                            orderDetailProductGroupPortionDTOS,
                            orderDetailRemovedPortionDTOS,
                            "sku"
                    )
            )
    );

    private final List<OrderDetailProductTaxDTO> orderDetailProductTaxDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductTaxDTO(
                            1L,
                            1L,
                            0.0,
                            1L,
                            "IMPOCONSUMO",
                            0.0
                    )
            )
    );

    private final List<OrderDetailProductDiscountDTO> orderDetailProductDiscountDTOS = Collections.singletonList(
            new OrderDetailProductDiscountDTO(
                    1L,
                    1L,
                    0.0
            )
    );

    private final List<OrderDetailProductDTO> orderDetailProductDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductDTO(
                            1L,
                            "",
                            1L,
                            8900.0,
                            1L,
                            "muy",
                            1L,
                            "Category",
                            BigDecimal.ONE,
                            BigDecimal.valueOf(0),
                            BigDecimal.valueOf(0),
                            orderDetailProductDiscountDTOS,
                            1L,
                            orderDetailProductGroupDTOS,
                            1L,
                            "image.png",
                            1L,
                            1L,
                            "paisa",
                            1,
                            1L,
                            "muy",
                            "sku",
                            orderDetailProductTaxDTOS,
                            new BigDecimal("8900.0"),
                            0.0
                    )
            )
    );

    private final OrderDetailUserDTO orderDetailUserDTO = OrderDetailUserDTO.builder()
            .email("bruno@email")
            .firstName("bruno")
            .id(1L)
            .lastName("jason")
            .mobile("300")
            .build();

    private final List<OrderDetailDiscountDTO> orderDetailDiscountDTOS = Collections.singletonList(
            new OrderDetailDiscountDTO(
                    1L,
                    1L,
                    0.0
            )
    );

    private final List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailDTO(
                            new OrderFiscalIdentifierDTOMock().getDataDefault(),
                            1L,
                            "muy",
                            BigDecimal.ZERO,
                            "cop",
                            List.of(),
                            1L,
                            0.0,
                            orderDetailDiscountDTOS,
                            1L,
                            ElectronicBillDTOMock.getDataDefault(),
                            BigDecimal.ZERO,
                            1L,
                            BigDecimal.ZERO,
                            "",
                            "Notes",
                            1L,
                            "pos",
                            "2005",
                            "3483eghf",
                            null,
                            "test 1",
                            true,
                            "0000",
                            "2022-01-01",
                            "09:27:44",
                            orderDetailPaymentMethodDTOS,
                            1L,
                            false,
                            orderDetailProductDTOS,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            null,
                            "1234",
                            orderDetailUserDTO,
                            Collections.singletonList(orderDetailUserDTO)
                    )
            )
    );

    /**
     * DTOs Data null
     */
    private final List<OrderDetailEntity> orderDetailEntitiesDataNull = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailEntity(
                            new OrderFiscalIdentifierEntityMock().getDataDefault(),
                            1L,
                            "muy",
                            BigDecimal.ZERO,
                            1L,
                            "cop",
                            1L,
                            BigDecimal.ZERO,
                            List.of(new CouponEntity("prueba", BigDecimal.ONE)),
                            BigDecimal.ZERO,
                            0.0,
                            null,
                            1L,
                            ElectronicBillEntityMock.getDataDefault(),
                            "",
                            "Notes",
                            1L,
                            "pos",
                            "2005",
                            "3483eghf",
                            "test 1",
                            null,
                            true,
                            "0000",
                            "2022-01-01",
                            "09:27:44",
                            null,
                            1L,
                            false,
                            null,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            null,
                            "1234",
                            null
                    )
            )
    );

    private final APIResponseEntity<List<OrderDetailEntity>> apiResponseEntityDataNull =
            APIResponseEntity.<List<OrderDetailEntity>>builder()
                    .code(200)
                    .data(orderDetailEntitiesDataNull)
                    .locale("CO")
                    .message("")
                    .status("Order detail returned")
                    .build();

    private final List<OrderDetailDTO> orderDetailDTOSDataNullEntity = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailDTO(
                            new OrderFiscalIdentifierDTOMock().getDataDefault(),
                            1L,
                            "muy",
                            BigDecimal.ZERO,
                            "cop",
                            List.of(),
                            1L,
                            0.0,
                            orderDetailDiscountDTOSDefault,
                            1L,
                            ElectronicBillDTOMock.getDataDefault(),
                            BigDecimal.ZERO,
                            1L,
                            BigDecimal.ZERO,
                            "",
                            "Notes",
                            1L,
                            "pos",
                            "2005",
                            "3483eghf",
                            "test 1",
                            null,
                            true,
                            "0000",
                            "2022-01-01",
                            "09:27:44",
                            orderDetailPaymentMethodDTOSDefault,
                            1L,
                            false,
                            orderDetailProductDTOSDefault,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            null,
                            "1234",
                            null,
                            Collections.singletonList(null)
                    )
            )
    );

    /**
     * Products Entities data null
     */
    private final List<OrderDetailProductEntity> orderDetailProductWithoutGroupsEmptyEntities = new ArrayList<>(
            Collections.singletonList(
                    OrderDetailProductEntity.builder()
                            .articleId(1L)
                            .articleTypeId(1L)
                            .basePrice(8900.0)
                            .brandId(1L)
                            .brandName("muy")
                            .categoryId(1L)
                            .categoryName("Category")
                            .co2Total(BigDecimal.ONE)
                            .deduction(BigDecimal.ZERO)
                            .discount(null)
                            .discounts(null)
                            .finalProductId(1L)
                            .groups(null)
                            .id(1L)
                            .image("image.png")
                            .brandMenuId(1L)
                            .menuHallProductId(1L)
                            .name("paisa")
                            .quantity(1)
                            .sizeId(1L)
                            .sizeName("muy")
                            .sku("sku")
                            .taxes(null)
                            .unitPrice(new BigDecimal("8900.0"))
                            .total(0.0)
                            .build()
            )
    );

    private final List<OrderDetailEntity> orderDetailEntitiesWithProductDataNull = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailEntity(
                            new OrderFiscalIdentifierEntityMock().getDataDefault(),
                            1L,
                            "muy",
                            BigDecimal.ZERO,
                            1L,
                            "cop",
                            1L,
                            BigDecimal.ZERO,
                            List.of(new CouponEntity("pruaba", BigDecimal.ONE)),
                            BigDecimal.ZERO,
                            0.0,
                            null,
                            1L,
                            ElectronicBillEntityMock.getDataDefault(),
                            "",
                            "Notes",
                            1L,
                            "pos",
                            "2005",
                            "3483eghf",
                            null,
                            "test 1",
                            true,
                            "0000",
                            "2022-01-01",
                            "09:27:44",
                            null,
                            1L,
                            false,
                            orderDetailProductWithoutGroupsEmptyEntities,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            null,
                            "1234",
                            null
                    )
            )
    );

    private final APIResponseEntity<List<OrderDetailEntity>> apiResponseEntityProductWithGroupDataNull =
            APIResponseEntity.<List<OrderDetailEntity>>builder()
                    .code(200)
                    .data(orderDetailEntitiesWithProductDataNull)
                    .locale("CO")
                    .message("")
                    .status("Order detail returned")
                    .build();

    private final List<OrderDetailProductDTO> orderDetailProductWithoutGroupDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductDTO(
                            1L,
                            "",
                            1L,
                            8900.0,
                            1L,
                            "muy",
                            1L,
                            "Category",
                            BigDecimal.ONE,
                            BigDecimal.valueOf(0),
                            null,
                            orderDetailProductDiscountDTOSDefault,
                            1L,
                            orderDetailProductGroupDTOSDefault,
                            1L,
                            "image.png",
                            1L,
                            1L,
                            "paisa",
                            1,
                            1L,
                            "muy",
                            "sku",
                            orderDetailProductTaxDTOSDefault,
                            new BigDecimal("8900.0"),
                            0.0
                    )
            )
    );

    private final List<OrderDetailDTO> orderDetailWithProductDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailDTO(
                            new OrderFiscalIdentifierDTOMock().getDataDefault(),
                            1L,
                            "muy",
                            BigDecimal.ZERO,
                            "cop",
                            List.of(),
                            1L,
                            0.0,
                            orderDetailDiscountDTOSDefault,
                            1L,
                            ElectronicBillDTOMock.getDataDefault(),
                            BigDecimal.ZERO,
                            1L,
                            BigDecimal.ZERO,
                            "",
                            "Notes",
                            1L,
                            "pos",
                            "2005",
                            "3483eghf",
                            null,
                            "test 1",
                            true,
                            "0000",
                            "2022-01-01",
                            "09:27:44",
                            orderDetailPaymentMethodDTOSDefault,
                            1L,
                            false,
                            orderDetailProductWithoutGroupDTOS,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            null,
                            "1234",
                            null,
                            Collections.singletonList(null)
                    )
            )
    );

    @Test
    void test_GetOrderDetailPrint_Returns_Successfully() {

        when(orderBcAPI.getOrderDetailPrint(token, orderIds, orderUids, Collections.emptyList()))
                .thenReturn(apiResponseEntity);

        final Result<List<OrderDetailDTO>> result = orderDetailPrintRepository.getOrderDetailPrint(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        );
        assertTrue(result instanceof Result.Success);
        assertEquals(orderDetailDTOS, ((Result.Success<List<OrderDetailDTO>>) result).getData());
    }

    @Test
    void test_GetOrderDetailPrint_Data_Null_Returns_Successfully() {
        when(orderBcAPI.getOrderDetailPrint(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        )).thenReturn(apiResponseEntityDataNull);

        final Result<List<OrderDetailDTO>> result = orderDetailPrintRepository.getOrderDetailPrint(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        );
        assertTrue(result instanceof Result.Success);
        assertEquals(orderDetailDTOSDataNullEntity, ((Result.Success<List<OrderDetailDTO>>) result).getData());
    }


    @Test
    void test_GetOrderDetailPrint_Product_Without_Data_Null_Returns_Successfully() {
        when(orderBcAPI.getOrderDetailPrint(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        )).thenReturn(apiResponseEntityProductWithGroupDataNull);

        final Result<List<OrderDetailDTO>> result = orderDetailPrintRepository.getOrderDetailPrint(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        );
        assertTrue(result instanceof Result.Success);
        assertEquals(orderDetailWithProductDTOS, ((Result.Success<List<OrderDetailDTO>>) result).getData());
    }

    @Test
    void test_GetOrderDetailPrint_Returns_With_Failure() {

        Request request = Request.create(Request.HttpMethod.GET, "url",
                new HashMap<>(), null, new RequestTemplate());

        when(orderBcAPI.getOrderDetailPrint(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        )).thenThrow(new FeignException.InternalServerError
                ("Any other message", request, null, null) );

        final Result<List<OrderDetailDTO>> result = orderDetailPrintRepository.getOrderDetailPrint(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        );

        assertTrue(result instanceof Result.Error);
    }
}
