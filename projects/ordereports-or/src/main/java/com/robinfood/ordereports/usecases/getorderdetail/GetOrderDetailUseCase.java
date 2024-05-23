package com.robinfood.ordereports.usecases.getorderdetail;

import com.robinfood.ordereports.dtos.orders.DeliveryCourierServiceDTO;
import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;
import com.robinfood.ordereports.dtos.orders.PaymentDetailDTO;
import com.robinfood.ordereports.repositories.orderdetail.IGetOrderDetailRepository;
import com.robinfood.ordereports.repositories.paymentmethod.IPaymentMethodRepository;
import com.robinfood.ordereports.repositories.services.IServicesRepository;
import com.robinfood.ordereports.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.robinfood.ordereports.enums.OrderDetailLogEnum.DATA_RESPONSE_REQUEST;

@Component
@Slf4j
public class GetOrderDetailUseCase implements IGetOrderDetailUseCase{

    private final IPaymentMethodRepository paymentMethodRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IServicesRepository servicesRepository;
    private final IGetOrderDetailRepository getOrderDetailRepository;


    public GetOrderDetailUseCase(IPaymentMethodRepository paymentMethodRepository,
                                 IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
                                 IServicesRepository servicesRepository,
                                 IGetOrderDetailRepository getOrderDetailRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.servicesRepository = servicesRepository;
        this.getOrderDetailRepository = getOrderDetailRepository;
    }

    @Override
    public OrderDetailDTO invoke(String transactionUuid) {

        String token = getToken();

        OrderDetailDTO orderDetailDTO = getOrderDetail(transactionUuid, token);

        PaymentDetailDTO paymentDetailDTO = getPaymentMethod(token, transactionUuid);

        DeliveryCourierServiceDTO deliveryCourierServiceDTO = getServices(token, transactionUuid);

        return setDataOrderDetail(orderDetailDTO, deliveryCourierServiceDTO, paymentDetailDTO, transactionUuid);

    }

    private OrderDetailDTO getOrderDetail(String transactionUuid, String token) {
        return getOrderDetailRepository.getOrderDetail(token, transactionUuid);
    }

    private DeliveryCourierServiceDTO getServices(String token, String transactionUuid) {
        return servicesRepository.getServices(token, transactionUuid);
    }

    private PaymentDetailDTO getPaymentMethod(String token, String transactionUuid) {
        return paymentMethodRepository.getPaymentMethod(token, transactionUuid);
    }

    private String getToken(){
        return getTokenBusinessCapabilityUseCase.invoke().getAccessToken();
    }

    private OrderDetailDTO setDataOrderDetail(OrderDetailDTO orderDetailDTO,
                                              DeliveryCourierServiceDTO deliveryCourierServiceDTO,
                                              PaymentDetailDTO paymentDetailDTO,
                                              String transactionUuid){
        orderDetailDTO.setTransactionUuid(transactionUuid);
        orderDetailDTO.setDeliveryCourierService(deliveryCourierServiceDTO);

        if (orderDetailDTO.getPayment() != null) {
            orderDetailDTO.getPayment().setDetail(paymentDetailDTO);
        }

        log.info(DATA_RESPONSE_REQUEST.getMessage(), orderDetailDTO);

        return orderDetailDTO;
    }
}
