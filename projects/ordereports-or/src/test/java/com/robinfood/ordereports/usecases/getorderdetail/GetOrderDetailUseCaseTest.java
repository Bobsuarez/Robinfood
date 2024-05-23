package com.robinfood.ordereports.usecases.getorderdetail;

import com.robinfood.ordereports.dtos.orders.DeliveryCourierServiceDTO;
import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;
import com.robinfood.ordereports.dtos.orders.PaymentDetailDTO;
import com.robinfood.ordereports.mocks.OrderDetailDTOMock;
import com.robinfood.ordereports.models.domain.TokenModel;
import com.robinfood.ordereports.repositories.orderdetail.IGetOrderDetailRepository;
import com.robinfood.ordereports.repositories.paymentmethod.IPaymentMethodRepository;
import com.robinfood.ordereports.repositories.services.IServicesRepository;
import com.robinfood.ordereports.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class GetOrderDetailUseCaseTest {

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IServicesRepository servicesRepository;

    @Mock
    private IGetOrderDetailRepository getOrderDetailRepository;

    @InjectMocks
    private GetOrderDetailUseCase getOrderDetailUseCase;

    private String transactionUuid = "ABC-123";

    @BeforeEach
    void setUp() {
        TokenModel tokenResponse = new TokenModel();
        tokenResponse.setAccessToken("testToken");

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(tokenResponse);
    }

    @Test
    void testInvoke_When_DataIsCorrect() {
        OrderDetailDTO mockOrderDetail = OrderDetailDTOMock.getDataDefault();

        PaymentDetailDTO mockPaymentDetail = new PaymentDetailDTO();
        DeliveryCourierServiceDTO mockServiceDetail = new DeliveryCourierServiceDTO();

        when(getOrderDetailRepository.getOrderDetail(anyString(), anyString())).thenReturn(mockOrderDetail);
        when(paymentMethodRepository.getPaymentMethod(anyString(), anyString())).thenReturn(mockPaymentDetail);
        when(servicesRepository.getServices(anyString(), anyString())).thenReturn(mockServiceDetail);

        OrderDetailDTO result = getOrderDetailUseCase.invoke("ABC-123");

        assertEquals(mockOrderDetail, result);
        verify(getOrderDetailRepository).getOrderDetail("testToken", transactionUuid);
        verify(paymentMethodRepository).getPaymentMethod("testToken", mockOrderDetail.getTransactionUuid());
        verify(servicesRepository).getServices("testToken", mockOrderDetail.getTransactionUuid());
    }

    @Test
    void testInvoke_When_DataIsCorrect_Without_Payment() {
        OrderDetailDTO mockOrderDetail = OrderDetailDTOMock.getDataDefault();
        mockOrderDetail.setPayment(null);

        PaymentDetailDTO mockPaymentDetail = new PaymentDetailDTO();
        DeliveryCourierServiceDTO mockServiceDetail = new DeliveryCourierServiceDTO();

        when(getOrderDetailRepository.getOrderDetail(anyString(), anyString())).thenReturn(mockOrderDetail);
        when(paymentMethodRepository.getPaymentMethod(anyString(), anyString())).thenReturn(mockPaymentDetail);
        when(servicesRepository.getServices(anyString(), anyString())).thenReturn(mockServiceDetail);

        OrderDetailDTO result = getOrderDetailUseCase.invoke("ABC-123");

        assertEquals(mockOrderDetail, result);
        verify(getOrderDetailRepository).getOrderDetail("testToken", transactionUuid);
        verify(paymentMethodRepository).getPaymentMethod("testToken", mockOrderDetail.getTransactionUuid());
        verify(servicesRepository).getServices("testToken", mockOrderDetail.getTransactionUuid());
    }

    @Test
    void testInvoke_WhenOrderDetailThrowsException() {
        when(getOrderDetailRepository.getOrderDetail(anyString(), anyString())).thenThrow(new RuntimeException("Order Detail Error"));

        RuntimeException thrown = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            getOrderDetailUseCase.invoke(transactionUuid);
        });

        assertEquals("Order Detail Error", thrown.getMessage());
        verify(getOrderDetailRepository).getOrderDetail("testToken", transactionUuid);
        verify(paymentMethodRepository, never()).getPaymentMethod(anyString(), anyString());
        verify(servicesRepository, never()).getServices(anyString(), anyString());
    }

    @Test
    void testInvoke_WhenPaymentMethodThrowsException() {
        OrderDetailDTO mockOrderDetail = new OrderDetailDTO();
        mockOrderDetail.setTransactionUuid("uuid");

        when(getOrderDetailRepository.getOrderDetail(anyString(), anyString())).thenReturn(mockOrderDetail);
        when(paymentMethodRepository.getPaymentMethod(anyString(), anyString())).thenThrow(new RuntimeException("Payment Method Error"));

        RuntimeException thrown = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            getOrderDetailUseCase.invoke(transactionUuid);
        });

        assertEquals("Payment Method Error", thrown.getMessage());
        verify(getOrderDetailRepository).getOrderDetail("testToken", transactionUuid);
        verify(paymentMethodRepository).getPaymentMethod("testToken", transactionUuid);
        verify(servicesRepository, never()).getServices(anyString(), anyString());
    }

    @Test
    void testInvoke_WhenServicesThrowsException() {
        OrderDetailDTO mockOrderDetail = new OrderDetailDTO();
        mockOrderDetail.setTransactionUuid("uuid");

        when(getOrderDetailRepository.getOrderDetail(anyString(), anyString())).thenReturn(mockOrderDetail);
        when(paymentMethodRepository.getPaymentMethod(anyString(), anyString())).thenReturn(new PaymentDetailDTO());
        when(servicesRepository.getServices(anyString(), anyString())).thenThrow(new RuntimeException("Services Error"));

        RuntimeException thrown = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            getOrderDetailUseCase.invoke(transactionUuid);
        });

        assertEquals("Services Error", thrown.getMessage());
        verify(getOrderDetailRepository).getOrderDetail("testToken", transactionUuid);
        verify(paymentMethodRepository).getPaymentMethod("testToken", transactionUuid);
        verify(servicesRepository).getServices("testToken", transactionUuid);
    }
}
