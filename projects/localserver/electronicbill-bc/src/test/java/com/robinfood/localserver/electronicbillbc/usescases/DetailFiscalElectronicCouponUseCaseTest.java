package com.robinfood.localserver.electronicbillbc.usescases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.localserver.commons.dtos.electronicbill.DetDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingProductDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.TreasuryCategoryDTO;
import com.robinfood.localserver.commons.exceptions.IncompleteDataException;
import com.robinfood.localserver.electronicbillbc.mocks.dto.DetDTOMock;
import com.robinfood.localserver.electronicbillbc.mocks.dto.OrderBillingProductDTOMock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DetailFiscalElectronicCouponUseCaseTest {

    @InjectMocks
    private DetailFiscalElectronicCouponUseCase detailFiscalElectronicCouponUseCase;

    private List<OrderBillingProductDTO> productDTOList = new ArrayList<>();

    private OrderBillingProductDTOMock orderBillingProductDTOMock = new OrderBillingProductDTOMock();

    private DetDTOMock detDTOMock = new DetDTOMock();

    @Test
    void test_Get_Detail_Success() throws JsonProcessingException {

        final List<DetDTO> detDTOList = detailFiscalElectronicCouponUseCase.invoke(
                orderBillingProductDTOMock.getDefaultOrderBillingProductDTOList(), BigDecimal.ZERO
        );

        Assertions.assertEquals(detDTOList, detDTOMock.getDefaultFinalDetDTOList());
    }

    @Test
    void test_Get_Detail_Success_With_Discount() throws JsonProcessingException {

        final List<DetDTO> detDTOList = detailFiscalElectronicCouponUseCase.invoke(
                orderBillingProductDTOMock.getDefaultOrderBillingProductDTORequestList(), BigDecimal.valueOf(1.0)
        );

        Assertions.assertEquals(detDTOList, detDTOMock.getDefaultFinalDetDTOListResponseWithDiscounts());
    }

    @Test
    void test_Get_Detail_Success_With_Discount_And_Rounding() throws JsonProcessingException {

        final List<DetDTO> detDTOList = detailFiscalElectronicCouponUseCase.invoke(
                orderBillingProductDTOMock.getDefaultOrderBillingProductDTOWithDiscountsRounding(),
                BigDecimal.valueOf(3.99)
        );

        Assertions.assertEquals(detDTOList, detDTOMock.getDefaultFinalDetDTOListResponseWithDiscountsRounding());
    }

    @Test
    void test_Get_Detail_With_Empty_Object() {

        final List<DetDTO> listDetExp = new ArrayList<>();
        final List<DetDTO> detDTOList = detailFiscalElectronicCouponUseCase.invoke(productDTOList, BigDecimal.ZERO);

        Assertions.assertEquals(detDTOList, listDetExp);
    }

    @Test
    void test_Get_Detail_With_Null_Object() {

        Assertions.assertThrows(NullPointerException.class, () -> {
                    detailFiscalElectronicCouponUseCase.invoke(null, BigDecimal.ZERO);
                }
        );
    }

    @Test
    void test_Get_Detail_With_Null_Treasury_Categories() throws JsonProcessingException {

        productDTOList.add(orderBillingProductDTOMock.getDefaultOrderBillingProductDTO());
        productDTOList.get(0).setTreasuryCategory(TreasuryCategoryDTO.builder().build());

        IncompleteDataException responseException = Assertions.assertThrows(IncompleteDataException.class, () -> {
                    detailFiscalElectronicCouponUseCase.invoke(productDTOList, BigDecimal.ZERO);
                }
        );

        Assertions.assertEquals("TreasuryCategory is null or Empty", responseException.getMessage());
    }

    @Test
    void test_Get_Detail_With_Null_Taxes() throws JsonProcessingException {

        productDTOList.add(orderBillingProductDTOMock.getDefaultOrderBillingProductDTO());
        productDTOList.get(0).setTaxes(Collections.emptyList());

        IncompleteDataException responseException = Assertions.assertThrows(IncompleteDataException.class, () -> {
                    detailFiscalElectronicCouponUseCase.invoke(productDTOList, BigDecimal.ZERO);
                }
        );

        Assertions.assertEquals("taxes of product 179 is null or Empty", responseException.getMessage());
    }

    @Test
    void test_Get_Detail_With_Null_Treasury_Taxes() throws JsonProcessingException {

        productDTOList.add(orderBillingProductDTOMock.getDefaultOrderBillingProductDTO());
        productDTOList.get(0).getTaxes().get(0).setTreasuryTaxes(Collections.emptyList());

        IncompleteDataException responseException = Assertions.assertThrows(IncompleteDataException.class, () -> {
                    detailFiscalElectronicCouponUseCase.invoke(productDTOList, BigDecimal.ZERO);
                }
        );

        Assertions.assertEquals("treasuryTaxes of product 179 is null or Empty", responseException.getMessage());
    }
}
