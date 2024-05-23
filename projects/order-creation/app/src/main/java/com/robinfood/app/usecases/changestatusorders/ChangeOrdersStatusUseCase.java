package com.robinfood.app.usecases.changestatusorders;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.changestatusordersrequestdto.ChangeStatusOrdersRequestDTO;
import com.robinfood.repository.changestatusorders.IChangeStatusOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of IChangeStatusOrdersUseCase
 */
@Service
@Slf4j
public class ChangeOrdersStatusUseCase implements IChangeOrdersStatusUseCase {

    private final IChangeStatusOrdersRepository changeStatusOrdersRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public ChangeOrdersStatusUseCase(
            IChangeStatusOrdersRepository changeStatusOrdersRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase) {
        this.changeStatusOrdersRepository = changeStatusOrdersRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    @Override
    public Boolean invoke(
        @NotNull ChangeStatusOrdersRequestDTO changeStatusOrdersRequestDTO) {

        var token = getTokenBusinessCapabilityUseCase.invoke();

        return changeStatusOrdersRepository.invoke(
            changeStatusOrdersRequestDTO,
            token.getAccessToken()
        ).join();
    }
}
