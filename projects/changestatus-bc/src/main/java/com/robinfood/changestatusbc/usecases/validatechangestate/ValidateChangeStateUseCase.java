package com.robinfood.changestatusbc.usecases.validatechangestate;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.exceptions.GenericChangeStatusBcException;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidateChangeStateUseCase implements IValidateChangeStateUseCase {

    @Override
    public boolean invoke(ChangeOrderStatusDTO changeOrderStatusDTO) {

        if (Objects.isNull(changeOrderStatusDTO.getOrderId())
                && StringUtils.isBlank(changeOrderStatusDTO.getOrderUuid())
                && StringUtils.isBlank(changeOrderStatusDTO.getDeliveryIntegrationId()) ) {

            throw new GenericChangeStatusBcException("Order is not possible to change " +
                    "due to missing OrderId or OrderUuid or deliveryIntegrationId");
        }

        return Boolean.TRUE;
    }
}
