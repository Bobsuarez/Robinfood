package com.robinfood.orderorlocalserver.mocks.dtos;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDetailPrintDTOMock {

    private static final List<OrderDetailPaymentMethodDTO> orderDetailPaymentMethodDTOS = new ArrayList<>(
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

    private static final List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOS = new ArrayList<>(
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

    private static final List<OrderDetailRemovedPortionDTO> orderDetailRemovedPortionDTOS = Collections.singletonList(
            new OrderDetailRemovedPortionDTO(
                    1L,
                    "Rice"
            )
    );

    private static final List<OrderDetailProductGroupDTO> orderDetailProductGroupDTOS = new ArrayList<>(
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

    private static final List<OrderDetailProductTaxDTO> orderDetailProductTaxDTOS = new ArrayList<>(
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

    private static final List<OrderDetailProductDiscountDTO> orderDetailProductDiscountDTOS = Collections.singletonList(
            new OrderDetailProductDiscountDTO(
                    1L,
                    1L,
                    0.0
            )
    );

    private static final List<OrderDetailProductDTO> orderDetailProductDTOS = new ArrayList<>(
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

    private static final OrderDetailUserDTO orderDetailUserDTO = OrderDetailUserDTO.builder()
            .email("bruno@email")
            .firstName("bruno")
            .id(1L)
            .lastName("jason")
            .mobile("300")
            .build();

    private static final List<OrderDetailDiscountDTO> orderDetailDiscountDTOS = Collections.singletonList(
            new OrderDetailDiscountDTO(
                    1L,
                    1L,
                    0.0
            )
    );

    public static List<OrderDetailDTO> getOrderDetailDTOS() {
        return new ArrayList<>(
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
                                "aaasad",
                                "3483eghf",
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
                                "",
                                "1234",
                                orderDetailUserDTO,
                                Collections.singletonList(orderDetailUserDTO)
                        )
                )
        );
    }

}
