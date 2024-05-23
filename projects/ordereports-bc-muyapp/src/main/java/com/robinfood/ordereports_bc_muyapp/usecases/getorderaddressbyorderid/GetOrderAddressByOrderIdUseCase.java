package com.robinfood.ordereports_bc_muyapp.usecases.getorderaddressbyorderid;

import com.robinfood.app.library.util.ObjectMapperSingleton;
import com.robinfood.ordereports_bc_muyapp.dto.OrderAddressDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderaddress.IOrderAddressRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Component
@Slf4j
public class GetOrderAddressByOrderIdUseCase implements IGetOrderAddressByOrderIdUseCase {

    private final IOrderAddressRepository orderAddressRepository;

    @Async
    @Override
    public CompletableFuture<OrderAddressDTO> invoke(Integer orderId) {

        return CompletableFuture.supplyAsync(() -> getDataAddressDTOList(orderId));
    }

    private OrderAddressDTO getDataAddressDTOList(Integer orderId) {

        return orderAddressRepository.findById(orderId)
                .map(data -> ObjectMapperSingleton.objectToClassConvertValue(data, OrderAddressDTO.class))
                .orElse(null);
    }
}
