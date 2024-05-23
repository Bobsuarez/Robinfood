package com.robinfood.localserver.electronicbillbc.usescases;

import static com.robinfood.localserver.commons.constants.SefazConstants.ROUNDING_SCALE;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.localserver.commons.dtos.electronicbill.DetDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.TreasuryPaymentsDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.EntityTreasuryDepartmentDTO;
import com.robinfood.localserver.commons.exceptions.IncompleteDataException;
import com.robinfood.localserver.electronicbillbc.mocks.dto.DetDTOMock;
import com.robinfood.localserver.electronicbillbc.mocks.dto.FiscalElectronicCouponDTOMock;
import com.robinfood.localserver.electronicbillbc.mocks.dto.OrderBillingDTOMock;
import com.robinfood.localserver.electronicbillbc.mocks.dto.OrderBillingProductDTOMock;
import com.robinfood.localserver.electronicbillbc.mocks.dto.TreasuryEntitiesDTOMock;
import com.robinfood.localserver.electronicbillbc.mocks.dto.TreasuryPaymentsDTOMocks;
import com.robinfood.localserver.electronicbillbc.repositories.IFiscalElectronicCouponRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SendFiscalElectronicCouponUseCaseTest {

    @InjectMocks
    private SendFiscalElectronicCouponUseCase sendFiscalElectronicCouponUseCase;

    @Mock
    private IEntityFiscalElectronicCouponUseCase entityFiscalElectronicCouponUseCase;

    @Mock
    private IPaymentMethodFiscalElectronicCouponUseCase payMethodFiscalElectronicCouponUseCase;

    @Mock
    private IDetailFiscalElectronicCouponUseCase detailFiscalElectronicCouponUseCase;

    @Mock
    private IFiscalElectronicCouponRepository repository;

    private final OrderBillingDTOMock orderBillingDTO = new OrderBillingDTOMock();

    private final FiscalElectronicCouponDTOMock fiscalElectronicCouponDTOMock = new FiscalElectronicCouponDTOMock();

    private OrderBillingProductDTOMock orderBillingProductDTOMock = new OrderBillingProductDTOMock();

    private TreasuryEntitiesDTOMock treasuryEntitiesDTOMock = new TreasuryEntitiesDTOMock();

    private DetDTOMock detDTOMock = new DetDTOMock();

    private TreasuryPaymentsDTOMocks treasuryPaymentsDTO = new TreasuryPaymentsDTOMocks();

    @Test
    void test_Send_Success_Fiscal_Electronic_Coupon_With_Discount() throws JsonProcessingException {

        final List<EntityTreasuryDepartmentDTO> treasuryEntitiesDTOMockList = new ArrayList<>();
        treasuryEntitiesDTOMockList.add(treasuryEntitiesDTOMock.getDefaultEntityDTOConnectionDataWhitDiscount());
        treasuryEntitiesDTOMockList.add(treasuryEntitiesDTOMock.getDefaultEntityDTOEmitDataWhitDiscount());
        treasuryEntitiesDTOMockList.add(treasuryEntitiesDTOMock.getDefaultEntityDTOIdeDataWhitDiscount());


        final Map<String, String> ideDataMock = new TreeMap<>();
        ideDataMock.put("xNome", "xNome");
        ideDataMock.put("numeroCaixa", "003");
        ideDataMock.put("signAC", "SGR-SAT SISTEMA DE GESTAO E RETAGUARDA DO SAT");
        ideDataMock.put("CNPJ", "16716114000172");

        final Map<String, String> emitDataMock = new TreeMap<>();
        emitDataMock.put("CNPJ", "61099008000141");
        emitDataMock.put("IE", "111111111111");
        emitDataMock.put("IM", "123123");
        emitDataMock.put("cRegTribISSQN", "3");
        emitDataMock.put("indRatISSQN", "N");

        when(entityFiscalElectronicCouponUseCase.invoke(treasuryEntitiesDTOMockList, "emit"))
                .thenReturn(emitDataMock);
        when(entityFiscalElectronicCouponUseCase.invoke(treasuryEntitiesDTOMockList, "ide"))
                .thenReturn(ideDataMock);

        final List<DetDTO> detDTOList = detDTOMock.getDefaultFinalDetDTOListWithDiscounts();

        when(detailFiscalElectronicCouponUseCase.invoke(orderBillingProductDTOMock
                .getDefaultOrderBillingProductDTOWithDiscountsList(),BigDecimal.valueOf(9.95)))
                .thenReturn(detDTOList);

        final List<TreasuryPaymentsDTO> listTreasuryPaymentsDTO = new ArrayList<>();
        listTreasuryPaymentsDTO.add(treasuryPaymentsDTO.getDefaultTreasuryPaymentsDTOCashDataWithDiscount());

        final Map<String, String> payMethodCashMock = new TreeMap<>();
        payMethodCashMock.put("cMP", "01");
        payMethodCashMock.put("vMP", "29.85");

        final List<Map<String, String>> payMethodMockList = new ArrayList<>();
        payMethodMockList.add(payMethodCashMock);

        when(payMethodFiscalElectronicCouponUseCase.invoke(
                listTreasuryPaymentsDTO, BigDecimal.ZERO, BigDecimal.valueOf(29.85))
        ).thenReturn(payMethodMockList);

        when(repository.sendFiscalElectronicCoupon(
                        fiscalElectronicCouponDTOMock.getDefaultFiscalElectronicCouponDTOWithDiscount(),
                        orderBillingDTO.getDefaultOrderBillingDTOWithDiscount().getId()
                )
        ).thenReturn(new SatHubResultDto());

        SatHubResultDto result = sendFiscalElectronicCouponUseCase.invoke(orderBillingDTO.getDefaultOrderBillingDTOWithDiscount());

        Assertions.assertNotNull(result);
    }

    @Test
    void test_Send_Success_Fiscal_Electronic_Coupon() throws JsonProcessingException {

        final List<EntityTreasuryDepartmentDTO> treasuryEntitiesDTOMockList = new ArrayList<>();
        treasuryEntitiesDTOMockList.add(treasuryEntitiesDTOMock.getDefaultEntityDTODestData());
        treasuryEntitiesDTOMockList.add(treasuryEntitiesDTOMock.getDefaultEntityDTOEmitData());
        treasuryEntitiesDTOMockList.add(treasuryEntitiesDTOMock.getDefaultEntityDTOIdeData());

        final Map<String, String> ideDataMock = new TreeMap<>();
        ideDataMock.put("xNome", "xNome");
        ideDataMock.put("numeroCaixa", "002");
        ideDataMock.put("signAC", "GjnnWlqNi94RkP4oDpsY+yIg1xWhAAocOrCJ4wOdLB5E7NV/eJ+QgbI80QiOcgeCRhsjF4I2C8DPjeOd0fcIENHe8HjJZ3rB9gaH8vb4D1DgMbXeg4VFC1pJJpTm56kXH6bZogU55o1oyKNUJfIDSp1P3Ls1EExvkATNSsW88ecg+3krdnKmNw1FKDEHTQjqOqDEdRbToGP/2LQkdyOKdWFZhLSGYID9h+mGGazytYld0o+F6Oa/FFUo41DtKzq8HAQuiTgUPLbo09ngM5ggRIX8G4CD6NO+nExPs/NAjbS7LPpOeowO0ns5yngsdJDdiIXfE5421qgA4yhL3MPPpA==");
        ideDataMock.put("CNPJ", "35880842000160");

        final Map<String, String> emitDataMock = new TreeMap<>();
        emitDataMock.put("CNPJ", "35880842001050");
        emitDataMock.put("IE", "636491161112");
        emitDataMock.put("IM", "123552");
        emitDataMock.put("cRegTribISSQN", "3");
        emitDataMock.put("indRatISSQN", "N");

        when(entityFiscalElectronicCouponUseCase.invoke(treasuryEntitiesDTOMockList, "emit"))
                .thenReturn(emitDataMock);
        when(entityFiscalElectronicCouponUseCase.invoke(treasuryEntitiesDTOMockList, "ide"))
                .thenReturn(ideDataMock);

        final List<DetDTO> detDTOList = detDTOMock.getDefaultFinalDetDTOList();

        when(detailFiscalElectronicCouponUseCase.invoke(orderBillingProductDTOMock.
                getDefaultOrderBillingProductDTOList(),BigDecimal.ZERO)).thenReturn(detDTOList);

        final List<TreasuryPaymentsDTO> listTreasuryPaymentsDTO = new ArrayList<>();
        listTreasuryPaymentsDTO.add(treasuryPaymentsDTO.getDefaultTreasuryPaymentsDTOCashData());
        listTreasuryPaymentsDTO.add(treasuryPaymentsDTO.getDefaultTreasuryPaymentsDTOCreditCardData());

        final Map<String, String> payMethodCashMock = new TreeMap<>();
        payMethodCashMock.put("cPM", "01");
        payMethodCashMock.put("vMP", "16.90");

        final Map<String, String> payMethodCreditCardMock = new TreeMap<>();
        payMethodCreditCardMock.put("cPM", "03");
        payMethodCreditCardMock.put("vMP", "10.00");

        final List<Map<String, String>> payMethodMockList = new ArrayList<>();
        payMethodMockList.add(payMethodCashMock);
        payMethodMockList.add(payMethodCreditCardMock);

        when(payMethodFiscalElectronicCouponUseCase.invoke(
                listTreasuryPaymentsDTO, BigDecimal.ZERO, BigDecimal.valueOf(26.9).setScale(ROUNDING_SCALE))
        ).thenReturn(payMethodMockList);

        when(repository.sendFiscalElectronicCoupon(
                        fiscalElectronicCouponDTOMock.getDefaultFiscalElectronicCouponDTO(),
                        orderBillingDTO.getDefaultOrderBillingDTO().getId()
                )
        ).thenReturn(new SatHubResultDto());

        SatHubResultDto result = sendFiscalElectronicCouponUseCase.invoke(orderBillingDTO.getDefaultOrderBillingDTO());

        Assertions.assertNotNull(result);
    }

    @Test
    void test_Send_Null_Fiscal_Electronic_Coupon() {

        Assertions.assertThrows(NullPointerException.class, () ->
                sendFiscalElectronicCouponUseCase.invoke(null)
        );
    }

    @Test
    void test_Send_Empty_Information_Fiscal_Electronic_Coupon() {

        OrderBillingDTO orderBillingDTO = OrderBillingDTO.builder()
                .id(3338975L)
                .build();

        IncompleteDataException responseException = Assertions.assertThrows(IncompleteDataException.class, () ->
                sendFiscalElectronicCouponUseCase.invoke(orderBillingDTO)
        );

        Assertions.assertEquals("TreasuryPayments of order: 3338975 is empty o null", responseException.getMessage());
    }
}
