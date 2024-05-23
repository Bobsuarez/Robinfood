package com.robinfood.localserver.electronicbillresponse.usecase.createelectronicbilling;

import com.robinfood.localserver.commons.dtos.electronicbill.RequestElectronicBillingDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.ResponseElectronicBillingDTO;
import com.robinfood.localserver.commons.entities.token.TokenModel;
import com.robinfood.localserver.electronicbillresponse.repositories.electronicbillingrepository.ICreateElectronicBillingRepository;
import com.robinfood.localserver.securitybc.usecase.gettokenorchestrator.IGetOrchestratorTokenUserUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of ICreateOrderElectronicBillUseCase
 */
@Service
@Slf4j
public class CreateOrderElectronicBillUseCase implements ICreateOrderElectronicBillUseCase {

    private final ICreateElectronicBillingRepository createElectronicBillingRepository;

    private final IGetOrchestratorTokenUserUseCase getOrchestratorTokenUserUseCase;

    public CreateOrderElectronicBillUseCase(
            ICreateElectronicBillingRepository createElectronicBillingRepository,
            IGetOrchestratorTokenUserUseCase getOrchestratorTokenUserUseCase
    ) {
        this.createElectronicBillingRepository = createElectronicBillingRepository;
        this.getOrchestratorTokenUserUseCase = getOrchestratorTokenUserUseCase;
    }

    public Boolean invoke(RequestElectronicBillingDTO requestElectronicBillingDTO) {

        log.debug("Creating order electronic billing use case execute invoke: {}", requestElectronicBillingDTO);

        TokenModel tokenUser = getOrchestratorTokenUserUseCase.invoke();

        ResponseElectronicBillingDTO response = createElectronicBillingRepository.invoke(
                requestElectronicBillingDTO,
                tokenUser.getAccessToken()
        );

        if (response.getId().isEmpty()) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
