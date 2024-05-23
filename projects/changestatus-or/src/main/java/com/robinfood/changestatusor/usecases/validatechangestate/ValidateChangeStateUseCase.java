package com.robinfood.changestatusor.usecases.validatechangestate;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.exceptions.GenericChangeStatusOrException;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidateChangeStateUseCase implements IValidateChangeStateUseCase{

    @Override
    public boolean invoke(ChangeOrderStatusDTO changeOrderStatusDTO) {

        if (Objects.isNull(changeOrderStatusDTO.getOrderId())
                && StringUtils.isBlank(changeOrderStatusDTO.getOrderUuid())
                && StringUtils.isBlank(changeOrderStatusDTO.getDeliveryIntegrationId())) {

            throw new GenericChangeStatusOrException("Order is not posible to change " +
                    "due to missing OrderId or OrderUuid or deliveryIntegrationId");
        }

        return Boolean.TRUE;
    }
}
