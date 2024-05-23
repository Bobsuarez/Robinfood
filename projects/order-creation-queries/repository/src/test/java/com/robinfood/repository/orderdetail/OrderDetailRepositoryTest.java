package com.robinfood.repository.orderdetail;

import com.robinfood.core.dtos.DataElectronicBillingDTO;
import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderDetailDiscountDTO;
import com.robinfood.core.dtos.OrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailProductDiscountDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.dtos.OrderDetailProductTaxDTO;
import com.robinfood.core.dtos.OrderDetailRemovedPortionDTO;
import com.robinfood.core.dtos.OrderDetailUserDTO;
import com.robinfood.core.dtos.CouponDTO;
import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.DataElectronicBillingEntity;
import com.robinfood.core.entities.ElectronicBillEntity;
import com.robinfood.core.entities.OrderDetailDiscountEntity;
import com.robinfood.core.entities.OrderDetailEntity;
import com.robinfood.core.entities.OrderDetailPaymentMethodEntity;
import com.robinfood.core.entities.OrderDetailProductDiscountEntity;
import com.robinfood.core.entities.OrderDetailProductEntity;
import com.robinfood.core.entities.OrderDetailProductGroupEntity;
import com.robinfood.core.entities.OrderDetailProductGroupPortionEntity;
import com.robinfood.core.entities.OrderDetailProductTaxEntity;
import com.robinfood.core.entities.OrderDetailRemovedPortionEntity;
import com.robinfood.core.entities.OrderThirdPartyEntity;
import com.robinfood.core.entities.OrderDetailUserEntity;
import com.robinfood.core.entities.CouponEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.core.mocks.dto.OrderDetailServiceDTOMock;
import com.robinfood.core.mocks.dto.OrderFiscalIdentifierDTOMock;
import com.robinfood.network.api.OrderBcAPI;
import com.robinfood.repository.entity.OrderFiscalIdentifierEntityMock;
import com.robinfood.repository.mocks.OrderDetailServiceEntityMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailRepositoryTest {


    @Mock
    private OrderBcAPI orderBcAPI;

    @Mock
    private Call<APIResponseEntity<List<OrderDetailEntity>>> mockValidateGetOrderDetailCall;

    @InjectMocks
    private OrderDetailRepository orderDetailRepository;

    private final String token = "token";

    private final List<Long> orderIds = new ArrayList<>(Collections.singletonList(1L));
    private final List<String> orderUids = new ArrayList<>(Collections.singletonList("uids"));

    final List<OrderDetailProductDTO> orderDetailProductDTOSDefault = new ArrayList<>();
    final List<OrderDetailPaymentMethodDTO> orderDetailPaymentMethodDTOSDefault = new ArrayList<>();
    final List<OrderDetailDiscountDTO> orderDetailDiscountDTOSDefault = new ArrayList<>();
    final List<OrderDetailProductGroupDTO> orderDetailProductGroupDTOSDefault = new ArrayList<>();
    final List<OrderDetailProductDiscountDTO> orderDetailProductDiscountDTOSDefault = new ArrayList<>();
    final List<OrderDetailProductTaxDTO> orderDetailProductTaxDTOSDefault = new ArrayList<>();

    /**
     * Entities
     */
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

    private static final OrderThirdPartyEntity orderThirdPartyEntity = OrderThirdPartyEntity.builder()
            .documentNumber("22675323")
            .documentType(1L)
            .email("pedro@gmail.com")
            .fullName("Pedro Jose")
            .phone("3113673398")
            .build();

    private static final OrderThirdPartyDTO orderThirdPartyDTO = OrderThirdPartyDTO.builder()
            .documentNumber("22675323")
            .documentType(1L)
            .email("pedro@gmail.com")
            .fullName("Pedro Jose")
            .phone("3113673398")
            .build();

    private static final DataElectronicBillingDTO dataElectronicBillingDTO = DataElectronicBillingDTO.builder()
            .broadcastDateTime("2024-04-18T11:27:08.5472815-05:00")
            .cufe("cufe test")
            .documentType("document type test")
            .nitTransmitter("nit test")
            .number("number test")
            .prefix("prefix test")
            .qr("qr test")
            .build();

    private static final ElectronicBillDTO electronicBillDTO = ElectronicBillDTO.builder()
            .orderThirdParty(orderThirdPartyDTO)
            .orderElectronicBilling(dataElectronicBillingDTO)
            .build();

    private static final DataElectronicBillingEntity dataElectronicBillingEntity = DataElectronicBillingEntity.builder()
            .FechaYHoraEmision("2024-04-18T11:27:08.5472815-05:00")
            .CUDE("cufe test")
            .TipoDocumento("document type test")
            .NitEmisor("nit test")
            .Numero("number test")
            .Prefijo("prefix test")
            .QR("qr test")
            .build();

    private static final ElectronicBillEntity electronicBillEntity = ElectronicBillEntity.builder()
            .orderThirdParty(orderThirdPartyEntity)
            .orderElectronicBilling(dataElectronicBillingEntity)
            .build();

    private final List<OrderDetailEntity> orderDetailEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailEntity(
                            new OrderFiscalIdentifierEntityMock().getDataDefault(),
                            1L,
                            "muy",
                            BigDecimal.ZERO,
                            1L,
                            "cop",
                            electronicBillEntity,
                            1L,
                            BigDecimal.ZERO,
                            List.of(),
                            BigDecimal.ZERO,
                            0.0,
                            orderDetailDiscountEntities,
                            1L,
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
                            "10:00:00",
                            orderDetailPaymentMethodEntities,
                            1L,
                            "FBI",
                            false,
                            orderDetailProductEntities,
                            OrderDetailServiceEntityMock.getDefaultList(),
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
    private final List<OrderDetailEntity> orderDetailWithCouponsEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailEntity(
                            new OrderFiscalIdentifierEntityMock().getDataDefault(),
                            1L,
                            "muy",
                            BigDecimal.ONE,
                            1L,
                            "cop",
                            electronicBillEntity,
                            1L,
                            BigDecimal.ZERO,
                            List.of(new CouponEntity("prueba", BigDecimal.ONE)),
                            BigDecimal.ZERO,
                            0.0,
                            orderDetailDiscountEntities,
                            1L,
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
                            "10:00:00",
                            orderDetailPaymentMethodEntities,
                            1L,
                            "FBI",
                            false,
                            orderDetailProductEntities,
                            OrderDetailServiceEntityMock.getDefaultList(),
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

    private final APIResponseEntity<List<OrderDetailEntity>> apiResponseEntity = new APIResponseEntity<>(
            200,
            orderDetailEntities,
            "CO",
            "",
            "Order detail returned"
    );

    private final APIResponseEntity<List<OrderDetailEntity>> apiResponseEntityWithCoupons = new APIResponseEntity<>(
            200,
            orderDetailWithCouponsEntities,
            "CO",
            "",
            "Order detail returned"
    );


    private final APIResponseEntity<List<OrderDetailEntity>> apiResponseErrorEntity = new APIResponseEntity<>(
            500,
            orderDetailEntities,
            "CO",
            "Error",
            "Order detail could not be returned"
    );

    /**
     * DTOs
     */
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
                            BigDecimal.valueOf(1L),
                            BigDecimal.valueOf(0),
                            1L,
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
                            BigDecimal.ZERO,
                            electronicBillDTO,
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
                            "10:00:00",
                            orderDetailPaymentMethodDTOS,
                            1L,
                            "FBI",
                            false,
                            orderDetailProductDTOS,
                            OrderDetailServiceDTOMock.getDefaultList(),
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

    private final List<OrderDetailDTO> orderDetailCoouponsDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailDTO(
                            new OrderFiscalIdentifierDTOMock().getDataDefault(),
                            1L,
                            "muy",
                            BigDecimal.ONE,
                            "cop",
                            List.of(CouponDTO.
                                    builder()
                                    .code("prueba")
                                    .value(BigDecimal.ONE).build()),
                            1L,
                            0.0,
                            orderDetailDiscountDTOS,
                            1L,
                            BigDecimal.ZERO,
                            electronicBillDTO,
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
                            "10:00:00",
                            orderDetailPaymentMethodDTOS,
                            1L,
                            "FBI",
                            false,
                            orderDetailProductDTOS,
                            OrderDetailServiceDTOMock.getDefaultList(),
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
                            null,
                            1L,
                            BigDecimal.ZERO,
                            List.of(),
                            BigDecimal.ZERO,
                            0.0,
                            null,
                            1L,
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
                            "10:00:00",
                            null,
                            1L,
                            "FBI",
                            false,
                            null,
                            null,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            "1234",
                            null,
                            null
                    )
            )
    );

    private final APIResponseEntity<List<OrderDetailEntity>> apiResponseEntityDataNull = new APIResponseEntity<>(
            200,
            orderDetailEntitiesDataNull,
            "CO",
            "",
            "Order detail returned"
    );

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
                            BigDecimal.ZERO,
                            electronicBillDTO,
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
                            "10:00:00",
                            orderDetailPaymentMethodDTOSDefault,
                            1L,
                            "FBI",
                            false,
                            orderDetailProductDTOSDefault,
                            List.of(),
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
                            .discount(BigDecimal.ZERO)
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
                            electronicBillEntity,
                            1L,
                            BigDecimal.ZERO,
                            List.of(),
                            BigDecimal.ZERO,
                            0.0,
                            null,
                            1L,
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
                            "10:00:00",
                            null,
                            1L,
                            "FBI",
                            false,
                            orderDetailProductWithoutGroupsEmptyEntities,
                            OrderDetailServiceEntityMock.getDefaultList(),
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

    /**
     * Products DTOs data null
     */
    private final APIResponseEntity<List<OrderDetailEntity>> apiResponseEntityProductWithGroupDataNull = new APIResponseEntity<>(
            200,
            orderDetailEntitiesWithProductDataNull,
            "CO",
            "",
            "Order detail returned"
    );

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
                            1L,
                            BigDecimal.valueOf(0),
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

    @Test
    void test_GetOrderDetail_Returns_Successfully() throws Exception {
        when(orderBcAPI.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        )).thenReturn(mockValidateGetOrderDetailCall);

        when(mockValidateGetOrderDetailCall.execute()).thenReturn(Response.success(apiResponseEntity));

        final Result<List<OrderDetailDTO>> result = orderDetailRepository.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        );
        assertTrue(result instanceof Result.Success);
        assertNotNull(((Result.Success<List<OrderDetailDTO>>) result).getData());
    }

    @Test
    void test_GetOrderDetail_Coupons_Returns_Successfully() throws Exception {
        when(orderBcAPI.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        )).thenReturn(mockValidateGetOrderDetailCall);

        when(mockValidateGetOrderDetailCall.execute()).thenReturn(Response.success(apiResponseEntityWithCoupons));

        final Result<List<OrderDetailDTO>> result = orderDetailRepository.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        );
        assertTrue(result instanceof Result.Success);
        assertNotNull(((Result.Success<List<OrderDetailDTO>>) result).getData());
    }

    @Test
    void test_GetOrderDetail_Data_Null_Returns_Successfully() throws Exception {
        when(orderBcAPI.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        )).thenReturn(mockValidateGetOrderDetailCall);

        when(mockValidateGetOrderDetailCall.execute()).thenReturn(Response.success(apiResponseEntityDataNull));

        final Result<List<OrderDetailDTO>> result = orderDetailRepository.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        );
        assertTrue(result instanceof Result.Success);
        assertNotNull(((Result.Success<List<OrderDetailDTO>>) result).getData());
    }

    @Test
    void test_GetOrderDetail_Product_Without_Data_Null_Returns_Successfully() throws Exception {
        when(orderBcAPI.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        )).thenReturn(mockValidateGetOrderDetailCall);

        when(mockValidateGetOrderDetailCall.execute()).thenReturn(Response.success(
                apiResponseEntityProductWithGroupDataNull
        ));

        final Result<List<OrderDetailDTO>> result = orderDetailRepository.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        );
        assertTrue(result instanceof Result.Success);
        assertNotNull(((Result.Success<List<OrderDetailDTO>>) result).getData());
    }

    @Test
    void test_GetOrderDetail_Returns_With_Failure() throws Exception {
        when(orderBcAPI.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        )).thenReturn(mockValidateGetOrderDetailCall);

        when(mockValidateGetOrderDetailCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(apiResponseErrorEntity)
        )));

        final Result<List<OrderDetailDTO>> result = orderDetailRepository.getOrderDetail(
                token,
                orderIds,
                orderUids,
                Collections.emptyList()
        );

        assertTrue(result instanceof Result.Error);
    }

}
