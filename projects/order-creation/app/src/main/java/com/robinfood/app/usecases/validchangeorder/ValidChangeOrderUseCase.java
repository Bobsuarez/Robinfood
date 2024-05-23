package com.robinfood.app.usecases.validchangeorder;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class ValidChangeOrderUseCase implements IValidChangeOrderUseCase{
    @Override
    public Boolean invoke(StateChangeRequestDTO stateChangeRequestDTO) {

        if (Objects.nonNull(stateChangeRequestDTO.getOrderId())) {
            return Boolean.TRUE;
        }

        if (Objects.nonNull(stateChangeRequestDTO.getOrderUuid()) &&
                !stateChangeRequestDTO.getOrderUuid().equals(GlobalConstants.DEFAULT_STRING_VALUE)){
            return Boolean.TRUE;
        }

        if (Objects.nonNull(stateChangeRequestDTO.getDeliveryIntegrationId()) &&
                !stateChangeRequestDTO.getDeliveryIntegrationId().equals(GlobalConstants.DEFAULT_STRING_VALUE)){
            return Boolean.TRUE;
        }

        throw new TransactionCreationException(HttpStatus.PRECONDITION_REQUIRED, "Order Identifier can not be null");
    }
}
