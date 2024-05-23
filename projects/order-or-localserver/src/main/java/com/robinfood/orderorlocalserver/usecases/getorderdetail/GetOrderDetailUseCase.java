package com.robinfood.orderorlocalserver.usecases.getorderdetail;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailChangedPortionDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductGroupDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductGroupPortionDTO;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public final class GetOrderDetailUseCase implements IGetOrderDetailUseCase {

    private GetOrderDetailUseCase(){
        //empty because in this class is not necessary to inject other classes
    }

    public static void invertReplacementPortions(@NotNull List<OrderDetailProductDTO> orderDetailProducts) {

        log.info("Starting process to invert portions replacements with products [{}]", orderDetailProducts);
        final List<OrderDetailProductGroupPortionDTO> portionsWithReplacement = new ArrayList<>();

        orderDetailProducts.forEach((OrderDetailProductDTO orderDetailProductDTO) ->
                orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO orderDetailProductGroupDTO) ->
                        portionsWithReplacement.addAll(orderDetailProductGroupDTO.getPortions()
                                .stream()
                                .filter(OrderDetailProductGroupPortionDTO::hasReplacement)
                                .collect(Collectors.toList()))
                )
        );

        portionsWithReplacement.forEach(GetOrderDetailUseCase::replacePortions);
    }

    private static void replacePortions(OrderDetailProductGroupPortionDTO orderDetailProductGroupPortionDTO) {
        OrderDetailChangedPortionDTO incomingChangedPortionDTO =
                orderDetailProductGroupPortionDTO.getChangedPortion();
        Long originalId = incomingChangedPortionDTO.getId();
        String originalName = incomingChangedPortionDTO.getName();
        Long originalParentId = incomingChangedPortionDTO.getParentId();

        OrderDetailChangedPortionDTO actualChangedPortionDTO = new OrderDetailChangedPortionDTO(
                orderDetailProductGroupPortionDTO.getId(),
                orderDetailProductGroupPortionDTO.getName(),
                orderDetailProductGroupPortionDTO.getParentId(),
                orderDetailProductGroupPortionDTO.getSku(),
                orderDetailProductGroupPortionDTO.getUnits(),
                orderDetailProductGroupPortionDTO.getWeight()
        );
        orderDetailProductGroupPortionDTO.setChangedPortion(actualChangedPortionDTO);
        orderDetailProductGroupPortionDTO.setId(originalId);
        orderDetailProductGroupPortionDTO.setName(originalName);
        orderDetailProductGroupPortionDTO.setParentId(originalParentId);
    }
}
