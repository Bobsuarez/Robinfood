package com.robinfood.app.usecases.getorderservicesdetails;

import com.robinfood.app.usecases.getresponseservicebyorder.IGetResponseServiceByOrderUseCase;
import com.robinfood.core.dtos.response.orderhistory.GetOrderServicesDetailsDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseServiceDTO;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.repository.orderservices.IOrderServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.robinfood.core.constants.GlobalConstants.MIN_INTEGER_VALUE;

@Component
@Slf4j
@Transactional(readOnly = true)
public class GetOrderServicesUseCase implements IGetOrderServicesUseCase {

    private final IGetResponseServiceByOrderUseCase getServiceByIdUseCase;

    private final IOrderServiceRepository orderServiceRepository;

    public GetOrderServicesUseCase(
            IGetResponseServiceByOrderUseCase getServiceByIdUseCase,
            IOrderServiceRepository orderServiceRepository
    ) {
        this.getServiceByIdUseCase = getServiceByIdUseCase;
        this.orderServiceRepository = orderServiceRepository;
    }

    @Override
    public List<GetOrderServicesDetailsDTO> invoke(Long orderId) {

        final List<OrderServicesEntity> orderServicesList = orderServiceRepository.findAllByOrderId(orderId);

        List<GetOrderServicesDetailsDTO> responseOrderServicesDetailsDTOList = new ArrayList<>();

        orderServicesList.stream().forEach((OrderServicesEntity orderServices) -> {
            responseOrderServicesDetailsDTOList.add(buildOrderServicesDetails(orderServices));
        });

        return responseOrderServicesDetailsDTOList;

    }

    private GetOrderServicesDetailsDTO buildOrderServicesDetails(OrderServicesEntity orderServices) {

        Optional<ResponseServiceDTO> optionalResponseServiceDTO = getServiceByIdUseCase.invoke(orderServices);

        //TODO the quantity right now is a constant in the future it must be made a dynamic variable
        return optionalResponseServiceDTO.map(responseServiceDTO ->
                        GetOrderServicesDetailsDTO.builder()
                                .discount(responseServiceDTO.getDiscount())
                                .id(orderServices.getServiceId())
                                .name(responseServiceDTO.getName())
                                .quantity(MIN_INTEGER_VALUE)
                                .subTotal(responseServiceDTO.getSubtotal())
                                .tax(responseServiceDTO.getTax())
                                .taxPercentage(responseServiceDTO.getTaxPercentage())
                                .unitPrice(orderServices.getTotal())
                                .build())
                .orElse(GetOrderServicesDetailsDTO.builder().build());
    }
}
