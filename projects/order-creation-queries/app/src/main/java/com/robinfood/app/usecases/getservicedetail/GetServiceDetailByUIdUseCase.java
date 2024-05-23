package com.robinfood.app.usecases.getservicedetail;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.services.ServiceDetailDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.services.IServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of IGetServiceDetailByUIdUseCase
 */
@Service
@Slf4j
public class GetServiceDetailByUIdUseCase implements IGetServiceDetailByUIdUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IServiceRepository serviceRepository;

    public GetServiceDetailByUIdUseCase(IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IServiceRepository serviceRepository) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Result<ServiceDetailDTO> invoke(
            String orderUId
    ) {
        var token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("Get service detail by order uid {}", orderUId);
        return serviceRepository.getServiceDetail(
                orderUId,
            token.getAccessToken()
        );
    }
}
