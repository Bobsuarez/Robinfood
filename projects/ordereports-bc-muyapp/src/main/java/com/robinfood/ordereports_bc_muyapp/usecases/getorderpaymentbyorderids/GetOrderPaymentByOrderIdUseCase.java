package com.robinfood.ordereports_bc_muyapp.usecases.getorderpaymentbyorderids;

import com.robinfood.app.library.util.ObjectMapperSingleton;
import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderpayment.IOrderPaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class GetOrderPaymentByOrderIdUseCase implements IGetOrderPaymentByOrderIdUseCase {

    private final IOrderPaymentRepository orderPaymentRepository;

    public GetOrderPaymentByOrderIdUseCase(IOrderPaymentRepository orderPaymentRepository) {
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Async
    @Override
    public CompletableFuture<List<OrderPaymentDTO>> invoke(Integer orderId) {
        return CompletableFuture.supplyAsync(() -> findOrderPaymentDTOList(orderId));
    }

    private List<OrderPaymentDTO> findOrderPaymentDTOList(Integer orderId) {
        return orderPaymentRepository.findAllByOrderId(orderId)
                .stream()
                .map(data -> ObjectMapperSingleton.objectToClassConvertValue(data, OrderPaymentDTO.class))
                .toList();
    }
}
