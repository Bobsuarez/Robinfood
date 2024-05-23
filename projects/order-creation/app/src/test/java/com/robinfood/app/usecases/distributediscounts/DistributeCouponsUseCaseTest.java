package com.robinfood.app.usecases.distributediscounts;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.DiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DistributeCouponsUseCaseTest {

    @InjectMocks
    private DistributeCouponsUseCase distributeCouponsUseCase;

    private final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
    private final OrderDTO order = transactionRequestDTOMocks.orderDTOs.get(0);

    private final OrderDTO orderWithOutService = transactionRequestDTOMocks.orderDTOsWithOutServices.get(0);
    private final OrderDTO orderDTOsVariousProducts = transactionRequestDTOMocks.orderDTOsVariousProducts.get(0);

    private final OrderDTO orderDTOsVariousProductsWithOutService = transactionRequestDTOMocks
            .orderDTOsVariousProductsWitOutService.get(0);
    private final OrderDTO orderValidateRange = transactionRequestDTOMocks.orderValidateRangeDTOs.get(0);

    private final OrderDTO orderValidateRangeWithOutService = transactionRequestDTOMocks.orderValidateRangeDTOsWithOutService.get(
            0);
    private final OrderDTO orderDTOsRequesDiferrenceDTO =
            transactionRequestDTOMocks.orderDTOsRequesDiferrenceDTO.get(0);

    private final OrderDTO orderDTOsRequesDiferrenceDTOWithOutService =
            transactionRequestDTOMocks.orderDTOsRequesDiferrenceDTOWithOutService.get(0);

    private final OrderDTO orderDTOsRequesDiferrenceDTOWithOutServiceRange =
            transactionRequestDTOMocks.orderDTOsRequesDiferrenceDTOWithOutServiceRange.get(0);

    private final OrderDTO orderDTOsRequesDiferrenceDTOWithOutServiceNotExceeds =
            transactionRequestDTOMocks.orderDTOsRequesDiferrenceDTOWithOutServiceNotExceeds.get(0);

    @Test
    void test_Distribute_coupons() {
        distributeCouponsUseCase.invoke(
                orderWithOutService,
                BigDecimal.valueOf(100.0),
                1L,
                transactionRequestDTOMocks.transactionRequestWithCouponsDTO
        );

        BigDecimal discountTotal = CollectionsKt.map(
                orderWithOutService.getFinalProducts().get(0).getDiscounts(),
                FinalProductDiscountDTO::getValue
        ).stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(new BigDecimal("3102.0833"), discountTotal);
    }

    @Test
    void test_Distribute_coupons_final_products_Various() {

        distributeCouponsUseCase.invoke(
                orderDTOsVariousProductsWithOutService,
                BigDecimal.valueOf(100.0),
                1L,
                transactionRequestDTOMocks.transactionRequestDTOWithVariousProducts
        );

        BigDecimal discountTotal = CollectionsKt.map(
                orderDTOsVariousProductsWithOutService.getDiscounts(),
                DiscountDTO::getValue
        ).stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(new BigDecimal("87.4477"), discountTotal);
    }

    @Test
    void test_Distribute_coupons_final_Orders_With_Margin_Error() {

        distributeCouponsUseCase.invoke(
                orderValidateRangeWithOutService,
                BigDecimal.valueOf(100.0),
                1L,
                transactionRequestDTOMocks.transactionRequestValidateRangeDTO
        );

        BigDecimal discountTotal = CollectionsKt.map(
                orderValidateRangeWithOutService.getDiscounts(),
                DiscountDTO::getValue
        ).stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(new BigDecimal("101.2821"), discountTotal);
    }

    @Test
    void test_Distribute_coupons_Add_difference() {

        distributeCouponsUseCase.invoke(
                orderDTOsRequesDiferrenceDTOWithOutService,
                BigDecimal.valueOf(100.0),
                1L,
                transactionRequestDTOMocks.transactionRequesDiferrenceDTO
        );

        BigDecimal discountTotalOrden1 =
                orderDTOsRequesDiferrenceDTO.getFinalProducts().get(0).getDiscounts().stream()
                        .filter(typeDiscount -> typeDiscount.getIsCoupons().equals(Boolean.TRUE))
                        .map(FinalProductDiscountDTO::getValue)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discountTotalOrden2 =
                orderDTOsRequesDiferrenceDTO.getFinalProducts().get(1).getDiscounts().stream()
                        .filter(typeDiscount -> typeDiscount.getIsCoupons().equals(Boolean.TRUE))
                        .map(FinalProductDiscountDTO::getValue)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        discountTotalOrden2 = discountTotalOrden2
                .multiply(new BigDecimal(orderDTOsRequesDiferrenceDTO.getFinalProducts().get(1)
                        .getQuantity()));

        BigDecimal discountTotal = discountTotalOrden1.add(discountTotalOrden2);

        assertEquals(new BigDecimal("167.3640"), discountTotal);
    }

    @Test
    void test_Distribute_coupons_Add_difference_Margen() {

        distributeCouponsUseCase.invoke(
                orderDTOsRequesDiferrenceDTOWithOutServiceRange,
                BigDecimal.valueOf(9000),
                1L,
                transactionRequestDTOMocks.transactionRequesDiferrenceDTORange
        );

        BigDecimal discountTotal = CollectionsKt.map(
                orderDTOsRequesDiferrenceDTOWithOutServiceRange.getDiscounts(),
                DiscountDTO::getValue
        ).stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(new BigDecimal("5999.9999"), discountTotal);
    }

    @Test
    void test_Distribute_Coupons_Add_difference_Not_Exceeds() {

        distributeCouponsUseCase.invoke(
                orderDTOsRequesDiferrenceDTOWithOutServiceNotExceeds,
                BigDecimal.valueOf(10000),
                1L,
                transactionRequestDTOMocks.transactionRequesDiferrenceDTONotExceeds
        );

        BigDecimal discountTotal = CollectionsKt.map(
                orderDTOsRequesDiferrenceDTOWithOutServiceNotExceeds.getDiscounts(),
                DiscountDTO::getValue
        ).stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(BigDecimal.valueOf(12500.0000).setScale(4), discountTotal);
    }

    @Test
    void test_Distribute_Coupons_When_Without_Coupons() {

        final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTO;

        transactionRequestDTO.setCoupons(List.of());

        distributeCouponsUseCase.invoke(
                orderDTOsRequesDiferrenceDTOWithOutServiceNotExceeds,
                BigDecimal.valueOf(10000),
                1L,
                transactionRequestDTO
        );

        BigDecimal discountTotal = CollectionsKt.map(
                orderDTOsRequesDiferrenceDTOWithOutServiceNotExceeds.getDiscounts(),
                DiscountDTO::getValue
        ).stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertNotNull(discountTotal);
    }
}
