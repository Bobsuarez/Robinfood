package com.robinfood.storeor.usecase.getbillingconfigurationbystore;

import com.robinfood.storeor.dtos.response.TreasureDepartmentsDTO;
import com.robinfood.storeor.mappers.ITreasuryDepartmentMapper;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.TreasureDepartmentsEntity;
import com.robinfood.storeor.entities.TreasuryDepartmentEntity;
import com.robinfood.storeor.repositories.billingrepository.IBillingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetBillingConfigurationByStoreUseCase implements IGetBillingConfigurationByStoreUseCase {

    private final IBillingRepository billingRepository;
    private final ITreasuryDepartmentMapper treasuryDepartmentMapper;

    public GetBillingConfigurationByStoreUseCase(
            IBillingRepository billingRepository,
            ITreasuryDepartmentMapper treasuryDepartmentMapper
    ) {
        this.billingRepository = billingRepository;
        this.treasuryDepartmentMapper = treasuryDepartmentMapper;
    }

    @Override
    public TreasureDepartmentsDTO invoke(Long storeId, String token) {

        log.info("Execute GetBillingConfigurationByStoreUseCase by store: {} and token: {}", storeId, token);

        APIResponseEntity<TreasureDepartmentsEntity> responseEntity = billingRepository
            .getBillingConfiguration(storeId, token);

        log.info("Response entity from getBillingConfiguration " + responseEntity.toString());

        TreasuryDepartmentEntity treasuryDepartment = responseEntity.getData().getTreasuryDepartment();

        return treasuryDepartmentMapper.treasureDepartmentsEntityToTreasureDepartmentsDto(treasuryDepartment);
    }
}
