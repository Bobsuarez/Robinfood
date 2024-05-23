package com.robinfood.changestatusbc.facades;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.OrderStateDTO;
import com.robinfood.changestatusbc.usecases.getstateorders.IGetStateOrderUseCase;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetOrderStateFacade implements IGetOrderStateFacade {

    private final IGetStateOrderUseCase getStateOrderUseCase;

    public GetOrderStateFacade(IGetStateOrderUseCase getStateOrderUseCase) {
        this.getStateOrderUseCase = getStateOrderUseCase;
    }

    public OrderStateDTO invoke(ChangeOrderStatusDTO changeOrderStatusDTO) {

        if (!StringUtils.isBlank(changeOrderStatusDTO.getOrderUuid())) {
            return getStateOrderUseCase.invokeUuid(changeOrderStatusDTO.getOrderUuid());
        }

        if (!StringUtils.isBlank(changeOrderStatusDTO.getDeliveryIntegrationId()) ) {
            return getStateOrderUseCase.invokeDeliveryIntegrationId(changeOrderStatusDTO.getDeliveryIntegrationId());
        }

        return getStateOrderUseCase.invoke(changeOrderStatusDTO.getOrderId());
    }
}
