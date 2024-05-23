package com.robinfood.app.usecases.getorderaddressbyorderid;

import com.robinfood.app.mappers.OrderAddressMappersKt;
import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.repository.orderaddress.IOrderAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetOrderAddressByOrderIdUseCase implements IGetOrderAddressByOrderIdUseCase {

    private final IOrderAddressRepository orderAddressRepository;

    public GetOrderAddressByOrderIdUseCase(IOrderAddressRepository orderAddressRepository) {
        this.orderAddressRepository = orderAddressRepository;
    }

    @Override
    public OrderAddressDTO invoke(Long orderId) {

        log.info(
            "Start of the process that searches for the address of an order with orderId {}",
            orderId
        );

        OrderAddressDTO orderAddressDTO = orderAddressRepository.findById(orderId)
            .map(OrderAddressMappersKt::toOrderAddressDTO)
            .orElse(null);

        log.info("Address found {}", orderAddressDTO);

        return orderAddressDTO;
    }

}
