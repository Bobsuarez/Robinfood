package com.robinfood.app.usecases.flushordertransaction;

import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.repository.orderbrandhistory.IOrderBrandHistoryRepository;
import com.robinfood.repository.orderchangedportions.IOrderChangedPortionRepository;
import com.robinfood.repository.orderdeduction.IOrderDeductionRepository;
import com.robinfood.repository.orderdeductiontrasaction.IDeductionTransactionRepository;
import com.robinfood.repository.ordercoupons.IOrderCouponRepository;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;

import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of IFlushOrderTransactionUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
@Transactional
public class FlushOrderTransactionUseCase implements IFlushOrderTransactionUseCase {

    private final IOrderBrandHistoryRepository orderBrandHistoryRepository;
    private final IOrderChangedPortionRepository orderChangedPortionRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IOrderDeviceRepository orderDeviceRepository;
    private final IOrderDiscountCRUDRepository orderDiscountCRUDRepository;
    private final IOrderExternalDiscountRepository orderExternalDiscountRepository;
    private final IOrderIntegrationRepository orderIntegrationRepository;
    private final IOrderFinalProductRepository orderFinalProductRepository;
    private final IOrderFinalProductPortionRepository orderFinalProductPortionRepository;
    private final IOrderFlagCorporateRepository orderFlagCorporateRepository;
    private final IOrderFlagIntegrationRepository orderFlagIntegrationRepository;
    private final IOrderFlagRepository orderFlagRepository;
    private final IOrderFlagPitsRepository orderFlagPitsRepository;
    private final IOrderFlagSubmarineRepository orderFlagSubmarineRepository;
    private final IOrderFlagTogoRepository orderFlagTogoRepository;
    private final IOrderHistoryRepository orderHistoryRepository;
    private final IOrderProductTaxesRepository orderProductTaxesRepository;
    private final IOrderPaymentRepository orderPaymentRepository;
    private final IOrderPaymentDetailRepository orderPaymentDetailRepository;
    private final IOrderRemovedPortionRepository orderRemovedPortionRepository;
    private final IOrdersRepository ordersRepository;
    private final IOrderUserDataRepository orderUserDataRepository;
    private final IPaymentRepository paymentRepository;
    private final IUserDataRepository userDataRepository;
    private final IOrderDeductionRepository orderDeductionRepository;
    private final IOrderProductFinalDeductionRepository orderProductFinalDeductionRepository;
    private final IDeductionTransactionRepository deductionTransactionRepository;
    private final IOrderCouponRepository orderCouponRepository;
    private final IOrderFiscalIdentifierRepository orderFiscalIdentifierRepository;
    private final ITransactionFlowRepository transactionFlowRepository;

    private final IOrderFoodCoinRepository orderFoodCoinRepository;

    private final IOrderServiceRepository orderServiceRepository;

    @Override
    public CompletableFuture<Void> invoke(RequestOrderTransactionDTO orderTransactionDTO) {

        log.info("Flush orders has started with orders: {}", orderTransactionDTO.getOrders());

        final Long transactionId = orderTransactionDTO.getId();

        if (transactionId == null) {
            log.warn("Order transaction id is null");
            return CompletableFuture.completedFuture(null);
        }

        final List<OrderDTO> orderDTOList = orderTransactionDTO.getOrders();

        final List<Long> orderIds = CollectionsKt.map(
                orderDTOList,
                OrderDTO::getId
        );

        deductionTransactionRepository.deleteAllByTrasactionId(orderTransactionDTO.getId());
        orderBrandHistoryRepository.deleteAllByOrderIdIsIn(orderIds);
        orderChangedPortionRepository.deleteAllByOrderIdIsIn(orderIds);
        orderDiscountCRUDRepository.deleteAllByOrderIdIsIn(orderIds);
        orderDeviceRepository.deleteAllByOrderIdIsIn(orderIds);
        orderExternalDiscountRepository.deleteAllByOrderIdIsIn(orderIds);
        orderFinalProductRepository.deleteAllByOrderIdIsIn(orderIds);
        orderFlagCorporateRepository.deleteAllByOrderIdIsIn(orderIds);
        orderFlagPitsRepository.deleteAllByOrderIdIsIn(orderIds);
        orderFlagTogoRepository.deleteAllByOrderIdIsIn(orderIds);
        orderProductTaxesRepository.deleteAllByOrderIdIsIn(orderIds);
        orderHistoryRepository.deleteAllByOrderIdIsIn(orderIds);
        ordersRepository.deleteAllByIdIsIn(orderIds);
        paymentRepository.deleteAllByTransactionId(transactionId);
        orderDetailRepository.deleteAllByOrderIdIsIn(orderIds);
        orderFinalProductPortionRepository.deleteAllByOrderIdIsIn(orderIds);
        orderFlagIntegrationRepository.deleteAllByOrderIdIsIn(orderIds);
        orderFlagRepository.deleteAllByOrderIdIsIn(orderIds);
        orderFlagSubmarineRepository.deleteAllByOrderIdIsIn(orderIds);
        orderIntegrationRepository.deleteAllByOrderIdIsIn(orderIds);
        orderPaymentRepository.deleteAllByOrderIdIsIn(orderIds);
        orderPaymentDetailRepository.deleteAllByOrderIdIsIn(orderIds);
        orderRemovedPortionRepository.deleteAllByOrderIdIsIn(orderIds);
        orderUserDataRepository.deleteAllByOrderIdIsIn(orderIds);
        userDataRepository.deleteAllByOrderIdIsIn(orderIds);
        orderDeductionRepository.deleteAllByOrderIdIsIn(orderIds);
        orderProductFinalDeductionRepository.deleteAllByOrderIdIsIn(orderIds);
        orderCouponRepository.deleteOrderCouponEntitiesByTransactionId(transactionId);
        orderFiscalIdentifierRepository.deleteByTransactionId(transactionId);
        transactionFlowRepository.deleteTransactionFlowEntityByTransactionId(transactionId);
        orderFoodCoinRepository.deleteAllByOrderIdIsIn(orderIds);
        orderServiceRepository.deleteAllByOrderIdIsIn(orderIds);
        log.info("Flush orders successfully");

        return CompletableFuture.completedFuture(null);
    }
}
