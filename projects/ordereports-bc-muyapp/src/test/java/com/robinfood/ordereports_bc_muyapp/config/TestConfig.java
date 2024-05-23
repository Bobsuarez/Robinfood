package com.robinfood.ordereports_bc_muyapp.config;

import com.robinfood.ordereports_bc_muyapp.repository.orderaddress.IOrderAddressRepository;
import com.robinfood.ordereports_bc_muyapp.repository.orderdevice.IOrderDeviceRepository;
import com.robinfood.ordereports_bc_muyapp.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import com.robinfood.ordereports_bc_muyapp.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.ordereports_bc_muyapp.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.ordereports_bc_muyapp.repository.orderpayment.IOrderPaymentRepository;
import com.robinfood.ordereports_bc_muyapp.repository.orders.IOrdersRepository;
import com.robinfood.ordereports_bc_muyapp.repository.orders.transactionflow.ITransactionFlowRepository;
import com.robinfood.ordereports_bc_muyapp.repository.paymentmethod.IPaymentMethodRepository;
import com.robinfood.ordereports_bc_muyapp.repository.status.IStatusRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestConfig {

    @MockBean
    private IOrderAddressRepository orderAddressRepository;

    @MockBean
    private IOrdersRepository ordersRepository;

    @MockBean
    private IOrderDeviceRepository orderDeviceRepository;

    @MockBean
    private IOrderFinalProductPortionRepository orderFinalProductPortionRepository;

    @MockBean
    private IOrderHistoryRepository orderHistoryRepository;

    @MockBean
    private IOrderFinalProductRepository orderFinalProductRepository;

    @MockBean
    private IStatusRepository statusRepository;

    @MockBean
    private IOrderPaymentRepository orderPaymentRepository;

    @MockBean
    private ITransactionFlowRepository transactionFlowRepository;

    @MockBean
    private IPaymentMethodRepository paymentMethodRepository;

}
