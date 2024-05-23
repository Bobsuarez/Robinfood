package com.robinfood.storeor.usecase.createelectronicbilling;

import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import com.robinfood.storeor.repositories.electronicbillingrepository.IElectronicBillingRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Implementation of ICreateOrderElectronicBillingUseCase
 */
@Slf4j
@Component
public class CreateElectronicBillingUseCase implements ICreateElectronicBillingUseCase {

    private final IElectronicBillingRepository createElectronicBillingRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public CreateElectronicBillingUseCase(
            IElectronicBillingRepository createElectronicBillingRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase
    ) {
        this.createElectronicBillingRepository = createElectronicBillingRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    @Override
    public CreateElectronicBillingResponseDTO invoke(
            @NotNull CreateElectronicBillingRequestDTO createElectronicBillingRequestDTO) {

        var token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("Orders electronic billing response {}, to create", createElectronicBillingRequestDTO);

        return createElectronicBillingRepository.save(
                token.getAccessToken(),
                createElectronicBillingRequestDTO
        );
    }
}
