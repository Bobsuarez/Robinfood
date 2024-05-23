package com.robinfood.app.usecases.getdetailorderbyuids;

import com.robinfood.app.datamocks.dto.core.GetDataElectronicBillingDTOMock;
import com.robinfood.app.datamocks.dto.core.UserDataDTOMock;
import com.robinfood.app.datamocks.dto.input.OrderFiscalIdentifierDTODataMock;
import com.robinfood.app.datamocks.dto.core.GetOrderThirdPartyDTOMock;
import com.robinfood.app.usecases.getorderbyuids.IGetOrderByUidsUseCase;
import com.robinfood.app.usecases.getorderdetailorder.IGetOrderDetailOrderUseCase;
import com.robinfood.core.dtos.DataElectronicBillingDTO;
import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductDTO;
import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.dtos.UserDataDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderDetailByUidsUseCaseTest {

    @Mock
    private IGetOrderByUidsUseCase mockGetOrderByUidsUseCase;

    @Mock
    private IGetOrderDetailOrderUseCase mockGetOrderDetailOrderUseCase;

    @InjectMocks
    private GetOrderDetailByUidsUseCase getOrderDetailByUidsUseCase;

    private final List<String> uidsList = new ArrayList<>(Collections.singletonList(
            "uid"
    ));

    private final List<Long> idsList = new ArrayList<>(Collections.singletonList(
            1L
    ));

    private final OrderThirdPartyDTO orderThirdPartyDTO = GetOrderThirdPartyDTOMock.getDataDefault();

    private final DataElectronicBillingDTO dataElectronicBillingDTO = GetDataElectronicBillingDTOMock.getDataDefault();

    private final ElectronicBillDTO electronicBillDTO = ElectronicBillDTO.builder()
            .orderElectronicBilling(dataElectronicBillingDTO)
            .orderThirdParty(orderThirdPartyDTO).build();

    private final List<OrderDTO> orderDTOS = new ArrayList<>(Collections.singletonList(
            new OrderDTO(
                    1L,
                    1L,
                    "Brand Name",
                    1L,
                    LocalDateTime.of(2021, 10, 11, 10, 10, 10),
                    "CO",
                    1L,
                    0.0,
                    1L,
                    LocalDate.of(2021, 10, 11),
                    LocalTime.of(10, 10, 10),
                    1,
                    LocalDate.of(2021, 10, 11),
                    "476383",
                    "234234",
                    1L,
                    "Origin Name",
                    Boolean.TRUE,
                    "Pickup Time",
                    1L,
                    Boolean.TRUE,
                    1L,
                    1L,
                    "Store Name",
                    0.0,
                    0.0,
                    1L,
                    BigDecimal.ZERO,
                    0.0,
                    "uid",
                    UUID.randomUUID().toString(),
                    1L,
                    1L
            )
    ));

    private final List<GetOrderDetailDiscountDTO> getOrderDetailDiscountDTOSByOrderId = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailDiscountDTO(
                            1L,
                            1L,
                            null,
                            1L,
                            5000.0
                    )
            )
    );

    private final List<GetOrderDetailPaymentMethodDTO> getOrderDetailPaymentMethodDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailPaymentMethodDTO(
                            0.0,
                            1L,
                            8900.0,
                            1L,
                            1L,
                            0.0,
                            8900.0
                    )
            )
    );

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
                            .sku("sku")
                            .discount(BigDecimal.ZERO)
                            .deduction(BigDecimal.valueOf(8900.0))
                            .orderId(1L)
                            .co2Total(BigDecimal.ZERO)
                            .total(0.0)
                            .build()
    );

    private final UserDataDTO userDataDTO = UserDataDTOMock.getDataDefault();

    private final List<GetOrderDetailDTO> getOrderDetailDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailDTO(
                            1L,
                            "muy",
                            1L,
                            BigDecimal.ZERO,
                            null,
                            "cop",
                            0.0,
                            getOrderDetailDiscountDTOSByOrderId,
                            BigDecimal.ZERO,
                            1L,
                            electronicBillDTO,
                            1L,
                            BigDecimal.ZERO,
                            1L,
                            "",
                            "Notes",
                            LocalDate.parse("2022-06-15"),
                            1L,
                            "pos",
                            "2005",
                            "3483eghf",
                            "asdasd",
                            "User",
                            true,
                            "0000",
                            new OrderFiscalIdentifierDTODataMock().getDataDefault(),
                            getOrderDetailPaymentMethodDTOS,
                            1L,
                            false,
                            getOrderDetailFinalProductDTOS,
                            1L,
                            "Pedido",
                            1L,
                            "muy 79",
                            8900.0,
                            0.0,
                            8900.0,
                            1L,
                            "1234",
                            "asdasd",
                            userDataDTO
                    )
            )
    );

    @Test
    void test_Get_Order_Detail_By_Uids() {
        when(mockGetOrderByUidsUseCase.invoke(uidsList))
                .thenReturn(orderDTOS);

        when(mockGetOrderDetailOrderUseCase.invoke(idsList))
                .thenReturn(getOrderDetailDTOS);

        final List<GetOrderDetailDTO> result = getOrderDetailByUidsUseCase.invoke(uidsList);

        assertEquals(getOrderDetailDTOS, result);
    }
}
