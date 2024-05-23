package com.robinfood.app.usecases.setordersmultibrand;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Component
@Slf4j
public class SetOrdersMultiBrandUseCase implements ISetOrdersMultiBrandUseCase {

    @Override
    public void invoke(List<OrderDTO> orderDTOS) {

        orderDTOS
                .forEach((OrderDTO orderDTO) -> {
                    List<Long> brands = orderDTO.getFinalProducts().stream()
                            .map(finalProductDTO -> finalProductDTO.getBrand().getId()).distinct()
                            .collect(Collectors.toList());

                    if (brands.size() > GlobalConstants.SIZE_ONE) {
                        orderDTO.setIsMultiBrand(Boolean.TRUE);
                        log.info("Order {} marked as multiBrand", objectToJson(orderDTO));
                    }
                });
    }

}
