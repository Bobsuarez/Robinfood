package com.robinfood.storeor.usecase.getelectronicbillbyordersid;

import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import com.robinfood.storeor.repositories.electronicbillingrepository.IElectronicBillingRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class GetElectronicBillByOrdersIdUseCase implements IGetElectronicBillByOrdersIdUseCase {

    private final IElectronicBillingRepository electronicBillingRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public GetElectronicBillByOrdersIdUseCase(
            IElectronicBillingRepository electronicBillingRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase
    ) {
        this.electronicBillingRepository = electronicBillingRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    public List<ElectronicBillDTO> invoke(List<Long> ordersId) {

        log.info("Receiving invoke orders ids: {}", ordersId);

        var token = getTokenBusinessCapabilityUseCase.invoke();

        List<ElectronicBillDTO> listElectronicBillDto =
                electronicBillingRepository.findAllByOrderIdIn(token.getAccessToken(),ordersId);

        log.debug("list of orders find in respository: {}", listElectronicBillDto);

        return listElectronicBillDto;
    }
}
