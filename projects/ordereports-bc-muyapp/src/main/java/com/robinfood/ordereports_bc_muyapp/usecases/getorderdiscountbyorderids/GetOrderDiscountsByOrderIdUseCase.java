package com.robinfood.ordereports_bc_muyapp.usecases.getorderdiscountbyorderids;

import com.robinfood.app.library.util.ObjectMapperSingleton;
import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderdiscount.IOrderDiscountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Component
@Slf4j
public class GetOrderDiscountsByOrderIdUseCase implements IGetOrderDiscountsByOrderIdUseCase {

    private final IOrderDiscountRepository orderDiscountRepository;

    @Async
    @Override
    public CompletableFuture<List<OrderDiscountDTO>> invoke(Integer orderId) {

        return CompletableFuture.supplyAsync(() -> getDataOrdersDiscountList(orderId));
    }

    private List<OrderDiscountDTO> getDataOrdersDiscountList(Integer orderId) {
        return orderDiscountRepository.findOrderDiscountEntitiesByOrderId(orderId)
                .stream()
                .map(data -> ObjectMapperSingleton.objectToClassConvertValue(data, OrderDiscountDTO.class))
                .toList();
    }
}
