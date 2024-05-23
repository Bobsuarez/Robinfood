package com.robinfood.app.usecases.getorderdetail;

import com.robinfood.core.dtos.DataElectronicBillingDTO;
import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.OrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.dtos.OrderDetailChangedPortionDTO;
import com.robinfood.core.dtos.OrderDetailProductTaxDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailRemovedPortionDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupDTO;
import com.robinfood.core.dtos.OrderDetailProductDiscountDTO;
import com.robinfood.core.dtos.OrderDetailUserDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderDetailDiscountDTO;
import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.dtos.CouponDTO;
import com.robinfood.core.mocks.dto.OrderDetailServiceDTOMock;
import com.robinfood.core.mocks.dto.OrderFiscalIdentifierDTOMock;
import com.robinfood.app.usecases.completeorderproductsdata.ICompleteOrderProductsDataFromMenuCurrentUseCase;
import com.robinfood.app.usecases.completeproducthalldata.ICompleteMenuProductsDataFromMenuHallsUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.orderdetail.IOrderDetailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderDetailUseCaseTest {

    @Mock
    private IOrderDetailRepository mockOrderDetailRepository;

    @Mock
    private ICompleteMenuProductsDataFromMenuHallsUseCase mockCompleteMenuProductsDataFromMenuHallsUseCase;

    @Mock
    private ICompleteOrderProductsDataFromMenuCurrentUseCase mockCompleteOrderProductsDataFromMenuCurrentUseCase;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private GetOrderDetailUseCase getOrderDetailUseCase;

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

    private final OrderDetailChangedPortionDTO orderDetailChangedPortionDTO = new OrderDetailChangedPortionDTO(
            1L,
            "",
            1L,
            "",
            1L,
            1.0
    );

    private final List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOSInvert = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionDTO(
                            true,
                            orderDetailChangedPortionDTO,
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
    private final List<OrderDetailProductGroupDTO> orderDetailProductGroupInvertDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupDTO(
                            1L,
                            "ingredientes",
                            orderDetailProductGroupPortionDTOSInvert,
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

    private final List<OrderDetailProductDTO> orderDetailProductInvertDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductDTO(
                            1L,
                            "Muy paisa",
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
                            orderDetailProductGroupInvertDTOS,
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

    private final List<OrderDetailProductDTO> orderDetailProductDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductDTO(
                            1L,
                            "Muy paisa",
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
                            "asdasd",
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
                            "1234",
                            null,
                            orderDetailUserDTO,
                            Collections.singletonList(orderDetailUserDTO)
                    )
            )
    );

    private final List<OrderDetailDTO> orderDetailWithCouponsDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailDTO(
                            null,
                            1L,
                            "muy",
                            BigDecimal.valueOf(1L),
                            "cop",
                            List.of(CouponDTO.builder().code("PRUEBA").build()),
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

    @Test
    void test_OrderDetail_Returns_Correctly() {

        final String token = "token";
        final List<Long> orderIds = new ArrayList<>(Collections.singletonList(1L));
        final List<String> orderUids = new ArrayList<>(Collections.singletonList("uids"));

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(mockOrderDetailRepository.getOrderDetail(token, orderIds, orderUids, List.of()))
                .thenReturn(new Result.Success<>(orderDetailDTOS));

        final Result<List<OrderDetailDTO>> result = getOrderDetailUseCase.invoke(
                1L,
                1L,
                orderIds,
                orderUids,
                Collections.emptyList()
        );

        orderDetailDTOS.forEach(
                orderDetailDTO -> {
                    verify(mockCompleteOrderProductsDataFromMenuCurrentUseCase).invoke(
                            1L,
                            1L,
                            orderDetailDTO.getProducts(),
                            orderDetailDTO.getStoreId(),
                            token
                    );
                    verify(mockCompleteMenuProductsDataFromMenuHallsUseCase).invoke(
                            1L,
                            1L,
                            orderDetailDTO.getProducts(),
                            orderDetailDTO.getStoreId(),
                            token
                    );
                }
        );

        assertEquals(orderDetailDTOS, ((Result.Success<List<OrderDetailDTO>>) result).getData());
    }

    @Test
    void test_OrderDetail_With_Coupons_Returns_Correctly() {

        final String token = "token";
        final List<Long> orderIds = new ArrayList<>(Collections.singletonList(1L));
        final List<String> orderUids = new ArrayList<>(Collections.singletonList("uids"));

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(mockOrderDetailRepository.getOrderDetail(token, orderIds, orderUids, List.of()))
                .thenReturn(new Result.Success<>(orderDetailWithCouponsDTOS));

        final Result<List<OrderDetailDTO>> result = getOrderDetailUseCase.invoke(
                1L,
                1L,
                orderIds,
                orderUids,
                Collections.emptyList()
        );

        orderDetailWithCouponsDTOS.forEach(
                orderDetailDTO -> {
                    verify(mockCompleteOrderProductsDataFromMenuCurrentUseCase).invoke(
                            1L,
                            1L,
                            orderDetailDTO.getProducts(),
                            orderDetailDTO.getStoreId(),
                            token
                    );
                    verify(mockCompleteMenuProductsDataFromMenuHallsUseCase).invoke(
                            1L,
                            1L,
                            orderDetailDTO.getProducts(),
                            orderDetailDTO.getStoreId(),
                            token
                    );
                }
        );

        assertEquals(orderDetailWithCouponsDTOS, ((Result.Success<List<OrderDetailDTO>>) result).getData());
    }

    @Test
    void test_invertReplacementPortions_Success() {
        GetOrderDetailUseCase.invertReplacementPortions(orderDetailProductInvertDTOS);
        assertEquals(1L, orderDetailProductDTOS.get(0).getArticleId());

    }
}
