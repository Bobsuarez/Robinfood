package com.robinfood.changestatusor.usecases.changeorderstatus;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import com.robinfood.changestatusor.models.domain.Token;
import com.robinfood.changestatusor.repository.changestateorders.IChangeSateOrderRepository;
import com.robinfood.changestatusor.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.robinfood.changestatusor.utilities.ObjectMapperSingleton.objectToClassConvertValue;

@Slf4j
@Service
public class ChangeOrderStatusUseCase implements IChangeOrderStatusUseCase {

    private final IChangeSateOrderRepository changeSateOrderRepository;

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public ChangeOrderStatusUseCase(
            IChangeSateOrderRepository changeSateOrderRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase
    ) {
        this.changeSateOrderRepository = changeSateOrderRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    @Override
    public ChangeOrderStatusDTO invoke(ChangeOrderStatusDTO changeOrderStatusDTO) {

        Token token = getTokenBusinessCapabilityUseCase.invoke();

        final ChangeStateOrderRespondEntity changeStateOrderRespondEntity = changeSateOrderRepository
                .changeOrderStatus(changeOrderStatusDTO, token.getAccessToken());

        return objectToClassConvertValue(changeStateOrderRespondEntity, ChangeOrderStatusDTO.class);
    }
}
