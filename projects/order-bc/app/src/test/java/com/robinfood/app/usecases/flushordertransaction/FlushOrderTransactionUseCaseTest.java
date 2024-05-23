package com.robinfood.app.usecases.flushordertransaction;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.repository.orderbrandhistory.IOrderBrandHistoryRepository;
import com.robinfood.repository.orderchangedportions.IOrderChangedPortionRepository;
import com.robinfood.repository.ordercoupons.IOrderCouponRepository;
import com.robinfood.repository.orderdeduction.IOrderDeductionRepository;
import com.robinfood.repository.orderdeductiontrasaction.IDeductionTransactionRepository;
import com.robinfood.repository.orderdetail.IOrderDetailRepository;
import com.robinfood.repository.orderdevice.IOrderDeviceRepository;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import com.robinfood.repository.orderexternaldiscount.IOrderExternalDiscountRepository;
import com.robinfood.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orderfiscalidentifier.IOrderFiscalIdentifierRepository;
import com.robinfood.repository.orderflag.IOrderFlagRepository;
import com.robinfood.repository.orderflagcorporate.IOrderFlagCorporateRepository;
import com.robinfood.repository.orderflagintegration.IOrderFlagIntegrationRepository;
import com.robinfood.repository.orderflagpits.IOrderFlagPitsRepository;
import com.robinfood.repository.orderflagsubmarine.IOrderFlagSubmarineRepository;
import com.robinfood.repository.orderflagtogo.IOrderFlagTogoRepository;
import com.robinfood.repository.orderfoodcoins.IOrderFoodCoinRepository;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import com.robinfood.repository.orderpaymentdetail.IOrderPaymentDetailRepository;
import com.robinfood.repository.orderproductodeduction.IOrderProductFinalDeductionRepository;
import com.robinfood.repository.orderproducttaxes.IOrderProductTaxesRepository;
import com.robinfood.repository.orderremovedportions.IOrderRemovedPortionRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.orderservices.IOrderServiceRepository;
import com.robinfood.repository.orderuserdata.IOrderUserDataRepository;
import com.robinfood.repository.payment.IPaymentRepository;
import com.robinfood.repository.transactionflow.ITransactionFlowRepository;
import com.robinfood.repository.userdata.IUserDataRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlushOrderTransactionUseCaseTest {

    @Mock
    private IDeductionTransactionRepository deductionTransactionRepository;

    @Mock
    private IOrderBrandHistoryRepository orderBrandHistoryRepository;

    @Mock
    private IOrderChangedPortionRepository orderChangedPortionRepository;

    @Mock
    private IOrderDeviceRepository orderDeviceRepository;

    @Mock
    private IOrderDiscountCRUDRepository orderDiscountCRUDRepository;

    @Mock
    private IOrderExternalDiscountRepository orderExternalDiscountRepository;

    @Mock
    private IOrderFinalProductRepository orderFinalProductRepository;

    @Mock
    private IOrderFlagCorporateRepository orderFlagCorporateRepository;

    @Mock
    private IOrderFlagPitsRepository orderFlagPitsRepository;

    @Mock
    private IOrderFlagTogoRepository orderFlagTogoRepository;

    @Mock
    private IOrderProductTaxesRepository orderProductTaxesRepository;

    @Mock
    private IOrderHistoryRepository orderHistoryRepository;

    @Mock
    private IOrdersRepository ordersRepository;

    @Mock
    private IPaymentRepository paymentRepository;

    @Mock
    private IOrderDetailRepository orderDetailRepository;

    @Mock
    private IOrderFinalProductPortionRepository orderFinalProductPortionRepository;

    @Mock
    private IOrderFlagIntegrationRepository orderFlagIntegrationRepository;

    @Mock
    private IOrderFlagRepository orderFlagRepository;

    @Mock
    private IOrderFlagSubmarineRepository orderFlagSubmarineRepository;

    @Mock
    private IOrderIntegrationRepository orderIntegrationRepository;

    @Mock
    private IOrderPaymentRepository orderPaymentRepository;

    @Mock
    private IOrderPaymentDetailRepository orderPaymentDetailRepository;

    @Mock
    private IOrderRemovedPortionRepository orderRemovedPortionRepository;

    @Mock
    private IOrderUserDataRepository orderUserDataRepository;

    @Mock
    private IOrderProductFinalDeductionRepository orderProductFinalDeductionRepository;

    @Mock
    private IUserDataRepository userDataRepository;

    @Mock
    private IOrderCouponRepository orderCouponRepository;

    @Mock
    private ITransactionFlowRepository transactionFlowRepository;

    @Mock
    private IOrderDeductionRepository orderDeductionRepository;

    @Mock
    private IOrderFiscalIdentifierRepository orderFiscalIdentifierRepository;

    @Mock
    private IOrderFoodCoinRepository orderFoodCoinRepository;

    @Mock
    private IOrderServiceRepository orderServiceRepository;

    @InjectMocks
    FlushOrderTransactionUseCase flushOrderTransactionUseCase;

    private final List<OrderDTO> orderDTOList = new OrderDTODataMock().getDataDefaultList();

    private final RequestOrderTransactionDTO inputOrderTransactionDTO = new RequestOrderTransactionDTO(
            false,
            null,
            null,
            null,
            null,
            3L,
            orderDTOList,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            1L,
            UUID.randomUUID()
    );

    final List<Long> orderIds = new ArrayList<>(Collections.singletonList(
            1L
    ));

    @Test
    void test_Flush_Order_Transaction(){

        final Long transactionId = 1L;

        Mockito.doNothing().when(orderBrandHistoryRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderChangedPortionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderDiscountCRUDRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderDeviceRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderExternalDiscountRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderFinalProductRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderFlagCorporateRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderFlagPitsRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderFlagTogoRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderProductTaxesRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderHistoryRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(ordersRepository).deleteAllByIdIsIn(orderIds);
        Mockito.doNothing().when(paymentRepository).deleteAllByTransactionId(transactionId);
        Mockito.doNothing().when(orderDetailRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderFinalProductPortionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderFlagIntegrationRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderFlagRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderFlagSubmarineRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderIntegrationRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when( orderPaymentRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderPaymentDetailRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderRemovedPortionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderUserDataRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(userDataRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderDeductionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderProductFinalDeductionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(deductionTransactionRepository).deleteAllByTrasactionId(transactionId);

        Mockito.doNothing().when(orderCouponRepository).deleteOrderCouponEntitiesByTransactionId(transactionId);
        Mockito.doNothing().when(transactionFlowRepository).deleteTransactionFlowEntityByTransactionId(transactionId);
        Mockito.doNothing().when(orderFoodCoinRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.doNothing().when(orderServiceRepository).deleteAllByOrderIdIsIn(orderIds);

        Void result = flushOrderTransactionUseCase.invoke(inputOrderTransactionDTO).join();

        Mockito.verify(orderBrandHistoryRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderChangedPortionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderDeviceRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderDiscountCRUDRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderExternalDiscountRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderFinalProductRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderFlagCorporateRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderFlagPitsRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderFlagTogoRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderProductTaxesRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderHistoryRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(ordersRepository).deleteAllByIdIsIn(orderIds);
        Mockito.verify(paymentRepository).deleteAllByTransactionId(transactionId);
        Mockito.verify(orderDetailRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderFinalProductPortionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderFlagIntegrationRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderFlagRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderFlagSubmarineRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderIntegrationRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderPaymentRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderPaymentDetailRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderRemovedPortionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderUserDataRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(userDataRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderDeductionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(orderProductFinalDeductionRepository).deleteAllByOrderIdIsIn(orderIds);
        Mockito.verify(deductionTransactionRepository).deleteAllByTrasactionId(transactionId);
        Mockito.verify(orderCouponRepository).deleteOrderCouponEntitiesByTransactionId(transactionId);
        Mockito.verify(orderFiscalIdentifierRepository).deleteByTransactionId(transactionId);
        Mockito.verify(transactionFlowRepository).deleteTransactionFlowEntityByTransactionId(transactionId);

        assertNull(result);
    }
}
