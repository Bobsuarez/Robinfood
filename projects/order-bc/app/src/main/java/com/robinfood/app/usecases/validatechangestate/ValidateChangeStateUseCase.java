package com.robinfood.app.usecases.validatechangestate;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.exceptions.GenericOrderBcException;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class ValidateChangeStateUseCase implements IValidateChangeStateUseCase{

    @Override
    public boolean invoke(ChangeOrderStatusDTO changeOrderStatusDTO) {

        if (Objects.isNull(changeOrderStatusDTO.getOrderId())
                && StringUtils.isBlank(changeOrderStatusDTO.getOrderUuid())
                && StringUtils.isBlank(changeOrderStatusDTO.getDeliveryIntegrationId()) ) {

            throw new GenericOrderBcException("Order is not posible to change " +
                    "due to missing OrderId or OrderUuid or deliveryIntegrationId");
        }

        return Boolean.TRUE;
    }
}
