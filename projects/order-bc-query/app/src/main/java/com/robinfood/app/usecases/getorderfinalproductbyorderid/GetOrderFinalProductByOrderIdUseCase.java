package com.robinfood.app.usecases.getorderfinalproductbyorderid;

import com.robinfood.app.mappers.OrderFinalProductMappersKt;
import com.robinfood.core.dtos.OrderFinalProductDTO;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GetOrderFinalProductByOrderIdUseCase implements IGetOrderFinalProductByOrderIdUseCase {

    private final IOrderFinalProductRepository orderFinalProductRepository;

    public GetOrderFinalProductByOrderIdUseCase(IOrderFinalProductRepository orderFinalProductRepository) {
        this.orderFinalProductRepository = orderFinalProductRepository;
    }

    @Override
    public List<OrderFinalProductDTO> invoke(Long orderId) {

        log.info(
            "Start of the process that searches for the products of an order with orderId {}",
            orderId
        );

        List<OrderFinalProductDTO> orderFinalProductDTOS =  orderFinalProductRepository
            .findAllByOrderId(orderId).stream()
            .map(OrderFinalProductMappersKt::toOrderFinalProductDTO)
            .collect(Collectors.toList());

        log.info("Final products found {}", orderFinalProductDTOS);

        return orderFinalProductDTOS;
    }

}
