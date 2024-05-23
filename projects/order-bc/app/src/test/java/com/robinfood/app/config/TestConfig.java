package com.robinfood.app.config;

import com.robinfood.repository.deliverytype.IDeliveryTypeRepository;
import com.robinfood.repository.orderaddress.IOrderAddressRepository;
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
import com.robinfood.repository.orderflag.IOrderFlagRepository;
import com.robinfood.repository.orderflagcorporate.IOrderFlagCorporateRepository;
import com.robinfood.repository.orderflagintegration.IOrderFlagIntegrationRepository;
import com.robinfood.repository.orderflagpits.IOrderFlagPitsRepository;
import com.robinfood.repository.orderflagsubmarine.IOrderFlagSubmarineRepository;
import com.robinfood.repository.orderflagtogo.IOrderFlagTogoRepository;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import com.robinfood.repository.orderpaymentdetail.IOrderPaymentDetailRepository;
import com.robinfood.repository.orderproductodeduction.IOrderProductFinalDeductionRepository;
import com.robinfood.repository.orderproducttaxes.IOrderProductTaxesRepository;
import com.robinfood.repository.orderremovedportions.IOrderRemovedPortionRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.orderuserdata.IOrderUserDataRepository;
import com.robinfood.repository.payment.IPaymentRepository;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import com.robinfood.repository.pickuptime.IPickupTimeRepository;
import com.robinfood.repository.status.IStatusRepository;
import com.robinfood.repository.transaction.ITransactionCRUDRepository;
import com.robinfood.repository.transactionflow.ITransactionFlowRepository;
import com.robinfood.repository.userdata.IUserDataRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestConfig {

    @MockBean
    private IOrderAddressRepository orderAddressRepository;

    @MockBean
    private IOrderDeductionRepository orderDeductionRepository;

    @MockBean
    private IDeductionTransactionRepository deductionTransactionRepository;

    @MockBean
    private IOrderProductFinalDeductionRepository orderProductFinalDeductionRepository;

    @MockBean
    private IOrderIntegrationRepository orderIntegrationRepository;

    @MockBean
    private IOrdersRepository ordersRepository;

    @MockBean
    private IOrderDeviceRepository orderDeviceRepository;

    @MockBean
    private ITransactionCRUDRepository transactionRepository;

    @MockBean
    private IOrderUserDataRepository orderUserDataRepository;

    @MockBean
    private IUserDataRepository userDataRepository;

    @MockBean
    private IOrderDetailRepository orderDetailRepository;

    @MockBean
    private IOrderFinalProductPortionRepository orderFinalProductPortionRepository;

    @MockBean
    private IOrderHistoryRepository orderHistoryRepository;

    @MockBean
    private IOrderFlagCorporateRepository orderFlagCorporateRepository;

    @MockBean
    private IOrderFlagPitsRepository orderFlagPitsRepository;

    @MockBean
    private IOrderFlagRepository orderFlagRepository;

    @MockBean
    private IOrderFinalProductRepository orderFinalProductRepository;

    @MockBean
    private IOrderRemovedPortionRepository orderRemovedPortionRepository;

    @MockBean
    private IPaymentRepository paymentRepository;

    @MockBean
    private IOrderDiscountCRUDRepository orderDiscountCRUDRepository;

    @MockBean
    private IDeliveryTypeRepository deliveryTypeRepository;

    @MockBean
    private IOrderFlagSubmarineRepository orderFlagSubmarineRepository;

    @MockBean
    private IStatusRepository statusRepository;

    @MockBean
    private IOrderBrandHistoryRepository orderBrandHistoryRepository;

    @MockBean
    private IOrderChangedPortionRepository orderChangedPortionRepository;

    @MockBean
    private IOrderPaymentDetailRepository orderPaymentDetailRepository;

    @MockBean
    private IOrderProductTaxesRepository orderProductTaxesRepository;

    @MockBean
    private IOrderFlagTogoRepository orderFlagTogoRepository;

    @MockBean
    private IOrderFlagIntegrationRepository orderFlagIntegrationRepository;

    @MockBean
    private IOrderPaymentRepository orderPaymentRepository;

    @MockBean
    private IPickupTimeRepository pickupTimeRepository;

    @MockBean
    private IOrderExternalDiscountRepository orderExternalDiscountRepository;

    @MockBean
    private IOrderCouponRepository orderCouponRepository;

    @MockBean
    private ITransactionFlowRepository transactionFlowRepository;

    @MockBean
    private IPaymentMethodRepository paymentMethodRepository;

}
